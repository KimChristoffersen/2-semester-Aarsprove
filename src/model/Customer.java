package model;

public class Customer extends Person {

	private String customerId;
	


	public Customer(String firstName, String lastName, String address, String postalCode, String city, String phone,
			String email, String customerId) {
		super(firstName, lastName, address, postalCode, city, phone, email);
		
		this.customerId = customerId;

	}

	// GETTERS
	public String getCustomerId() {
		return customerId;
	}

	//SETTERS
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
