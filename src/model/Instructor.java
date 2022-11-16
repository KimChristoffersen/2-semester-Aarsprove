package model;

public class Instructor extends Person {
	private String instructorId;
	private boolean status;

	public Instructor(String firstName, String lastName, String address, String postalCode, String city, String phone,
			String email, String instructorId, boolean status) {

		super(firstName, lastName, address, postalCode, city, phone, email);
		this.instructorId = instructorId;
		this.status = status;
	}

	// GETTERS
	public String getInstructorId() {
		return instructorId;
	}

	public boolean getStatus() {
		return status;
	}

	// SETTERS
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
