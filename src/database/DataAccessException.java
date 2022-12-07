package database;
/**
 * Class for DataAccessException.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class DataAccessException extends Exception {
	public DataAccessException(String message, Exception e) {
		super(message, e);
	}
}
