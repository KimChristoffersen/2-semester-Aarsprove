package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Instructor;

public class InstructorDB implements InstructorDBIF {

	private static final String FIND_ALL_Q = "select i.instructor_id, p.fName, p.lName, p.phone, p.email, a.address, a.postalCode_Id, pc.city from instructor i, person p, address a, PostalCode pc where status = 1";
	private static final String FIND_BY_ID_Q = "select i.instructor_id, p.fName, p.lName, p.phone, p.email, a.address, a.postalCode_Id, pc.city from instructor i, person p, address a, PostalCode pc where instructor_id = ?";

	private PreparedStatement findAllPS;
	private PreparedStatement findByIdPS;

	public InstructorDB() throws SQLException, DataAccessException {
		findAllPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_Q);
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
	}

	public List<Instructor> findAll() throws DataAccessException, SQLException {
		ResultSet rs;
		try {
			DBConnection.getInstance().startTransaction();
			rs = findAllPS.executeQuery();
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Could not retrieve any instructor", e);
		}
		List<Instructor> instructors = buildObjects(rs);
		return instructors;
	}
	
	public Instructor findInstructorById(int id) throws DataAccessException, SQLException {
		Instructor res = null;
		try {
			DBConnection.getInstance().startTransaction();
			findByIdPS.setInt(1, id);
			ResultSet rs = findByIdPS.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
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

	private List<Instructor> buildObjects(ResultSet rs) throws SQLException, DataAccessException {
		List<Instructor> instructors = new ArrayList<>();
		while (rs.next()) {
			instructors.add(buildObject(rs));
		}
		return instructors;
	}

	
	
}