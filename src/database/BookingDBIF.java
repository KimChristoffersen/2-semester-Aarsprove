package database;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.Booking;

public interface BookingDBIF {
	Booking createBooking(Booking booking) throws DataAccessException;
	public Booking findBookingByNumber(int id) throws DataAccessException, SQLException;
	List<Integer> getAvailableShootingRangeIds(LocalDate date, int time) throws DataAccessException;
	List<Integer> getAvailableInstructorIds(LocalDate date, int time) throws DataAccessException;
}


