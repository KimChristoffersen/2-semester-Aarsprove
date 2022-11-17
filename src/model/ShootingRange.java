package model;

public class ShootingRange {
	private int shootingRangeId;
	private boolean status;
	
	public ShootingRange(int shootingRangeId, boolean status) {
		this.shootingRangeId = shootingRangeId;
		this.status = status;
	}

	
	//GETTERS
	public int getShootingRangeId() {
		return shootingRangeId;
	}

	public boolean getStatus() {
		return status;
	}

	//SETTERS
	public void setShootingRangeId(int shootingRangeId) {
		this.shootingRangeId = shootingRangeId;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
