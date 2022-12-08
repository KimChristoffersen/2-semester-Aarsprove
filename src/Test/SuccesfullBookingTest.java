package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingController;
import controller.ShootingRangeController;
import database.BookingDB;
import database.BookingDBIF;
import database.DataAccessException;
import model.Booking;
import model.Customer;
import model.Instructor;
import model.Price;
import model.ShootingRange;
import model.Weapon;

class TestCases {

	private static BookingController bookingController;
	private static ShootingRangeController shootingRangeController;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bookingController = new BookingController();
	}		

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void unitTestCancelBooking() throws DataAccessException, SQLException {
		// ARRANGE
		Booking booking = new Booking();
		bookingController.setBooking(booking);
	
		// ACT
		bookingController.cancelBooking();
		
		// ASSERT
		assertTrue(bookingController.getCurrentBooking() == null);
	}
	
	
	@Test
	void integrationsTestSuccesfullBooking() throws DataAccessException {
		// ARRANGE
		Price shootingRangePrice = new Price();
		shootingRangePrice.setPrice(350.00);
		Price weaponPrice = new Price();
		weaponPrice.setPrice(150.00);
		Price instructorPrice = new Price();
		instructorPrice.setPrice(500.00);
		ShootingRange shootingRange = new ShootingRange();
		shootingRange.setPrice(shootingRangePrice);
		Weapon weapon = new Weapon();
		weapon.setPrice(weaponPrice);
		Instructor instructor = new Instructor();
		instructor.setPrice(instructorPrice);

		Booking booking = new Booking();
		booking.setShootingRange(shootingRange);
		booking.setWeapon(weapon);
		booking.setInstructor(instructor);

		// ACT
		double totalPrice = bookingController.calculateTotal(booking);
		
		// ASSERT
		assertEquals(1000.00, totalPrice);

	}
	
	
	@Test
	void unitTestDesiredWeaponUnavailable() throws DataAccessException, SQLException {
		//ARRANGE
		Weapon weapon = new Weapon(1,"Glock",null,null,false,null);
		
		
		
		Booking booking = new Booking();
		bookingController.setBooking(booking);
	
		//ACT
		bookingController.addWeapon(1);
		
		//ASSERT
		assertTrue(weapon.getStatus());
		
	}
	
	
	
	
}
