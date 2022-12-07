package database;

import java.sql.SQLException;
import java.util.List;

import model.Instructor;
/**
 * Interface for InstructorDB.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public interface InstructorDBIF {
	List<Instructor> findAll() throws DataAccessException, SQLException;
	Instructor findInstructorById(int id) throws DataAccessException, SQLException;
}
