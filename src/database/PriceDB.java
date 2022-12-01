package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.Price;

public class PriceDB implements PriceDBIF {

	private static final String FIND_BY_ID_Q = "select * from price where price_id = ?";
	private static final String FIND_PRICE_BY_WEAPONID_Q = "select * from price where weapon_Id = ?";
	
	

	private PreparedStatement findByIdPS;
	private PreparedStatement findPriceByWeaponIdPS;

	public PriceDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
		findPriceByWeaponIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_PRICE_BY_WEAPONID_Q);
	} 
	
	

	public Price findPriceById(int id) throws DataAccessException, SQLException {
		Price res = null;
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
			throw new DataAccessException("Could not retrieve price", e);
		}

		return res;
	}

	public Price buildObject(ResultSet rs) throws DataAccessException {

		Price currentPrice = new Price();
		try {
			currentPrice.setStartDate(rs.getDate("startDate").toLocalDate());
			currentPrice.setPrice(rs.getDouble("price"));
			
			

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
			rs = findPriceByWeaponIdPS.executeQuery();
			if (rs.next()) {
				price = buildObject(rs);
				
			}
			DBConnection.getInstance().commitTransaction();

		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Could not retrieve price", e);
		}

		return price;
	}
	
	
	
}