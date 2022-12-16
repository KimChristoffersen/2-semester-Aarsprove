package controller;

import java.sql.SQLException;
import java.util.List;
import database.DataAccessException;
import database.WeaponDB;
import database.WeaponDBIF;
import model.Weapon;

/**
 * Class for WeaponController.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class WeaponController {
	private WeaponDBIF weaponDB;
	
	public WeaponController() throws SQLException, DataAccessException  {
		weaponDB = new WeaponDB();
	}
	
	public List<Weapon> findAll() throws DataAccessException, SQLException{
		return weaponDB.findAll();
	}

	public Weapon findById(int weaponId) throws DataAccessException, SQLException {
		return weaponDB.findWeaponById(weaponId);
	}
}
