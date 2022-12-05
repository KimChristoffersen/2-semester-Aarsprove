package database;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import model.Booking;

public interface BookingDBIF {
	Booking confirmBooking(Booking booking) throws DataAccessException;
	public Booking findBookingByNumber(int id) throws DataAccessException, SQLException;
	List<Integer> getAvailableShootingRangeIds(LocalDate date, int time) throws DataAccessException;
	List<Integer> getAvailableInstructorIds(LocalDate date, int time) throws DataAccessException;
	int getNewestBookingNumber() throws DataAccessException;
	LocalDateTime getLastDatabaseChangeTime() throws DataAccessException;
}


