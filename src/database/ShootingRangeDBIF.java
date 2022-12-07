package database;

import java.sql.SQLException;
import java.util.List;

import model.ShootingRange;
/**
 * Interface for ShootingRangeDB.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public interface ShootingRangeDBIF {
	List<ShootingRange> findAll() throws DataAccessException, SQLException;	
	ShootingRange findShootingRangeById(int id) throws DataAccessException, SQLException;

}
