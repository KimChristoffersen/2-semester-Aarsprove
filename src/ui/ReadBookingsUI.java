package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import controller.BookingController;
import database.DataAccessException;
import model.Booking;
import javax.swing.JTable;


public class ReadBookingsUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private BookingController bookingController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadBookingsUI frame = new ReadBookingsUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReadBookingsUI() throws SQLException, DataAccessException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/images/programIcon.gif")));
		setTitle("\u00D8STJYSK V\u00C5BENHANDEL BOOKINGSYSTEM");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 426, 263);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		scrollPane.setViewportView(table);
		
		init();
		
		
	}
	private void updateTable() throws DataAccessException{
		ReadBookingsTableModel rtm = new ReadBookingsTableModel();
		this.table.setModel(rtm);
		List<Booking> data = bookingController.readBookings(1);
		rtm.setData(data);
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
	private void init() throws SQLException, DataAccessException {
		bookingController = new BookingController();
		updateTable();
	}
}
