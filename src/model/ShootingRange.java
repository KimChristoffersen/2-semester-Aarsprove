package model;
/**
 * Class for ShootingRange model.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class ShootingRange {
	private int shootingRangeId;
	private boolean status;
	private Price price;
	
	public ShootingRange() {
	}
	
	public ShootingRange(int shootingRangeId, boolean status, Price price) {
		this.shootingRangeId = shootingRangeId;
		this.status = status;
		this.price = price;
	}

	//GETTERS
	public int getShootingRangeId() {
		return shootingRangeId;
	}

	public boolean getStatus() {
		return status;
	}
	
	public Price getPrice() {
		return price;
	}

	//SETTERS
	public void setShootingRangeId(int shootingRangeId) {
		this.shootingRangeId = shootingRangeId;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setPrice(Price price) {
		this.price = price;
	}
}