package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;


import model.Booking;


public class BookingDB implements BookingDBIF {

	private static final String FIND_BY_ID_Q = "select * from booking where bookingnumber = ?";
	private static final String INSERT_Q = "insert into booking (bookingNumber, creationDate, priceTotal, date, time) values (?, ?, ?, ?, ?)";
	

	private PreparedStatement findByIdPS;
	private PreparedStatement insertPS;
	

	public BookingDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
		insertPS = DBConnection.getInstance().getConnection().prepareStatement(INSERT_Q);
	}

	
	//Finds booking in database
	public Booking findBookingByNumber(int id) throws DataAccessException, SQLException {
		Booking res = null;
		try {
			findByIdPS.setInt(1, id);
			ResultSet rs = findByIdPS.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}

		} catch (SQLException e) {
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
	
	
	//Creates booking in database
	public Booking createBooking(Booking booking) throws DataAccessException {
		
		try {
			insertPS.setInt(1, booking.getBookingNumber());
			insertPS.setDate(2, Date.valueOf(booking.getCreationDate()));
			insertPS.setDouble(3, booking.getPriceTotal());
			insertPS.setDate(4, Date.valueOf(booking.getDate()));
			insertPS.setTime(5, Time.valueOf(booking.getTime()));
						
			
		} catch (SQLException e) {
			
			throw new DataAccessException("Booking could not be created", e);
		}
		try {
			insertPS.executeUpdate();
		} catch (SQLException e) {
			
			throw new DataAccessException("Booking could not be created", e);
		}

		return booking;
	}
	
	

}