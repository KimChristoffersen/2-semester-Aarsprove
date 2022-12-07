package database;

import java.sql.SQLException;
import java.util.List;

import model.Weapon;
/**
 * Interface for WeaponDB.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public interface WeaponDBIF {
	Weapon findWeaponById(int id) throws DataAccessException, SQLException;
	List<Weapon> findAll() throws DataAccessException, SQLException;
}
