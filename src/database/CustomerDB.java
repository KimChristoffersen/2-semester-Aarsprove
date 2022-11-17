package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;

public class CustomerDB implements CustomerDBIF {

	private static final String FIND_BY_ID_Q = "select * from Customer where customer_id = ?";

	private PreparedStatement findByIdPS;

	public CustomerDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
	}

	public Customer findCustomerById(String id) throws DataAccessException, SQLException {
		Customer res = null;
		try {
			findByIdPS.setString(1, id);
			ResultSet rs = findByIdPS.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}

		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve Customer", e);
		}

		return res;
	}

	public Customer buildObject(ResultSet rs) throws DataAccessException {
//		Customer c = new Customer(rs.getString("customerId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("address"), rs.getString("postalCode"), rs.getString("city"), rs.getString("phone"), rs.getString("email")) {};
//		return c;
		Customer currentCustomer = new Customer();
		try {
			currentCustomer.setCustomerId(rs.getString("customer_Id"));
			currentCustomer.setFirstName(rs.getString("fName"));
			currentCustomer.setLastName(rs.getString("lName"));
			currentCustomer.setAddress(rs.getString("address"));
			currentCustomer.setPostalCode(rs.getString("postalCode"));
			currentCustomer.setCity(rs.getString("city"));
			currentCustomer.setPhone(rs.getString("phone"));
			currentCustomer.setEmail(rs.getString("email"));

		} catch (SQLException e) {

			throw new DataAccessException("Could not retrieve Customer", e);
		}
		return currentCustomer;

	}

}