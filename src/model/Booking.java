package model;
/**
 * Class for Booking model.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
import java.time.LocalDate;

public class Booking {
	private int bookingNumber;
	private LocalDate creationDate;
	private double priceTotal;
	private LocalDate date;
	private int time;
	private Customer customer;
	private ShootingRange shootingRange;
	private Weapon weapon;
	private Instructor instructor;

	public Booking() {
	}

	public Booking(Customer customer) {
		this.customer = customer;
	}

	public Booking(int bookingNumber, LocalDate creationDate, double priceTotal, LocalDate date, int time,
			Customer customer, ShootingRange shootingRange, Weapon weapon, Instructor instructor) {
		this.bookingNumber = bookingNumber;
		this.creationDate = creationDate;
		this.priceTotal = priceTotal;
		this.date = date;
		this.time = time;
		this.customer = customer;
		this.shootingRange = shootingRange;
		this.weapon = weapon;
		this.instructor = instructor;
	}

	// GETTERS
	public int getBookingNumber() {
		return bookingNumber;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public double getPriceTotal() {
		return priceTotal;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getTime() {
		return time;
	}

	public Customer getCustomer() {
		return customer;
	}

	public ShootingRange getShootingRange() {
		return shootingRange;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	// SETTERS
	public void setBookingNumber(int bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public void setPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setShootingRange(ShootingRange shootingRange) {
		this.shootingRange = shootingRange;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
}