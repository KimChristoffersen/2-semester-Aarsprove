package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {
	private Connection connection = null;
	private static DBConnection dbConnection;

	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String dbName = "DMA-CSD-V221_10434668";
	private static final String serverAddress = "hildur.ucn.dk";
	private static final int serverPort = 1433;
	private static final String userName = "DMA-CSD-V221_10434668";
	private static final String password = "Password1!";
	private static final String encryption = "encrypt=false";

	private DBConnection() throws DataAccessException {

		String connectionString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s;encrypt=false",
				serverAddress, serverPort, dbName, userName, password, encryption);
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			throw new DataAccessException("Missing JDBC driver", e);
			

		} catch (SQLException e) {
			throw new DataAccessException(String.format("Could not connect to database %s@%s:%d user %s", dbName,
					serverAddress, serverPort, userName), e);
			
		}
	}

	public static synchronized DBConnection getInstance() throws DataAccessException {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	
	
	public void startTransaction() throws DataAccessException {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DataAccessException("Could not start transaction.", e);
		}
	}

	public void commitTransaction() throws DataAccessException {
		try {
			try {
				connection.commit();
			} catch (SQLException e) {
				throw e;
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not commit transaction", e);
		}
	}

	public void rollbackTransaction() throws DataAccessException {
		try {
			try {
				connection.rollback();
			} catch (SQLException e) {
				throw e;
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not rollback transaction", e);
		}
	}



	public Connection getConnection() {
		return connection;
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	INSERT WITH IDENTITY
//	public int executeInsertWithIdentity(String sql) throws DataAccessException {
//		System.out.println("DBConnection, Inserting: " + sql);
//		int res = -1;
//		try (Statement s = connection.createStatement()) {
//			res = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
//			if (res > 0) {
//				ResultSet rs = s.getGeneratedKeys();
//				rs.next();
//				res = rs.getInt(1);
//			}
//
//		} catch (SQLException e) {
//			
//			throw new DataAccessException("Could not execute insert (" + sql + ").", e);
//		}
//		return res;
//	}
//
	public int executeInsertWithIdentity(PreparedStatement ps) throws DataAccessException {
		int res = -1;
		try {
			res = ps.executeUpdate();
			if (res > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
		} catch (SQLException e) {

			throw new DataAccessException("Could not execute insert", e);
		}
		return res;
	}
}