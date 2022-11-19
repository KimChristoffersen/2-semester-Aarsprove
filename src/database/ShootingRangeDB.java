package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.ShootingRange;

public class ShootingRangeDB implements ShootingRangeDBIF {

	private static final String FIND_BY_ID_Q = "select * from shootingrange where shootingrange_id = ?";

	private PreparedStatement findByIdPS;

	public ShootingRangeDB() throws SQLException, DataAccessException {
		findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
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
			

		} catch (SQLException e) {

			throw new DataAccessException("Could not retrieve shooting range", e);
		}
		return currentShootingRange;

	}

}