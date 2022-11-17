package database;

import java.sql.ResultSet;

import model.ShootingRange;

public interface ShootingRangeDBIF {
	ShootingRange buildObject(ResultSet rs) throws DataAccessException;		

}
