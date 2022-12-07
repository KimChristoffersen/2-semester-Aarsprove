package model;
/**
 * Class for Weapon model.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class Weapon {
	private int weaponId;
	private String weaponName;
	private String weaponType;
	private String ammunitionType;
	private boolean status;
	private Price price;

	public Weapon() {
	}

	public Weapon(int weaponId, String weaponName, String weaponType, String ammunitionType, boolean status, Price price) {
		this.weaponId = weaponId;
		this.weaponName = weaponName;
		this.weaponType = weaponType;
		this.ammunitionType = ammunitionType;
		this.status = status;
		this.price = price;
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
	
	public Price getPrice() {
		return price;
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
	
	public void setPrice(Price price) {
		this.price = price;
	}
}