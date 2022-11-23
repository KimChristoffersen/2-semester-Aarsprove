package controller;

import java.sql.SQLException;
import java.util.List;

import database.DataAccessException;
import model.*;

public class BookingController {

	private Booking currentBooking;
	// private CustomerDBIF customerDB;
	// private BookingDBIF bookingDB;
	private WeaponController weaponController;
	
	public BookingController() throws SQLException, DataAccessException {
		weaponController = new WeaponController();
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
	
//	public List<Integer> getAvailableShootingRanges(LocalDate date, int time){
//	return shootingRangeIds = bookingDB.getAvailableShootingRangeIds();
//	}
//
//	public List<Integer> getAvailableInstructors(LocalDate date, int time){
//	return availableInstructors = bookingDB.getAvailableInstructors();
//	}
}
