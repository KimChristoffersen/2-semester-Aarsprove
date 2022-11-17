package model;

public class Instructor extends Person {
	private int instructorId;
	private boolean status;
	
	public Instructor() {
		super();
	}

	public Instructor(String firstName, String lastName, String address, String postalCode, String city, String phone,
			String email, int instructorId, boolean status) {

		super(firstName, lastName, address, postalCode, city, phone, email);
		this.instructorId = instructorId;
		this.status = status;
	}

	// GETTERS
	public int getInstructorId() {
		return instructorId;
	}

	public boolean getStatus() {
		return status;
	}

	// SETTERS
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
