package model;

public class ShootingRange {
	private String shootingRangeId;
	private boolean status;
	
	public ShootingRange(String shootingRangeId, boolean status) {
		this.shootingRangeId = shootingRangeId;
		this.status = status;
	}

	
	//GETTERS
	public String getShootingRangeId() {
		return shootingRangeId;
	}

	public boolean getStatus() {
		return status;
	}

	//SETTERS
	public void setShootingRangeId(String shootingRangeId) {
		this.shootingRangeId = shootingRangeId;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
