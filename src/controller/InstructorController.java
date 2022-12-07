package controller;

import java.sql.SQLException;
import java.util.List;
import database.DataAccessException;
import database.InstructorDB;
import database.InstructorDBIF;
import model.Instructor;
/**
 * Class for InstructorController.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class InstructorController {
	private InstructorDBIF instructorDB;

	public InstructorController() throws SQLException, DataAccessException {
		instructorDB = new InstructorDB();
	}

	public List<Instructor> findAll() throws DataAccessException, SQLException {
		return instructorDB.findAll();
	}

	public Instructor findById(int instructorId) throws DataAccessException, SQLException {
		return instructorDB.findInstructorById(instructorId);
	}
}
