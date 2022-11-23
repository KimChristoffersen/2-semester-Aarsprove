package database;

import java.sql.SQLException;
import java.util.List;

import model.Weapon;

public interface WeaponDBIF {
	Weapon findWeaponById(int id) throws DataAccessException, SQLException;
	List<Weapon> findAll() throws DataAccessException, SQLException;
}
