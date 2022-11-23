package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BookingController;
import database.DataAccessException;
import model.Customer;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainUI extends JFrame {

	private JPanel contentPane;
	private Start start;
	private WeaponChoice weaponChoice;
	private JPanel panelCenterStart;
	private JPanel panelCenterWeapon;
	private TimeChoice timeChoice;
	private JPanel panelCenterTime;
	private JButton btnStatusWeapon;
	private JButton btnStatusTime;
	private JButton btnStatusConfirmation;
	private BookingConfirmation bookingConfirmation;
	private JPanel panelCenterBooking;
	private JPanel panelSouth;
	private BookingController bookingController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws DataAccessException 
	 * @throws SQLException 
	 */
	public MainUI() throws SQLException, DataAccessException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorth = new JPanel();
		panelNorth.setPreferredSize(new Dimension(10, 50));
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("\u00D8STJYSK V\u00C5BENHANDEL BOOKINGSYSTEM");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelNorth.add(lblNewLabel);

		panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);

		btnStatusWeapon = new JButton("V\u00E5benvalg");
		btnStatusWeapon.setPreferredSize(new Dimension(180, 40));
		btnStatusWeapon.setFont(new Font("Tahoma", Font.BOLD, 18));

		btnStatusTime = new JButton("Tidsvalg");
		btnStatusTime.setPreferredSize(new Dimension(180, 40));
		btnStatusTime.setFont(new Font("Tahoma", Font.BOLD, 18));

		btnStatusConfirmation = new JButton("Godkend");
		btnStatusConfirmation.setPreferredSize(new Dimension(180, 40));
		btnStatusConfirmation.setFont(new Font("Tahoma", Font.BOLD, 18));

		panelCenterStart = new JPanel();
		contentPane.add(panelCenterStart, BorderLayout.CENTER);
		panelCenterStart.setLayout(new GridLayout(1, 0, 0, 0));

		start = new Start(this);
		panelCenterStart.add(start, BorderLayout.CENTER);
		
		init();
	}

	private void init() throws SQLException, DataAccessException {
		bookingController = new BookingController();
		
	}

	public void createBookingWithWeaponAndInstructor() throws SQLException, DataAccessException {
		//
		panelSouth.setLayout(new GridLayout(0, 3, 10, 10));
		panelSouth.add(btnStatusWeapon);
		panelSouth.add(btnStatusTime);
		panelSouth.add(btnStatusConfirmation);

		panelCenterStart.hide();
		panelCenterWeapon = new JPanel();
		contentPane.add(panelCenterWeapon, BorderLayout.CENTER);
		panelCenterWeapon.setLayout(new GridLayout(1, 0, 0, 0));
		weaponChoice = new WeaponChoice(this);
		panelCenterWeapon.add(weaponChoice, BorderLayout.CENTER);
		btnStatusWeapon.setBackground(Color.GREEN);
		
		Customer customer = new Customer();	// HARDCODE CUSTOMER
		customer.setFirstName("Lasse");		// MAYBE REMOVE??
		bookingController.createBooking(customer);	
	}

	public void addWeapon(int weaponId) throws DataAccessException, SQLException {
		panelCenterWeapon.hide();
		panelCenterTime = new JPanel();
		contentPane.add(panelCenterTime, BorderLayout.CENTER);
		panelCenterTime.setLayout(new GridLayout(1, 0, 0, 0));
		timeChoice = new TimeChoice(this);
		panelCenterTime.add(timeChoice, BorderLayout.CENTER);
		btnStatusWeapon.setEnabled(true);
		btnStatusTime.setBackground(Color.GREEN);

		bookingController.addWeapon(weaponId);
	}

	public void bookingConfirmation() {
		panelCenterTime.disable();
		panelCenterBooking = new JPanel();
		contentPane.add(panelCenterBooking, BorderLayout.CENTER);
		panelCenterBooking.setLayout(new GridLayout(1, 0, 0, 0));
		bookingConfirmation = new BookingConfirmation(this);
		panelCenterBooking.add(bookingConfirmation, BorderLayout.CENTER);
		btnStatusTime.setEnabled(true);
		btnStatusConfirmation.setBackground(Color.GREEN);
	}
}
