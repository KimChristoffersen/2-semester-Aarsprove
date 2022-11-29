package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

	private PreparedStatement findByIdPS;
	private PreparedStatement insertPS;
	private PreparedStatement findAvailableShootingRanges;
	private PreparedStatement findAvailableInstructors;

	public BookingDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
		insertPS = DBConnection.getInstance().getConnection().prepareStatement(INSERT_Q);
		findAvailableShootingRanges = DBConnection.getInstance().getConnection().prepareStatement(FINDAVAILABLESHOOTINGRANGES_Q);
		findAvailableInstructors = DBConnection.getInstance().getConnection().prepareStatement(FINDAVAILABLEINSTRUCTORS_Q);
		shootingRangeDB = new ShootingRangeDB();
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
		Booking currentBooking = new Booking();
		try {
			currentBooking.setBookingNumber(rs.getInt("bookingNumber"));
			currentBooking.setCreationDate(rs.getDate("creationDate").toLocalDate());
			currentBooking.setPriceTotal(rs.getDouble("priceTotal"));
			currentBooking.setTime(rs.getInt("time"));
			currentBooking.setDate(rs.getDate("date").toLocalDate());
			Customer customer = customerDB.findCustomerById(rs.getInt("customer_Id"));
			currentBooking.setCustomer(customer);
			Instructor instructor = instructorDB.findInstructorById(rs.getInt("Instructor_Id"));
			currentBooking.setInstructor(instructor);
			ShootingRange shootingRange = shootingRangeDB.findShootingRangeById(rs.getInt("shootingRange_Id"));
			currentBooking.setShootingRange(shootingRange);
			Weapon weapon = weaponDB.findWeaponById(rs.getInt("weapon_Id"));
			currentBooking.setWeapon(weapon);
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
	public Booking createBooking(Booking booking) throws DataAccessException {
		try {
			DBConnection.getInstance().startTransaction();
			insertPS.setDate(1, Date.valueOf(booking.getCreationDate()));
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
				int shootingRange_Id = 0;
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
				int instructorRange_Id = 0;
				availableInstructors.add(rs.getInt("instructor_Id"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve instructors", e);
		}

		return availableInstructors;
	}
}