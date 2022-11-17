package database;

import java.sql.ResultSet;


import model.Instructor;

public interface InstructorDBIF {
	
	Instructor buildObject(ResultSet rs) throws DataAccessException;		
}
