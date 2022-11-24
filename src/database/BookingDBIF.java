package database;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import model.Booking;

public interface BookingDBIF {
	Booking buildObject(ResultSet rs) throws DataAccessException;	
	Booking createBooking(Booking booking) throws DataAccessException;
	List<Integer> getAvailableShootingRangeIds(LocalDate date, int time) throws DataAccessException;
	List<Integer> getAvailableInstructors(LocalDate date, int time) throws DataAccessException;

}


