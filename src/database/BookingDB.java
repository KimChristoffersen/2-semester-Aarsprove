package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import model.Booking;

public class BookingDB implements BookingDBIF {

	private static final String FIND_BY_ID_Q = "select * from booking where bookingnumber = ?";
	private static final String INSERT_Q = "insert into booking (bookingNumber, creationDate, priceTotal, date, time) values (?, ?, ?, ?, ?)";
	private static final String FINDAVAILABLESHOOTINGRANGES_Q = "select shootingRange_Id from shootingRange where shootingRange_Id NOT IN (select shootingRange_Id from Booking where date = ? and time = ?)";
	private static final String FINDAVAILABLEINSTRUCTORS_Q = "select instructor_Id from Instructor where instructor_Id NOT IN (select instructor_Id from Booking where date = ? and time = ?)";
	private static final String CHECKAVAILABILTY_Q = "select shootingRange_Id from shootingRange where shootingRange_Id NOT IN (select shootingRange_Id from Booking where date = ? and time = ?)";

	private PreparedStatement findByIdPS;
	private PreparedStatement insertPS;
	private PreparedStatement findAvailableShootingRanges;
	private PreparedStatement findAvailableInstructors;
	private PreparedStatement checkAvailability;

	public BookingDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
		insertPS = DBConnection.getInstance().getConnection().prepareStatement(INSERT_Q);
		findAvailableShootingRanges = DBConnection.getInstance().getConnection()
				.prepareStatement(FINDAVAILABLESHOOTINGRANGES_Q);
		findAvailableInstructors = DBConnection.getInstance().getConnection()
				.prepareStatement(FINDAVAILABLEINSTRUCTORS_Q);
		checkAvailability = DBConnection.getInstance().getConnection().prepareStatement(CHECKAVAILABILTY_Q);

	}

	// Finds booking in database
	public Booking findBookingByNumber(int id) throws DataAccessException, SQLException {
		Booking res = null;
		try {
			DBConnection.getInstance().startTransaction();
			findByIdPS.setInt(1, id);
			ResultSet rs = findByIdPS.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
			DBConnection.getInstance().commitTransaction();

		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Could not retrieve booking", e);
		}

		return res;
	}

	public Booking buildObject(ResultSet rs) throws DataAccessException {

		Booking currentBooking = new Booking();
		try {
			currentBooking.setBookingNumber(rs.getInt("bookingNumber"));
			currentBooking.setCreationDate(rs.getDate("creationDate").toLocalDate());
			currentBooking.setPriceTotal(rs.getDouble("priceTotal"));
			currentBooking.setDate(rs.getDate("date").toLocalDate());
			currentBooking.setTime(rs.getTime("time").toLocalTime());

		} catch (SQLException e) {

			throw new DataAccessException("Could not retrieve booking", e);
		}
		return currentBooking;

	}

	// Creates booking in database
	public Booking createBooking(Booking booking) throws DataAccessException {

		try {
			DBConnection.getInstance().startTransaction();
			insertPS.setInt(1, booking.getBookingNumber());
			insertPS.setDate(2, Date.valueOf(booking.getCreationDate()));
			insertPS.setDouble(3, booking.getPriceTotal());
			insertPS.setDate(4, Date.valueOf(booking.getDate()));
			insertPS.setTime(5, Time.valueOf(booking.getTime()));
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

	public List<Integer> getAvailableInstructors(LocalDate date, int time) throws DataAccessException {
		List<Integer> availableInstructors = new LinkedList<>();
		try {
			Date sqlDate = Date.valueOf(date);
			findAvailableInstructors.setDate(1, sqlDate);
			findAvailableInstructors.setInt(2, time);
			ResultSet rs = findAvailableInstructors.executeQuery();
			while (rs.next()) {
				int shootingRange_Id = 0;
				availableInstructors.add(rs.getInt("shootingRange_Id"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve shootingRanges", e);
		}

		return availableInstructors;
	}

	public List<LocalDateTime> getAvailabilityList(List<LocalDateTime> weekTimes) throws DataAccessException {
		DBConnection.getInstance().startTransaction();
		try {
			for (int i = 0; i < weekTimes.size(); i++) {
				LocalDate localDate = weekTimes.get(i).toLocalDate();
				Date sqlDate = Date.valueOf(localDate);
				int time = weekTimes.get(i).getHour();
				checkAvailability.setDate(1, sqlDate);
				checkAvailability.setInt(2, time);
				ResultSet rs = checkAvailability.executeQuery();
				if (!rs.next()) {
					weekTimes.remove(weekTimes.get(i));
				}
			}
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Could not retrieve booking", e);
		}
		return weekTimes;
	}
}