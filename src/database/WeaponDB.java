package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.Weapon;

public class WeaponDB implements WeaponDBIF {

	private static final String FIND_BY_ID_Q = "select * from weapon where weapon_id = ?";

	private PreparedStatement findByIdPS;

	public WeaponDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
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

	public Weapon buildObject(ResultSet rs) throws DataAccessException {

		Weapon currentWeapon = new Weapon();
		try {
			currentWeapon.setWeaponId(rs.getInt("weaponId"));
			currentWeapon.setWeaponName(rs.getString("weaponName"));
			currentWeapon.setWeaponType(rs.getString("weaponType"));
			currentWeapon.setAmmunitionType(rs.getString("ammunitionType"));
			currentWeapon.setStatus(rs.getBoolean("status"));
			

		} catch (SQLException e) {

			throw new DataAccessException("Could not retrieve weapon", e);
		}
		return currentWeapon;

	}

}