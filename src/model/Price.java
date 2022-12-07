package model;

import java.time.LocalDate;
/**
 * Class for Price model.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class Price {
	private LocalDate startDate;
	private double price;
	
	public Price() {
	}
	
	public Price(LocalDate startDate, double price) {
		this.startDate = startDate;
		this.price = price;
	}

	//GETTERS
	public LocalDate getStartDate() {
		return startDate;
	}

	public double getPrice() {
		return price;
	}

	//SETTERS
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}