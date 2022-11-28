package database;


import java.sql.SQLException;
import java.util.List;

import model.ShootingRange;

public interface ShootingRangeDBIF {
	List<ShootingRange> findAll() throws DataAccessException, SQLException;	
	ShootingRange findShootingRangeById(int id) throws DataAccessException, SQLException;

}
