package database;

import java.sql.ResultSet;


import model.Price;
/**
 * Interface for PriceDB.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public interface PriceDBIF {
	Price findPriceByWeaponId(int id) throws DataAccessException;
	Price findPriceByShootingRangeId(int id) throws DataAccessException;
	Price findPriceByInstructorID(int id) throws DataAccessException;
		
}
