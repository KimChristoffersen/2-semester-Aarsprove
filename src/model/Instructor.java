package model;
/**
 * Class for Instructor model.
 * 
 * Child of Person
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class Instructor extends Person {
	private int instructorId;
	private boolean status;
	private Price price;

	public Instructor() {
		super();
	}

	public Instructor(String firstName, String lastName, String address, String postalCode, String city, String phone,
			String email, int instructorId, boolean status, Price price) {

		super(firstName, lastName, address, postalCode, city, phone, email);
		this.instructorId = instructorId;
		this.status = status;
		this.price = price;
	}

	// GETTERS
	public int getInstructorId() {
		return instructorId;
	}

	public boolean getStatus() {
		return status;
	}

	public Price getPrice() {
		return price;
	}

	// SETTERS
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setPrice(Price price) {
		this.price = price;
	}
}