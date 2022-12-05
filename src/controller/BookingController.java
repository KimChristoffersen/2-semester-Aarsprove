package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import database.BookingDB;
import database.BookingDBIF;
import database.CustomerDBIF;
import database.DataAccessException;
import model.*;

public class BookingController {

	private CustomerController customerController;
	private ShootingRangeController shootingRangeController;
	private InstructorController instructorController;
	private WeaponController weaponController;
	private BookingDBIF bookingDB;
	private Booking currentBooking;

	public BookingController() throws SQLException, DataAccessException {
		customerController = new CustomerController();
		shootingRangeController = new ShootingRangeController();
		instructorController = new InstructorController();
		weaponController = new WeaponController();
		bookingDB = new BookingDB();
	}

	public void createBooking(int customerId) throws DataAccessException, SQLException {
		currentBooking = new Booking(customerController.findCustomerById(customerId));
	}

	public Customer findByCustomerById(int customerId) throws DataAccessException, SQLException {
		return customerController.findCustomerById(customerId);
	}

	public void addWeapon(int weaponId) throws DataAccessException, SQLException {
		currentBooking.setWeapon(findWeaponById(weaponId));
	}

	public Weapon findWeaponById(int weaponId) throws DataAccessException, SQLException {
		return weaponController.findById(weaponId);
	}

	public void setTimeSlot(LocalDate date, int time, int instructorId, int shootingRangeId)
			throws DataAccessException, SQLException {
		currentBooking.setDate(date);
		currentBooking.setTime(time);
		currentBooking.setInstructor(instructorController.findById(instructorId));
		currentBooking.setShootingRange(shootingRangeController.findById(shootingRangeId));
		currentBooking.setPriceTotal(calculateTotal(currentBooking));
	}

	public void confirmBooking() throws DataAccessException {
		bookingDB.confirmBooking(currentBooking);
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
		return bookingDB.getAvailableInstructorIds(date, time);
	}

	public void cancelBooking() {
		this.currentBooking = null;
	}

	public int getNewestBookingNumber() throws DataAccessException {
		return bookingDB.getNewestBookingNumber();
	}

	public double calculateTotal(Booking booking) {
		return booking.getShootingRange().getPrice().getPrice()
				+ booking.getInstructor().getPrice().getPrice()
				+ booking.getWeapon().getPrice().getPrice();
	}

	public LocalDateTime getLastDataBaseChangeTime() throws DataAccessException {
		return bookingDB.getLastDatabaseChangeTime();
	}
}
