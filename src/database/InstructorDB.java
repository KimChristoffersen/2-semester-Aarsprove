package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.Instructor;

public class InstructorDB implements InstructorDBIF {

	private static final String FIND_BY_ID_Q = "select * from instructor where instructor_id = ?";

	private PreparedStatement findByIdPS;

	public InstructorDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
	}

	public Instructor findInstructorById(int id) throws DataAccessException, SQLException {
		Instructor res = null;
		try {
			findByIdPS.setInt(1, id);
			ResultSet rs = findByIdPS.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}

		} catch (SQLException e) {
			throw new DataAccessException("Could not retrieve instructor", e);
		}

		return res;
	}

	public Instructor buildObject(ResultSet rs) throws DataAccessException {

		Instructor currentInstructor = new Instructor();
		try {
			currentInstructor.setInstructorId(rs.getInt("instructor_Id"));
			currentInstructor.setFirstName(rs.getString("fName"));
			currentInstructor.setLastName(rs.getString("lName"));
			currentInstructor.setAddress(rs.getString("address"));
			currentInstructor.setPostalCode(rs.getString("postalCode"));
			currentInstructor.setCity(rs.getString("city"));
			currentInstructor.setPhone(rs.getString("phone"));
			currentInstructor.setEmail(rs.getString("email"));

		} catch (SQLException e) {

			throw new DataAccessException("Could not retrieve Instructor", e);
		}
		return currentInstructor;

	}

}