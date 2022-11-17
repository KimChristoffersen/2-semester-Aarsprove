package model;

public class Weapon {

	private int weaponId;
	private String weaponName;
	private String weaponType;
	private String ammunitionType;
	private boolean status;

	public Weapon() {
	}

	public Weapon(int weaponId, String weaponName, String weaponType, String ammunitionType, boolean status) {
		this.weaponId = weaponId;
		this.weaponName = weaponName;
		this.weaponType = weaponType;
		this.ammunitionType = ammunitionType;
		this.status = status;
	}

//GETTERS
	public int getWeaponId() {
		return weaponId;
	}

	public String getWeaponName() {
		return weaponName;
	}

	public String getWeaponType() {
		return weaponType;
	}

	public String getAmmunitionType() {
		return ammunitionType;
	}

	public boolean getStatus() {
		return status;
	}

//SETTERS
	public void setWeaponId(int weaponId) {
		this.weaponId = weaponId;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}

	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	public void setAmmunitionType(String ammunitionType) {
		this.ammunitionType = ammunitionType;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
