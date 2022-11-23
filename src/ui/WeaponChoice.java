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
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

public class WeaponChoice extends JPanel {

	private MainUI mainUI;
	private JTable table;
	private WeaponController weaponController;
	private JPanel panelCenter;

	/**
	 * Create the panel.
	 * 
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	public WeaponChoice(MainUI mainUI) throws SQLException, DataAccessException {
		setBounds(new Rectangle(0, 0, 800, 500));
		this.mainUI = mainUI;
		setLayout(new BorderLayout(0, 0));

		panelCenter = new JPanel();
		panelCenter.setBorder(new LineBorder(Color.WHITE, 10));
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		panelCenter.add(scrollPane);

		table = new JTable();
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		scrollPane.setViewportView(table);

		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.GRAY);
		add(panelTop, BorderLayout.NORTH);

		JLabel lblChooseWeapon = new JLabel("Vælg våben");
		lblChooseWeapon.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseWeapon.setForeground(Color.WHITE);
		lblChooseWeapon.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelTop.add(lblChooseWeapon);

		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(Color.WHITE);
		add(panelSouth, BorderLayout.SOUTH);

		JButton btnChoose = new JButton("Ok");
		btnChoose.addActionListener(e -> {
			try {
				selectWeapon();
			} catch (DataAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnChoose.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSouth.add(btnChoose);

		init();
	}

	private void selectWeapon() throws DataAccessException, SQLException {
		int selectedWeapon = table.getSelectedRow();
		if (selectedWeapon != -1) {
			mainUI.timeChoice();
			updateTable();
		} else {
			JOptionPane.showMessageDialog(panelCenter, "Intet våben valgt");
		}
	}

	private void init() throws SQLException, DataAccessException {
		weaponController = new WeaponController();
		updateTable();
	}

	private void updateTable() throws DataAccessException, SQLException {
		WeaponTableModel wtm = new WeaponTableModel(weaponController);
		this.table.setModel(wtm);
		List<Weapon> data = weaponController.getWeapons();
		wtm.setData(data);
		setTableAlign(); // se kode fra semesterprojekt 1
	}

	private void setTableAlign() {
		DefaultTableCellRenderer headerLeftRenderer = new DefaultTableCellRenderer();
		headerLeftRenderer.setHorizontalAlignment(JLabel.LEFT);
		headerLeftRenderer.setBackground(new Color(0, 94, 150));
		headerLeftRenderer.setForeground(Color.white);

		table.getColumnModel().getColumn(0).setHeaderRenderer(headerLeftRenderer);
		table.getColumnModel().getColumn(1).setHeaderRenderer(headerLeftRenderer);
		table.getColumnModel().getColumn(2).setHeaderRenderer(headerLeftRenderer);
		table.getColumnModel().getColumn(3).setHeaderRenderer(headerLeftRenderer);

		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);

		table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
	}

}
