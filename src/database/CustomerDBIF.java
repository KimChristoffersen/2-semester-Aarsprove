package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Customer;

public interface CustomerDBIF {
	Customer findCustomerById(int customerId) throws DataAccessException, SQLException;
}
