package database;

import java.sql.ResultSet;

import model.Booking;

public interface BookingDBIF {
	Booking buildObject(ResultSet rs) throws DataAccessException;	
	Booking createBooking(Booking booking) throws DataAccessException;
}


