package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
/**
 * Class for CustomerDB.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class CustomerDB implements CustomerDBIF {
	private static final String FIND_BY_ID_Q = "select c.customer_id, p.fName, p.lName, p.phone, p.email, a.address, a.postalCode_Id, pc.city from customer c, person p, address a, PostalCode pc where customer_Id = ? and c.customer_id = p.personId and p.address_id = a.address_Id and a.postalCode_Id = pc.postalCode";

	private PreparedStatement findByIdPS;

	public CustomerDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
	}

	public Customer findCustomerById(int customerId) throws DataAccessException, SQLException {
		Customer res = null;
		try {
			DBConnection.getInstance().startTransaction();
			findByIdPS.setInt(1, customerId);
			ResultSet rs = findByIdPS.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Could not retrieve customer", e);
		}
		return res;
	}

	private Customer buildObject(ResultSet rs) throws DataAccessException {
		Customer currentCustomer = null;
		try {
			int customerId = rs.getInt("customer_Id");
			String firstName = rs.getString("fName");
			String lastName = rs.getString("lName");
			String address = rs.getString("address");
			String PostalCode = rs.getString("postalCode_Id");
			String City = rs.getString("city");
			String Phone = rs.getString("phone");
			String Email = rs.getString("email");
			currentCustomer = new Customer(firstName, lastName, address, PostalCode, City, Phone, Email, customerId);
		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve customer", e);
		}
		return currentCustomer;
	}
}