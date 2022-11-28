package database;

import java.sql.SQLException;
import java.util.List;

import model.Instructor;

public interface InstructorDBIF {
	
	List<Instructor> findAll() throws DataAccessException, SQLException;
	Instructor findInstructorById(int id) throws DataAccessException, SQLException;
}
