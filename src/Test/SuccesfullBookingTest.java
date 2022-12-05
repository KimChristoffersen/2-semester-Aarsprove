package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingController;
import model.Booking;
import model.Customer;
import model.Instructor;
import model.Price;
import model.ShootingRange;
import model.Weapon;

class SuccesfullBookingTest {

	private static BookingController bookingController;

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
	void unitTestCalculatePriceTotal() {
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
	void integrationTestCreateBookingWithShootingRange() {
		// ARRANGE
		Customer customer = new Customer();
		customer.setCustomerId(1);
		//bookingController.createBooking(0);
		
		// ACT
		
		// ASSERT
	}
	
}
