package database;

import java.sql.SQLException;
import model.Customer;
/**
 * Interface for CustomerDB.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public interface CustomerDBIF {
	Customer findCustomerById(int customerId) throws DataAccessException, SQLException;
}
