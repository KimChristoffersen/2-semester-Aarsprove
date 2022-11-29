package database;

import java.sql.SQLException;
import model.Customer;

public interface CustomerDBIF {
	Customer findCustomerById(int customerId) throws DataAccessException, SQLException;
}
