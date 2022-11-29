package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;

public class CustomerDB implements CustomerDBIF {

	private static final String FIND_BY_ID_Q = "select c.customer_id, p.fName, p.lName, p.phone, p.email, a.address, a.postalCode_Id, pc.city from customer c, person p, address a, PostalCode pc where customer_Id = ?";

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

	public Customer buildObject(ResultSet rs) throws DataAccessException {
		Customer currentCustomer = new Customer();
		try {
			currentCustomer.setCustomerId(rs.getInt("customer_Id"));
			currentCustomer.setFirstName(rs.getString("fName"));
			currentCustomer.setLastName(rs.getString("lName"));
			currentCustomer.setAddress(rs.getString("address"));
			currentCustomer.setPostalCode(rs.getString("postalCode_Id"));
			currentCustomer.setCity(rs.getString("city"));
			currentCustomer.setPhone(rs.getString("phone"));
			currentCustomer.setEmail(rs.getString("email"));

		} catch (SQLException e) {

			throw new DataAccessException("Could not retrieve customer", e);
		}
		return currentCustomer;

	}

}