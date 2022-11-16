package model;

import java.time.LocalDateTime;

public class Booking {
	
	private Long bookingNumber;
	private LocalDateTime creationDate;
	private double priceTotal;
	private LocalDateTime timeSlot;
	
	
	public Booking(Long bookingNumber, LocalDateTime creationDate, double priceTotal, LocalDateTime timeSlot) {
		this.bookingNumber = bookingNumber;
		this.creationDate = creationDate;
		this.priceTotal = priceTotal;
		this.timeSlot = timeSlot;
	}

//GETTERS
	public Long getBookingNumber() {
		return bookingNumber;
	}


	public LocalDateTime getCreationDate() {
		return creationDate;
	}


	public double getPriceTotal() {
		return priceTotal;
	}


	public LocalDateTime getTimeSlot() {
		return timeSlot;
	}

	
	//SETTERS
	public void setBookingNumber(Long bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public void setPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}

	public void setTimeSlot(LocalDateTime timeSlot) {
		this.timeSlot = timeSlot;
	}

	
	
	
	
}
