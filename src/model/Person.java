package model;
/**
 * Class for Person model.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class Person {
	private String firstName;
	private String lastName;
	private String address;
	private String postalCode;
	private String city;
	private String phone;
	private String email;

	public Person() {
	}

	public Person(String firstName, String lastName, String address, String postalCode, String city, String phone,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
		this.phone = phone;
		this.email = email;
	}

	// GETTERS
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	//SETTERS
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}