package ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.sql.SQLException;
import java.util.List;
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
/**
 * Class for WeaponChoice.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class WeaponChoice extends JPanel {

	private MainUI mainUI;
	private JTable table;
	private WeaponController weaponController;
	private JPanel panelCenter;

	/**
	 * Create the panel.
	 * 
	 */
	public WeaponChoice(MainUI mainUI) throws SQLException, DataAccessException {
		setBounds(new Rectangle(0, 0, 800, 500));
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

		JLabel lblChooseWeapon = new JLabel("V\u00E6lg v\u00E5ben");
		lblChooseWeapon.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseWeapon.setForeground(Color.WHITE);
		lblChooseWeapon.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelTop.add(lblChooseWeapon);

		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(Color.WHITE);
		add(panelSouth, BorderLayout.SOUTH);

		JButton btnChoose = new JButton("V\u00E6lg v\u00E5ben");
		btnChoose.addActionListener(e -> {
			try {
				selectWeapon();
			} catch (DataAccessException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		JButton btnAnnullerBooking = new JButton("Annuller booking");
		btnAnnullerBooking.addActionListener(e -> backToStart());
		btnAnnullerBooking.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSouth.add(btnAnnullerBooking);
		btnChoose.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSouth.add(btnChoose);

		init(mainUI);
	}

	private void init(MainUI mainUI) throws SQLException, DataAccessException {
		this.mainUI = mainUI;
		weaponController = new WeaponController();
		updateTable();
	}

	private void selectWeapon() throws DataAccessException, SQLException {
		int selectedWeapon = table.getSelectedRow();
		if (selectedWeapon != -1) {
			int weaponId = Integer.parseInt((String) table.getValueAt(selectedWeapon, 3));
			mainUI.addWeapon(weaponId);
		} else {
			JOptionPane.showMessageDialog(panelCenter, "Intet v\u00E5ben valgt");
		}
	}

	private void updateTable() throws DataAccessException, SQLException {
		WeaponTableModel wtm = new WeaponTableModel(weaponController);
		this.table.setModel(wtm);
		List<Weapon> data = weaponController.findAll();
		wtm.setData(data);
		setTableAlign();
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
	
	private void backToStart() {
		mainUI.backToStart();
	}
}
