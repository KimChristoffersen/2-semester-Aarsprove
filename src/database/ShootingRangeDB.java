package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Price;
import model.ShootingRange;

public class ShootingRangeDB implements ShootingRangeDBIF {
	
	private PriceDBIF priceDB;

	private static final String FIND_BY_ID_Q = "select s.shootingRange_id, s.status, p.price from ShootingRange s, price p where s.shootingRange_Id = ? and s.shootingRange_Id = p.shootingRange_Id";
	private static final String Find_All_Q = "select shootingRange_Id, status from shootingrange where status = 1";

	private PreparedStatement findByIdPS;
	private PreparedStatement findAll;

	public ShootingRangeDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
		findAll = DBConnection.getInstance().getConnection().prepareStatement(Find_All_Q);
		priceDB = new PriceDB();
	}

	public List<ShootingRange> findAll() throws DataAccessException, SQLException {
		ResultSet rs = null;
		try {
			DBConnection.getInstance().startTransaction();
			rs = findAll.executeQuery();
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException("Could not retrieve any shootingranges", e);
		}
		List<ShootingRange> shootingRanges = buildObjects(rs);
		return shootingRanges;

	}

	public ShootingRange findShootingRangeById(int id) throws DataAccessException, SQLException {
		ShootingRange res = null;
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
			throw new DataAccessException("Could not retrieve shooting range", e);
		}

		return res;
	}

	public ShootingRange buildObject(ResultSet rs) throws DataAccessException {
		ShootingRange currentShootingRange = new ShootingRange();
		try {
			currentShootingRange.setShootingRangeId(rs.getInt("shootingrange_Id"));
			currentShootingRange.setStatus(rs.getBoolean("status"));
			Price price = priceDB.findPriceByShootingRangeId(currentShootingRange.getShootingRangeId());
			currentShootingRange.setPrice(price);
		} catch (SQLException e) {

			throw new DataAccessException("Could not retrieve shooting range", e);
		}
		return currentShootingRange;

	}

	private List<ShootingRange> buildObjects(ResultSet rs) throws SQLException, DataAccessException {
		List<ShootingRange> shootingRanges = new ArrayList<>();
		while (rs.next()) {
			shootingRanges.add(buildObject(rs));
		}
		return shootingRanges;
	}

}