package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Booking {
	
	private int bookingNumber;
	private LocalDate creationDate;
	private double priceTotal;
	private LocalDate date;
	private LocalTime time;
	
	public Booking() {}
	
	
	public Booking(int bookingNumber, LocalDate creationDate, double priceTotal, LocalDate date, LocalTime time) {
		this.bookingNumber = bookingNumber;
		this.creationDate = creationDate;
		this.priceTotal = priceTotal;
		this.date = date;
		this.time = time;
	}

//GETTERS
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
	
	
	public LocalTime getTime() {
		return time;
	}


	//SETTERS
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


	public void setTime(LocalTime time) {
		this.time = time;
	}


	
	
}
