package database;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import model.Booking;
/**
 * Interface for BookingDB.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public interface BookingDBIF {
	Booking confirmBooking(Booking booking) throws DataAccessException, SQLException;
	Booking findBookingByNumber(int id) throws DataAccessException, SQLException;
	List<Integer> getAvailableShootingRangeIds(LocalDate date, int time) throws DataAccessException;
	List<Integer> getAvailableInstructorIds(LocalDate date, int time) throws DataAccessException;
	List<Integer> getAvailableWeaponIds(LocalDate date, int time, int weaponId) throws DataAccessException;
	LocalDateTime getLastDatabaseChangeTime() throws DataAccessException;
	List<Booking> readBookings(int customer_id) throws DataAccessException;
}