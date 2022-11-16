package model;

import java.time.LocalDateTime;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class Price {
	private LocalDateTime startDate;
	private double price;
	
	public Price(LocalDateTime startDate, double price) {
	
		this.startDate = startDate;
		this.price = price;
	}

	
	//GETTERS
	public LocalDateTime getStartDate() {
		return startDate;
	}

	public double getPrice() {
		return price;
	}


	//SETTERS
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
	
	

}
