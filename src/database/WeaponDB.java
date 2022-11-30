package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Weapon;

public class WeaponDB implements WeaponDBIF {

	private static final String FIND_BY_ID_Q = "select w.weaponid, w.weaponname, wt.weapontype, a.ammunitiontype, status from weapon w, weapontype wt, ammunitiontype a where weaponId = ? and w.weaponType_Id = wt.weaponTypeId and w.ammunitionType_Id = a.AmmunitionTypeId";
	private static final String FIND_ALL_Q = "select w.weaponid, w.weaponname, wt.weapontype, a.ammunitiontype, status from weapon w, weapontype wt, ammunitiontype a where w.weaponType_Id = wt.weaponTypeId and w.ammunitionType_Id = a.AmmunitionTypeId";

	private PreparedStatement findByIdPS;
	private PreparedStatement findAll;

	public WeaponDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
		findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_Q);
	}

	public List<Weapon> findAll() throws DataAccessException, SQLException {
		ResultSet rs;
		try {
			DBConnection.getInstance().startTransaction();
			rs = findAll.executeQuery();
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Could not retrieve any weapons", e);
		}
		List<Weapon> weapons = buildObjects(rs);
		return weapons;
	}

	public Weapon findWeaponById(int id) throws DataAccessException, SQLException {
		Weapon res = null;
		try {
			DBConnection.getInstance().startTransaction();
			findByIdPS.setInt(1, id);
			ResultSet rs = findByIdPS.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Could not retrieve weapon", e);
		}
		return res;
	}

	private Weapon buildObject(ResultSet rs) throws DataAccessException {
		Weapon currentWeapon = new Weapon();
		try {
			currentWeapon.setWeaponId(rs.getInt("weaponId"));
			currentWeapon.setWeaponName(rs.getString("weaponName"));
			currentWeapon.setWeaponType(rs.getString("weaponType"));
			currentWeapon.setAmmunitionType(rs.getString("ammunitionType"));
			currentWeapon.setStatus(rs.getBoolean("status"));
//			boolean weaponStatus = false;
//			if(rs.getInt("status") == 1) weaponStatus = true; 
//			currentWeapon.setStatus(weaponStatus);
			
		} catch (SQLException e) {

			throw new DataAccessException("Could not retrieve weapon", e);
		}
		return currentWeapon;
	}

	private List<Weapon> buildObjects(ResultSet rs) throws SQLException, DataAccessException {
		List<Weapon> weapons = new ArrayList<>();
		while (rs.next()) {
			weapons.add(buildObject(rs));
		}
		return weapons;
	}

}