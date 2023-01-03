package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Booking;
import model.Customer;
import model.Instructor;
import model.ShootingRange;
import model.Weapon;

/**
 * Class for BookingDB.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class BookingDB implements BookingDBIF {
	private ShootingRangeDBIF shootingRangeDB;
	private CustomerDBIF customerDB;
	private InstructorDBIF instructorDB;
	private WeaponDBIF weaponDB;
	private Connection con;

	private static final String FIND_BY_ID_Q = "select * from booking where bookingnumber = ?";
	private static final String INSERT_Q = "insert into booking values(?, ?, ?, ?, ?,?,?,?)";
	private static final String FINDAVAILABLESHOOTINGRANGES_Q = "select shootingRange_Id from shootingRange where shootingRange_Id NOT IN (select shootingRange_Id from Booking where date = ? and time = ?) and status = 1";
	private static final String FINDAVAILABLEINSTRUCTORS_Q = "select instructor_Id from Instructor where instructor_Id NOT IN (select instructor_Id from Booking where date = ? and time = ?) and status = 1";
	private static final String FINDAVAILABLEWEAPONS_Q = "select weaponid from weapon where weaponid NOT IN (select weapon_id from Booking where date = ? and time = ?) and weaponid = ? and status = 1";
	private static final String FIND_LAST_DATABASECHANGE_TIME_Q = "select max(datetime) as datetime from updatetime";
	private static final String CHECK_FOR_DOUBLEBOOKING_Q = "select * from booking where (date = ? and time = ? and ShootingRange_Id = ?) OR (date = ? and time = ? and instructor_id = ?) OR (date = ? and time = ? and Weapon_Id = ?)";
	private static final String INSERT_TIMESTAMP_Q = "insert into UpdateTime values(?)";
	private static final String READ_BOOKINGS_Q = "select * from booking where customer_ID = ?";

	private PreparedStatement findByIdPS;
	private PreparedStatement insertPS;
	private PreparedStatement findAvailableShootingRangesPS;
	private PreparedStatement findAvailableInstructorsPS;
	private PreparedStatement findAvailableWeaponsPS;
	private PreparedStatement findLastDatabaseChangeTimePS;
	private PreparedStatement checkForDoubleBookingPS;
	private PreparedStatement insertTimestampPS;
	private PreparedStatement readBookingsPS;


	public BookingDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
		insertPS = DBConnection.getInstance().getConnection().prepareStatement(INSERT_Q,
				Statement.RETURN_GENERATED_KEYS);
		findAvailableShootingRangesPS = DBConnection.getInstance().getConnection()
				.prepareStatement(FINDAVAILABLESHOOTINGRANGES_Q);
		findAvailableInstructorsPS = DBConnection.getInstance().getConnection()
				.prepareStatement(FINDAVAILABLEINSTRUCTORS_Q);
		findAvailableWeaponsPS = DBConnection.getInstance().getConnection().prepareStatement(FINDAVAILABLEWEAPONS_Q);
		findLastDatabaseChangeTimePS = DBConnection.getInstance().getConnection()
				.prepareStatement(FIND_LAST_DATABASECHANGE_TIME_Q);
		checkForDoubleBookingPS = DBConnection.getInstance().getConnection().prepareStatement(CHECK_FOR_DOUBLEBOOKING_Q);
		insertTimestampPS = DBConnection.getInstance().getConnection().prepareStatement(INSERT_TIMESTAMP_Q);
		readBookingsPS = DBConnection.getInstance().getConnection().prepareStatement(READ_BOOKINGS_Q);
		con = DBConnection.getInstance().getConnection();
		customerDB = new CustomerDB();
		shootingRangeDB = new ShootingRangeDB();
		instructorDB = new InstructorDB();
		weaponDB = new WeaponDB();
		
	}

	// Finds booking in database
	public Booking findBookingByNumber(int id) throws DataAccessException, SQLException {
		Booking booking = null;
		try {
			DBConnection.getInstance().startTransaction();
			findByIdPS.setInt(1, id);
			ResultSet rs = findByIdPS.executeQuery();
			while (rs.next()) {
				booking = buildObject(rs);
			}
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Could not retrieve booking", e);
		}
		return booking;
	}

	public List<Booking> readBookings(int customer_id) throws DataAccessException{
		List<Booking> customerBookings = new ArrayList<>();
		try {
			readBookingsPS.setInt(1, customer_id);
			ResultSet rs = readBookingsPS.executeQuery();
			while (rs.next()) {
				customerBookings.add(buildObject(rs));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve any bookings", e);
		}
		return customerBookings;
		
	}
	
	private Booking buildObject(ResultSet rs) throws DataAccessException {
		Booking currentBooking = null;
		try {
			int bookingNumber = rs.getInt("bookingNumber");
			LocalDate creationDate = rs.getDate("creationDate").toLocalDate();
			double priceTotal = rs.getDouble("priceTotal");
			int time = rs.getInt("time");
			LocalDate date = rs.getDate("date").toLocalDate();
			Customer customer = customerDB.findCustomerById(rs.getInt("customer_Id"));
			Instructor instructor = instructorDB.findInstructorById(rs.getInt("Instructor_Id"));
			ShootingRange shootingRange = shootingRangeDB.findShootingRangeById(rs.getInt("shootingRange_Id"));
			Weapon weapon = weaponDB.findWeaponById(rs.getInt("weapon_Id"));
			currentBooking = new Booking(bookingNumber, creationDate, priceTotal, date, time, customer, shootingRange,
					weapon, instructor);
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve booking", e);
		}
		return currentBooking;
	}
	

	private List<Booking> buildObjects(ResultSet rs) throws SQLException, DataAccessException {
		List<Booking> bookings = new ArrayList<>();
		while (rs.next()) {
			bookings.add(buildObject(rs));
		}
		return bookings;
	}

	// Creates booking in database
	public Booking confirmBooking(Booking booking) throws DataAccessException, SQLException {
		try {
			DBConnection.getInstance().startTransaction();
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			if (!checkForDoubleBookingOfRessource(booking)) {
				insertPS.setDate(1, Date.valueOf(LocalDate.now()));
				insertPS.setDouble(2, booking.getPriceTotal());
				insertPS.setInt(3, booking.getTime());
				insertPS.setDate(4, Date.valueOf(booking.getDate()));
				insertPS.setInt(5, booking.getCustomer().getCustomerId());
				insertPS.setInt(6, booking.getInstructor().getInstructorId());
				insertPS.setInt(7, booking.getShootingRange().getShootingRangeId());
				insertPS.setInt(8, booking.getWeapon().getWeaponId());
				int bookingNumber = DBConnection.getInstance().executeInsertWithIdentity(insertPS);
				booking.setBookingNumber(bookingNumber);
				// Sets new timestamp for last database change which is used by the pollthread
				insertTimestamp();
				DBConnection.getInstance().commitTransaction();
			}
		} catch (
		SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Booking could not be created", e);
		}
		finally{con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);}		
		return booking;
	}

	private boolean checkForDoubleBookingOfRessource(Booking booking) throws SQLException {
		boolean hasDoubleBooking = false;
		checkForDoubleBookingPS.setDate(1, Date.valueOf(booking.getDate()));
		checkForDoubleBookingPS.setInt(2, booking.getTime());
		checkForDoubleBookingPS.setInt(3, booking.getShootingRange().getShootingRangeId());
		checkForDoubleBookingPS.setDate(4, Date.valueOf(booking.getDate()));
		checkForDoubleBookingPS.setInt(5, booking.getTime());
		checkForDoubleBookingPS.setInt(6, booking.getInstructor().getInstructorId());
		checkForDoubleBookingPS.setDate(7, Date.valueOf(booking.getDate()));
		checkForDoubleBookingPS.setInt(8, booking.getTime());
		checkForDoubleBookingPS.setInt(9, booking.getWeapon().getWeaponId());
		ResultSet rs = checkForDoubleBookingPS.executeQuery();
		if (rs.next()) {
			hasDoubleBooking = true;
		}
		return hasDoubleBooking;
	}

	// creates list with available shootingrange ids for calendarbuttons
	public List<Integer> getAvailableShootingRangeIds(LocalDate date, int time) throws DataAccessException {
		List<Integer> availShootingRanges = new ArrayList<>();
		try {
			Date sqlDate = Date.valueOf(date);
			findAvailableShootingRangesPS.setDate(1, sqlDate);
			findAvailableShootingRangesPS.setInt(2, time);
			ResultSet rs = findAvailableShootingRangesPS.executeQuery();
			while (rs.next()) {
				availShootingRanges.add(rs.getInt("shootingRange_Id"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve shootingRanges", e);
		}
		return availShootingRanges;
	}

	// creates list with available instructor ids for calendarbuttons
	public List<Integer> getAvailableInstructorIds(LocalDate date, int time) throws DataAccessException {
		List<Integer> availableInstructors = new ArrayList<>();
		try {
			Date sqlDate = Date.valueOf(date);
			findAvailableInstructorsPS.setDate(1, sqlDate);
			findAvailableInstructorsPS.setInt(2, time);
			ResultSet rs = findAvailableInstructorsPS.executeQuery();
			while (rs.next()) {
				availableInstructors.add(rs.getInt("instructor_Id"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve instructors", e);
		}
		return availableInstructors;
	}

	// creates list with available weapon ids for calendarbuttons
	public List<Integer> getAvailableWeaponIds(LocalDate date, int time, int weaponId) throws DataAccessException {
		List<Integer> availableWeapons = new ArrayList<>();
		try {
			Date sqlDate = Date.valueOf(date);
			findAvailableWeaponsPS.setDate(1, sqlDate);
			findAvailableWeaponsPS.setInt(2, time);
			findAvailableWeaponsPS.setInt(3, weaponId);
			ResultSet rs = findAvailableWeaponsPS.executeQuery();
			while (rs.next()) {
				availableWeapons.add(rs.getInt("weaponId"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve weapons", e);
		}
		return availableWeapons;
	}

	// gets last database change for poll thread
	public LocalDateTime getLastDatabaseChangeTime() throws DataAccessException {
		LocalDateTime localDateTime = null;
		try {
			ResultSet rs = findLastDatabaseChangeTimePS.executeQuery();
			if (rs.next()) {
				localDateTime = rs.getTimestamp("DateTime").toLocalDateTime();
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve last database change time", e);
		}
		return localDateTime;
	}

	private void insertTimestamp() throws SQLException {
		long now = System.currentTimeMillis();
		Timestamp sqlTimestamp = new Timestamp(now);
		insertTimestampPS.setTimestamp(1, sqlTimestamp);
		insertTimestampPS.executeUpdate();
	}
}