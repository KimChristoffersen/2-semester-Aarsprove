package ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.WeaponController;
import model.*;


public class WeaponTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private List<Weapon> data;
	private WeaponController weaponController;
	private static final String[] NAMES = {"Navn", "Type", "V\u00E5bentype", "Ammunitionstype", "Serienummer"};
	
	public WeaponTableModel(WeaponController weaponController) {
		data = new ArrayList<>();
		this.weaponController = weaponController;
	}
	
	public void setData(List<Weapon> weapons) {
		this.data = weapons;
		super.fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int col) {
		return NAMES[col];
	}
	
	@Override
	public int getRowCount() { 
		return data.size();
	}
 	
	@Override
	public int getColumnCount() {
		return NAMES.length;
	}
	
	public String getValueAt(int row, int col) {
		DecimalFormat dc = new DecimalFormat("0.00");

		Weapon weapon = data.get(row);
		switch(col) {
			case 0: return weapon.getWeaponName();
			case 1: return weapon.getWeaponType();
			case 2: return weapon.getWeaponType();
			case 3: return weapon.getAmmunitionType();
			case 4: return weapon.getWeaponId()+"";
			default: return "Something went odd in the pipe";
		}
	}

	public Weapon getLine(int row) {
		return data.get(row);
	}
}