package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Price;
/**
 * Class for PriceDB.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class PriceDB implements PriceDBIF {
	private static final String FIND_PRICE_BY_WEAPONID_Q = "select startdate, price from price where weapon_Id = ? and startdate = (select max(startdate) from price where weapon_Id = ?)";
	private static final String FIND_PRICE_BY_SHOOTINGRANGEID_Q = "select startdate, price from price where shootingRange_Id = ? and startdate = (select max(startdate) from price where shootingRange_Id = ?)";
	private static final String FIND_PRICE_BY_INSTRUCTOR_ID_Q = "select startdate, price from price where instructor_Id = ? and startdate = (select max(startdate) from price where instructor_Id = ?)";
	
	private PreparedStatement findPriceByWeaponIdPS;
	private PreparedStatement findPriceByShootingrangeIdPS;
	private PreparedStatement findPriceByInstructorIdPS;

	public PriceDB() throws SQLException, DataAccessException {
		findPriceByWeaponIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_PRICE_BY_WEAPONID_Q);
		findPriceByShootingrangeIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_PRICE_BY_SHOOTINGRANGEID_Q);
		findPriceByInstructorIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_PRICE_BY_INSTRUCTOR_ID_Q);
	} 
	
	private Price buildObject(ResultSet rs) throws DataAccessException {
        Price currentPrice = null;
        try {
            LocalDate startDate = rs.getDate("startDate").toLocalDate();
            double price = rs.getDouble("price");
            currentPrice = new Price(startDate, price);
        } catch (SQLException e) {
            throw new DataAccessException("Could not retrieve price", e);
        }
        return currentPrice;
    }

	public Price findPriceByWeaponId(int id) throws DataAccessException {
		ResultSet rs = null;
		Price price = null;
		try {
			DBConnection.getInstance().startTransaction();
			findPriceByWeaponIdPS.setInt(1, id);
			findPriceByWeaponIdPS.setInt(2, id);
			rs = findPriceByWeaponIdPS.executeQuery();
			if (rs.next()) {
				price = buildObject(rs);	
			}
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Could not retrieve weapon price", e);
		}
		return price;
	}
	
	public Price findPriceByShootingRangeId(int id) throws DataAccessException {
		ResultSet rs = null;
		Price price = null;
		try {
			DBConnection.getInstance().startTransaction();
			findPriceByShootingrangeIdPS.setInt(1, id);
			findPriceByShootingrangeIdPS.setInt(2, id);
			rs = findPriceByShootingrangeIdPS.executeQuery();
			if (rs.next()) {
				price = buildObject(rs);
			}
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Could not retrieve shooting range price", e);
		}
		return price;
	}

	@Override
	public Price findPriceByInstructorID(int id) throws DataAccessException {
		ResultSet rs = null;
		Price price = null;
		try {
			DBConnection.getInstance().startTransaction();
			findPriceByInstructorIdPS.setInt(1, id);
			findPriceByInstructorIdPS.setInt(2, id);
			rs = findPriceByInstructorIdPS.executeQuery();
			if (rs.next()) {
				price = buildObject(rs);
			}
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Could not retrieve Instructor price", e);
		}
		return price;
	}
}