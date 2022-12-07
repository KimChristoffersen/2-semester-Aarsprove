package model;
/**
 * Class for Customer model.
 * 
 * Child of Person
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class Customer extends Person {
	private int customerId;

	public Customer() {
		super();
	}

	public Customer(String firstName, String lastName, String address, String postalCode, String city, String phone,
			String email, int customerId) {
		super(firstName, lastName, address, postalCode, city, phone, email);
		this.customerId = customerId;
	}

	// GETTERS
	public int getCustomerId() {
		return customerId;
	}

	// SETTERS
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}