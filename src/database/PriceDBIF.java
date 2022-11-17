package database;

import java.sql.ResultSet;


import model.Price;

public interface PriceDBIF {
	
	Price buildObject(ResultSet rs) throws DataAccessException;		
}
