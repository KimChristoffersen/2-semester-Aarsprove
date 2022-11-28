package database;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import model.Booking;

public interface BookingDBIF {
	Booking createBooking(Booking booking) throws DataAccessException;
	List<Integer> getAvailableShootingRangeIds(LocalDate date, int time) throws DataAccessException;
	List<Integer> getAvailableInstructors(LocalDate date, int time) throws DataAccessException;
	List<LocalDateTime> getAvailabilityList(List<LocalDateTime> weekTimeSlots) throws DataAccessException;
}


