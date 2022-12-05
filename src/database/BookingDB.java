package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class BookingDB implements BookingDBIF {

	private ShootingRangeDBIF shootingRangeDB;
	private CustomerDBIF customerDB;
	private InstructorDBIF instructorDB;
	private WeaponDBIF weaponDB;

	private static final String FIND_BY_ID_Q = "select * from booking where bookingnumber = ?";
	private static final String INSERT_Q = "insert into booking values(?, ?, ?, ?, ?,?,?,?)";
	private static final String FINDAVAILABLESHOOTINGRANGES_Q = "select shootingRange_Id from shootingRange where shootingRange_Id NOT IN (select shootingRange_Id from Booking where date = ? and time = ?)";
	private static final String FINDAVAILABLEINSTRUCTORS_Q = "select instructor_Id from Instructor where instructor_Id NOT IN (select instructor_Id from Booking where date = ? and time = ?)";
	private static final String FINDAVAILABLEWEAPONS_Q = "select weaponid from weapon where weaponid NOT IN (select weapon_id from Booking where date = ? and time = ?) and weaponid = ?";
	private static final String FIND_LAST_DATABASECHANGE_TIME_Q = "select max(datetime) as datetime from updatetime";
	
	
	private PreparedStatement findByIdPS;
	private PreparedStatement insertPS;
	private PreparedStatement findAvailableShootingRanges;
	private PreparedStatement findAvailableInstructors;
	private PreparedStatement findAvailableWeapons;
	private PreparedStatement findLastDatabaseChangeTime;

	public BookingDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
		insertPS = DBConnection.getInstance().getConnection().prepareStatement(INSERT_Q);
		findAvailableShootingRanges = DBConnection.getInstance().getConnection().prepareStatement(FINDAVAILABLESHOOTINGRANGES_Q);
		findAvailableInstructors = DBConnection.getInstance().getConnection().prepareStatement(FINDAVAILABLEINSTRUCTORS_Q);
		findAvailableWeapons = DBConnection.getInstance().getConnection().prepareStatement(FINDAVAILABLEWEAPONS_Q);
		findLastDatabaseChangeTime = DBConnection.getInstance().getConnection().prepareStatement(FIND_LAST_DATABASECHANGE_TIME_Q);
	}

	// Finds booking in database
	public Booking findBookingByNumber(int id) throws DataAccessException, SQLException {
		Booking booking = null;
		try {
			DBConnection.getInstance().startTransaction();
			findByIdPS.setInt(1, id);
			ResultSet rs = findByIdPS.executeQuery();
			if (rs.next()) {
				booking = buildObject(rs);
			}
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Could not retrieve booking", e);
		}
		return booking;
	}

	public Booking buildObject(ResultSet rs) throws DataAccessException {
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
            currentBooking = new Booking(bookingNumber, creationDate, priceTotal, date, time, customer, shootingRange, weapon, instructor);
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
	public Booking confirmBooking(Booking booking) throws DataAccessException {
		try {
			DBConnection.getInstance().startTransaction();
			
			// s�t database isolationsniveau
			
			
			
			insertPS.setDate(1, Date.valueOf(LocalDate.now()));
			insertPS.setDouble(2, booking.getPriceTotal());
			insertPS.setInt(3, booking.getTime());
			insertPS.setDate(4, Date.valueOf(booking.getDate()));
			insertPS.setInt(5, booking.getCustomer().getCustomerId());
			insertPS.setInt(6, booking.getInstructor().getInstructorId());
			insertPS.setInt(7, booking.getShootingRange().getShootingRangeId());
			insertPS.setInt(8, booking.getWeapon().getWeaponId());
			insertPS.executeUpdate();
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Booking could not be created", e);
		}
		return booking;
	}

	public List<Integer> getAvailableShootingRangeIds(LocalDate date, int time) throws DataAccessException {
		List<Integer> availShootingRanges = new LinkedList<>();
		try {
			Date sqlDate = Date.valueOf(date);
			findAvailableShootingRanges.setDate(1, sqlDate);
			findAvailableShootingRanges.setInt(2, time);
			ResultSet rs = findAvailableShootingRanges.executeQuery();
			while (rs.next()) {
				availShootingRanges.add(rs.getInt("shootingRange_Id"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve shootingRanges", e);
		}
		return availShootingRanges;
	}

	public List<Integer> getAvailableInstructorIds(LocalDate date, int time) throws DataAccessException {
		List<Integer> availableInstructors = new LinkedList<>();
		try {
			Date sqlDate = Date.valueOf(date);
			findAvailableInstructors.setDate(1, sqlDate);
			findAvailableInstructors.setInt(2, time);
			ResultSet rs = findAvailableInstructors.executeQuery();
			while (rs.next()) {
				availableInstructors.add(rs.getInt("instructor_Id"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve instructors", e);
		}
		return availableInstructors;
	}
	
	public List<Integer> getAvailableWeaponIds(LocalDate date, int time, int weaponId) throws DataAccessException {
		List<Integer> availableWeapons = new LinkedList<>();
		try {
			Date sqlDate = Date.valueOf(date);
			findAvailableWeapons.setDate(1, sqlDate);
			findAvailableWeapons.setInt(2, time);
			findAvailableWeapons.setInt(3, weaponId);
			ResultSet rs = findAvailableWeapons.executeQuery();
			while (rs.next()) {
				availableWeapons.add(rs.getInt("weaponId"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve weapons", e);
		}
		return availableWeapons;
	}

	public LocalDateTime getLastDatabaseChangeTime() throws DataAccessException {
		LocalDateTime localDateTime = null;
		try {
			ResultSet rs = findLastDatabaseChangeTime.executeQuery();
			if(rs.next()) {
				localDateTime = rs.getTimestamp("DateTime").toLocalDateTime();	
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve last database change time", e);
		}
		return localDateTime;
	}
}