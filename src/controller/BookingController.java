package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import database.BookingDB;
import database.BookingDBIF;
import database.DataAccessException;
import model.*;

public class BookingController {

	private Booking currentBooking;
	// private CustomerDBIF customerDB;
	private BookingDBIF bookingDB;
	private WeaponController weaponController;

	public BookingController() throws SQLException, DataAccessException {
		weaponController = new WeaponController();
		bookingDB = new BookingDB();
	}

	public void createBooking(Customer customer) {
		currentBooking = new Booking(customer);
	}

	public void addWeapon(int weaponId) throws DataAccessException, SQLException {
		currentBooking.setWeapon(findById(weaponId));
	}

	public Weapon findById(int weaponId) throws DataAccessException, SQLException {
		return weaponController.findById(weaponId);
	}

	public Booking getCurrentBooking() {
		return currentBooking;
	}
	
	public Booking findBookingByNumber(int id) throws DataAccessException, SQLException {
		return bookingDB.findBookingByNumber(id);
	}

	public List<Integer> getAvailableShootingRanges(LocalDate date, int time) throws DataAccessException {
		return bookingDB.getAvailableShootingRangeIds(date, time);
	}

	public List<Integer> getAvailableInstructors(LocalDate date, int time) throws DataAccessException {
		return bookingDB.getAvailableInstructors(date, time);
	}

	public List<LocalDateTime> getAvailability(List<LocalDateTime> weeksTimeSlots) throws DataAccessException {
		return bookingDB.getAvailabilityList(weeksTimeSlots);
	}

	public List<Booking> getWeekBookings(LocalDate startDate, LocalDate endDate) throws DataAccessException, SQLException {
		return bookingDB.getWeekBookings(startDate, endDate);
	}
	
	public void cancelBooking() {
		this.currentBooking = null;
	}
}
