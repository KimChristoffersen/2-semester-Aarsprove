package controller;

import java.sql.SQLException;
import java.util.List;
import database.DataAccessException;
import database.ShootingRangeDB;
import database.ShootingRangeDBIF;
import model.ShootingRange;
/**
 * Class for ShootingRangeController.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class ShootingRangeController {
	private ShootingRangeDBIF shootingRangeDB;
	
	public ShootingRangeController() throws SQLException, DataAccessException {
		shootingRangeDB = new ShootingRangeDB();
	}
	
	public List<ShootingRange> FindAll() throws DataAccessException, SQLException{
		return shootingRangeDB.findAll();
	}
	
	public ShootingRange findById(int shootingRangeid) throws DataAccessException, SQLException {
		return shootingRangeDB.findShootingRangeById(shootingRangeid);
	}
}
