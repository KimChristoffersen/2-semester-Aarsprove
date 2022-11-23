package controller;

import java.sql.SQLException;
import java.util.List;

import database.DataAccessException;
import database.WeaponDB;
import database.WeaponDBIF;
import model.Weapon;

public class WeaponController {
	
	private WeaponDBIF weaponDB;
	
	public WeaponController() throws SQLException, DataAccessException  {
		weaponDB = new WeaponDB();
	}
	
	public List<Weapon> getWeapons() throws DataAccessException, SQLException{
		return weaponDB.findAll();
	}
}
