package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Instructor;
import model.Price;
/**
 * Class for InstructorDB.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class InstructorDB implements InstructorDBIF {
	private PriceDBIF priceDB;

	private static final String FIND_ALL_Q = "select i.instructor_id, i.status, p.fName, p.lName, p.phone, p.email, a.address, a.postalCode_Id, pc.city from instructor i, person p, address a, PostalCode pc where status = ? and i.instructor_id = p.personId and p.address_id = a.address_Id and a.postalCode_Id = pc.postalCode";
	private static final String FIND_BY_ID_Q = "select i.instructor_id, i.status, p.fName, p.lName, p.phone, p.email, a.address, a.postalCode_Id, pc.city from instructor i, person p, address a, PostalCode pc where instructor_id = ? and i.instructor_id = p.personId and p.address_id = a.address_Id and a.postalCode_Id = pc.postalCode";

	private PreparedStatement findAllPS;
	private PreparedStatement findByIdPS;

	public InstructorDB() throws SQLException, DataAccessException {
		findAllPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_Q);
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
		
		priceDB = new PriceDB();
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

	private Instructor buildObject(ResultSet rs) throws DataAccessException {
        Instructor currentInstructor = null;
        try {
            int instructorId = rs.getInt("instructor_Id");
            Boolean status = rs.getBoolean("status");
            String firstName = rs.getString("fName");
            String lastName = rs.getString("lName");
            String address = rs.getString("address");
            String postalCode = rs.getString("postalCode_id");
            String city = rs.getString("city");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            Price price = priceDB.findPriceByInstructorID(instructorId);
            currentInstructor = new Instructor(firstName, lastName, address, postalCode, city, phone, email, instructorId, status, price);
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