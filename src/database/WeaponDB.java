package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Price;
import model.Weapon;
/**
 * Class for WeaponDB.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class WeaponDB implements WeaponDBIF {
	private PriceDBIF priceDB;

	private static final String FIND_BY_ID_Q = "select w.weaponid, w.weaponname, wt.weapontype, a.ammunitiontype, status from weapon w, weapontype wt, ammunitiontype a where weaponId = ? and w.weaponType_Id = wt.weaponTypeId and w.ammunitionType_Id = a.AmmunitionTypeId";
	private static final String FIND_ALL_Q = "select w.weaponid, w.weaponname, wt.weapontype, a.ammunitiontype, status from weapon w, weapontype wt, ammunitiontype a where w.weaponType_Id = wt.weaponTypeId and w.ammunitionType_Id = a.AmmunitionTypeId and w.status = 1";

	private PreparedStatement findByIdPS;
	private PreparedStatement findAllPS;

	public WeaponDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
		findAllPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_Q);
		priceDB = new PriceDB();
	}

	public List<Weapon> findAll() throws DataAccessException, SQLException {
		ResultSet rs;
		try {
			DBConnection.getInstance().startTransaction();
			rs = findAllPS.executeQuery();
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
        Weapon currentWeapon = null;
        try {
            int weaponId = rs.getInt("weaponId");
            String weaponName = rs.getString("weaponName");
            String weaponType = rs.getString("weaponType");
            String ammunitionType = rs.getString("ammunitionType");
            boolean status = rs.getBoolean("status");
            Price price = priceDB.findPriceByWeaponId(weaponId);
            currentWeapon = new Weapon(weaponId, weaponName, weaponType, ammunitionType, status, price);
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