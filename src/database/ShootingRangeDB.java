package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Price;
import model.ShootingRange;
/**
 * Class for ShootingRangeDB.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class ShootingRangeDB implements ShootingRangeDBIF {
	private PriceDBIF priceDB;

	private static final String FIND_BY_ID_Q = "select s.shootingRange_id, s.status, p.price from ShootingRange s, price p where s.shootingRange_Id = ? and s.shootingRange_Id = p.shootingRange_Id";
	private static final String Find_All_Q = "select shootingRange_Id, status from shootingrange where status = 1";

	private PreparedStatement findByIdPS;
	private PreparedStatement findAllPS;

	public ShootingRangeDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
		findAllPS = DBConnection.getInstance().getConnection().prepareStatement(Find_All_Q);
		priceDB = new PriceDB();
	}

	public List<ShootingRange> findAll() throws DataAccessException, SQLException {
		ResultSet rs = null;
		try {
			DBConnection.getInstance().startTransaction();
			rs = findAllPS.executeQuery();
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

	private ShootingRange buildObject(ResultSet rs) throws DataAccessException {
		ShootingRange currentShootingRange = null;
		try {
			int shootingRangeId = rs.getInt("shootingrange_Id");
			boolean status = rs.getBoolean("status");
			Price price = priceDB.findPriceByShootingRangeId(shootingRangeId);
			currentShootingRange = new ShootingRange(shootingRangeId, status, price);
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