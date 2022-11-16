package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Customer;

public interface CustomerDBIF {
	
	Customer buildObject(ResultSet rs) throws DataAccessException;		
}
