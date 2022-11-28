package controller;

import java.sql.SQLException;

import database.CustomerDB;
import database.CustomerDBIF;
import database.DataAccessException;
import model.Customer;

public class CustomerController {

	CustomerDBIF customerDB;
	
	public CustomerController() throws SQLException, DataAccessException {
		customerDB = new CustomerDB();
	}
	
	public Customer findCustomerById(int customerId) throws DataAccessException, SQLException {
		return customerDB.findCustomerById(customerId);
	}
	
}
