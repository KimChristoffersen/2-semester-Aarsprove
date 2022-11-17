package database;

import java.sql.ResultSet;


import model.Weapon;

public interface WeaponDBIF {
	
	Weapon buildObject(ResultSet rs) throws DataAccessException;		
}
