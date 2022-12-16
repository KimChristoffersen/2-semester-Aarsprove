package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.BookingController;
import database.DataAccessException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
/**
 * Class for MainUI.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class MainUI extends JFrame {
	private JPanel contentPane;
	private Start start;
	private WeaponChoice weaponChoice;
	private JPanel panelCenterStart;
	private JPanel panelCenterWeapon;
	private TimeChoice timeChoice;
	private JPanel panelCenterTime;
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
	 */
	public MainUI() throws SQLException, DataAccessException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/images/programIcon.gif")));
		setTitle("\u00D8STJYSK V\u00C5BENHANDEL BOOKINGSYSTEM");
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
		// call to controller that creates booking
		bookingController.createBooking(2); // hardcoded customer
		// creates ui elements
		panelCenterStart.hide();
		panelCenterWeapon = new JPanel();
		contentPane.add(panelCenterWeapon, BorderLayout.CENTER);
		panelCenterWeapon.setLayout(new GridLayout(1, 0, 0, 0));
		weaponChoice = new WeaponChoice(this);
		panelCenterWeapon.add(weaponChoice, BorderLayout.CENTER);
	}

	public void addWeapon(int weaponId) throws DataAccessException, SQLException {
		// call to controller that adds weapon to current booking
		bookingController.addWeapon(weaponId);
		// creates ui elemets
		panelCenterWeapon.hide();
		panelCenterTime = new JPanel();
		contentPane.add(panelCenterTime, BorderLayout.CENTER);
		panelCenterTime.setLayout(new GridLayout(1, 0, 0, 0));
		timeChoice = new TimeChoice(this, bookingController);
		panelCenterTime.add(timeChoice, BorderLayout.CENTER);
	}

	public void gotoBookingConfirmation(LocalDate date, int time, int shootingRange, int instructor) throws DataAccessException, SQLException {
		// call to controller that sets date, time, instructor and shootingrange to current booking
		bookingController.setTimeSlot(date, time, instructor, shootingRange);
		// creates ui elements
		panelCenterTime.hide();
		panelCenterBooking = new JPanel();
		contentPane.add(panelCenterBooking, BorderLayout.CENTER);
		panelCenterBooking.setLayout(new GridLayout(1, 0, 0, 0));
		bookingConfirmation = new BookingConfirmation(this, bookingController);
		panelCenterBooking.add(bookingConfirmation, BorderLayout.CENTER);
	}

	public void backToStart() {
		// shows start
		panelCenterStart.show();
		// hides currently shown panels
		if(panelCenterBooking != null) panelCenterBooking.hide();
		if(panelCenterWeapon != null) panelCenterWeapon.hide();
		if(panelCenterTime != null) panelCenterTime.hide();
	}
}