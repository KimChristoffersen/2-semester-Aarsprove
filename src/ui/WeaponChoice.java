package ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.WeaponController;
import database.DataAccessException;
import model.Weapon;

public class WeaponChoice extends JPanel {

	private MainUI mainUI;
	private JTable table;
	private WeaponController weaponController;

	/**
	 * Create the panel.
	 * 
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	public WeaponChoice(MainUI mainUI) throws SQLException, DataAccessException {
		setBounds(new Rectangle(0, 0, 800, 500));
		this.mainUI = mainUI;

		JButton btnNewButton = new JButton("WEAPONCHOISE");
		btnNewButton.setBounds(0, 0, 0, 0);
		btnNewButton.addActionListener(e -> weaponChoice());
		setLayout(null);
		add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(102, 69, 534, 319);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		init();
	}

	private void init() throws SQLException, DataAccessException {
		weaponController = new WeaponController();
		updateTable();
	}

	private void weaponChoice() {
		mainUI.timeChoice();
	}

	private void updateTable() throws DataAccessException, SQLException {
		WeaponTableModel wtm = new WeaponTableModel(weaponController);
		this.table.setModel(wtm);
		List<Weapon> data = weaponController.getWeapons();
		wtm.setData(data);
		// setTableAlign(); // se kode fra semesterprojekt 1
	}

}
