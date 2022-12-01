package database;

import java.sql.ResultSet;


import model.Price;

public interface PriceDBIF {
	
	Price findPriceByWeaponId(int id) throws DataAccessException;
		
}
