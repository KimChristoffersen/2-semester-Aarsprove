package ui;
/**
 * Class for BookingConfirmation.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.BookingController;
import database.DataAccessException;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class BookingConfirmation extends JPanel {
	private MainUI mainUI;
	private BookingController bookingController;
	private Color blueColor;
	private JTextField textFieldBookingShootingRange;
	private JTextField txtFieldRessourceName;
	private JTextField txtFieldPrice;
	private JTextField txtFieldShootingRange;
	private JTextField txtFieldShootingRangePrice;
	private JTextField textFieldInstructor;
	private JTextField textFieldWeapon;
	private JTextField textFieldTotal;
	private JTextField textFieldInstructorPrice;
	private JTextField textFieldWeaponPrice;
	private JTextField textFieldTotalPrice;
	private JTextField textFieldCustomerName;
	private JTextField textFieldBookingInstructor;
	private JTextField textFieldBookingWeapon;
	private JTextField textFieldCustomerPhone;
	private JTextField textFieldCustomerEmail;
	private JTextField textFieldCustomerNumber;
	private JTextField textFieldBookingDateTimeInfo;
	private DateTimeFormatter dayMontFormat;
	private DecimalFormat twoDecimals;

	/**
	 * Create the panel.
	 */
	public BookingConfirmation(MainUI mainUI, BookingController bookingController) {
		blueColor = new Color(0, 94, 150);
		setBounds(new Rectangle(0, 0, 800, 500));
		setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.GRAY);
		add(panelTop, BorderLayout.NORTH);

		JLabel lblBookingConfirmation = new JLabel("Bekr\u00E6ft booking");
		lblBookingConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookingConfirmation.setForeground(Color.WHITE);
		lblBookingConfirmation.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelTop.add(lblBookingConfirmation);

		JPanel panelCenter = new JPanel();
		panelCenter.setBorder(new LineBorder(new Color(255, 255, 255), 10));
		panelCenter.setBackground(Color.WHITE);
		add(panelCenter);
		panelCenter.setLayout(new GridLayout(0, 1, 0, 10));

		JPanel panelBooking = new JPanel();
		panelBooking.setBackground(Color.WHITE);
		panelCenter.add(panelBooking);
		panelBooking.setLayout(new GridLayout(1, 0, 10, 10));

		JPanel panelBookingInfo = new JPanel();
		panelBooking.add(panelBookingInfo);
		SpringLayout sl_panelBookingInfo = new SpringLayout();
		panelBookingInfo.setLayout(sl_panelBookingInfo);

		JPanel panelBookingInfoHeader = new JPanel();
		sl_panelBookingInfo.putConstraint(SpringLayout.NORTH, panelBookingInfoHeader, 0, SpringLayout.NORTH,
				panelBookingInfo);
		sl_panelBookingInfo.putConstraint(SpringLayout.WEST, panelBookingInfoHeader, 0, SpringLayout.WEST,
				panelBookingInfo);
		sl_panelBookingInfo.putConstraint(SpringLayout.SOUTH, panelBookingInfoHeader, 30, SpringLayout.NORTH,
				panelBookingInfo);
		sl_panelBookingInfo.putConstraint(SpringLayout.EAST, panelBookingInfoHeader, 0, SpringLayout.EAST,
				panelBookingInfo);
		panelBookingInfoHeader.setBackground(blueColor);
		panelBookingInfo.add(panelBookingInfoHeader);
		panelBookingInfoHeader.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblBookingInfo = new JLabel("Booking info");
		lblBookingInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookingInfo.setForeground(Color.WHITE);
		lblBookingInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelBookingInfoHeader.add(lblBookingInfo);

		JLabel label = new JLabel("New label");
		sl_panelBookingInfo.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, panelBookingInfo);
		sl_panelBookingInfo.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, panelBookingInfo);
		panelBookingInfo.add(label);

		JLabel lblShootingRangeNumber = new JLabel("Skydebane nr.");
		sl_panelBookingInfo.putConstraint(SpringLayout.NORTH, lblShootingRangeNumber, 44, SpringLayout.NORTH, panelBookingInfo);
		sl_panelBookingInfo.putConstraint(SpringLayout.WEST, lblShootingRangeNumber, 15, SpringLayout.WEST,
				panelBookingInfo);
		sl_panelBookingInfo.putConstraint(SpringLayout.EAST, lblShootingRangeNumber, 130, SpringLayout.WEST,
				panelBookingInfo);
		lblShootingRangeNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelBookingInfo.add(lblShootingRangeNumber);

		textFieldBookingShootingRange = new JTextField();
		sl_panelBookingInfo.putConstraint(SpringLayout.NORTH, textFieldBookingShootingRange, 42, SpringLayout.NORTH, panelBookingInfo);
		sl_panelBookingInfo.putConstraint(SpringLayout.WEST, textFieldBookingShootingRange, 140, SpringLayout.WEST,
				panelBookingInfo);
		sl_panelBookingInfo.putConstraint(SpringLayout.EAST, textFieldBookingShootingRange, -15, SpringLayout.EAST,
				panelBookingInfo);
		textFieldBookingShootingRange.setBackground(new Color(255, 255, 255));
		textFieldBookingShootingRange.setEditable(false);
		textFieldBookingShootingRange.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldBookingShootingRange.setColumns(10);
		panelBookingInfo.add(textFieldBookingShootingRange);

		JLabel lblInstruktorName = new JLabel("Instrukt\u00F8r navn");
		sl_panelBookingInfo.putConstraint(SpringLayout.NORTH, lblInstruktorName, 15, SpringLayout.SOUTH,
				lblShootingRangeNumber);
		sl_panelBookingInfo.putConstraint(SpringLayout.WEST, lblInstruktorName, 15, SpringLayout.WEST, panelBookingInfo);
		lblInstruktorName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelBookingInfo.add(lblInstruktorName);

		JLabel lblWeapon = new JLabel("V\u00E5ben");
		sl_panelBookingInfo.putConstraint(SpringLayout.NORTH, lblWeapon, 15, SpringLayout.SOUTH, lblInstruktorName);
		sl_panelBookingInfo.putConstraint(SpringLayout.WEST, lblWeapon, 15, SpringLayout.WEST, panelBookingInfo);
		lblWeapon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelBookingInfo.add(lblWeapon);

		textFieldBookingInstructor = new JTextField();
		sl_panelBookingInfo.putConstraint(SpringLayout.NORTH, textFieldBookingInstructor, 10, SpringLayout.SOUTH,
				textFieldBookingShootingRange);
		sl_panelBookingInfo.putConstraint(SpringLayout.WEST, textFieldBookingInstructor, 140, SpringLayout.WEST,
				panelBookingInfo);
		sl_panelBookingInfo.putConstraint(SpringLayout.EAST, textFieldBookingInstructor, -15, SpringLayout.EAST,
				panelBookingInfo);
		textFieldBookingInstructor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldBookingInstructor.setEditable(false);
		textFieldBookingInstructor.setColumns(10);
		textFieldBookingInstructor.setBackground(Color.WHITE);
		panelBookingInfo.add(textFieldBookingInstructor);

		textFieldBookingWeapon = new JTextField();
		sl_panelBookingInfo.putConstraint(SpringLayout.NORTH, textFieldBookingWeapon, 10, SpringLayout.SOUTH,
				textFieldBookingInstructor);
		sl_panelBookingInfo.putConstraint(SpringLayout.WEST, textFieldBookingWeapon, 140, SpringLayout.WEST,
				panelBookingInfo);
		sl_panelBookingInfo.putConstraint(SpringLayout.EAST, textFieldBookingWeapon, -15, SpringLayout.EAST,
				panelBookingInfo);
		textFieldBookingWeapon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldBookingWeapon.setEditable(false);
		textFieldBookingWeapon.setColumns(10);
		textFieldBookingWeapon.setBackground(Color.WHITE);
		panelBookingInfo.add(textFieldBookingWeapon);

		JLabel lblDateTime = new JLabel("Dato tid");
		sl_panelBookingInfo.putConstraint(SpringLayout.NORTH, lblDateTime, 15, SpringLayout.SOUTH, lblWeapon);
		sl_panelBookingInfo.putConstraint(SpringLayout.WEST, lblDateTime, 15, SpringLayout.WEST, panelBookingInfo);
		lblDateTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelBookingInfo.add(lblDateTime);

		textFieldBookingDateTimeInfo = new JTextField();
		sl_panelBookingInfo.putConstraint(SpringLayout.NORTH, textFieldBookingDateTimeInfo, 6, SpringLayout.SOUTH,
				textFieldBookingWeapon);
		sl_panelBookingInfo.putConstraint(SpringLayout.WEST, textFieldBookingDateTimeInfo, 140, SpringLayout.WEST,
				panelBookingInfo);
		sl_panelBookingInfo.putConstraint(SpringLayout.EAST, textFieldBookingDateTimeInfo, -15, SpringLayout.EAST,
				panelBookingInfo);
		textFieldBookingDateTimeInfo.setText((String) null);
		textFieldBookingDateTimeInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldBookingDateTimeInfo.setEditable(false);
		textFieldBookingDateTimeInfo.setColumns(10);
		textFieldBookingDateTimeInfo.setBackground(Color.WHITE);
		panelBookingInfo.add(textFieldBookingDateTimeInfo);

		JPanel panelCustomerInfo = new JPanel();
		panelBooking.add(panelCustomerInfo);
		SpringLayout sl_panelCustomerInfo = new SpringLayout();
		panelCustomerInfo.setLayout(sl_panelCustomerInfo);

		JPanel panelCustomerInfoHeader = new JPanel();
		sl_panelCustomerInfo.putConstraint(SpringLayout.NORTH, panelCustomerInfoHeader, 0, SpringLayout.NORTH,
				panelCustomerInfo);
		sl_panelCustomerInfo.putConstraint(SpringLayout.WEST, panelCustomerInfoHeader, 0, SpringLayout.WEST,
				panelCustomerInfo);
		sl_panelCustomerInfo.putConstraint(SpringLayout.SOUTH, panelCustomerInfoHeader, 30, SpringLayout.NORTH,
				panelCustomerInfo);
		sl_panelCustomerInfo.putConstraint(SpringLayout.EAST, panelCustomerInfoHeader, 0, SpringLayout.EAST,
				panelCustomerInfo);
		panelCustomerInfoHeader.setBackground(blueColor);
		panelCustomerInfo.add(panelCustomerInfoHeader);

		JLabel lblCustomerInfo = new JLabel("Kunde info");
		lblCustomerInfo.setForeground(Color.WHITE);
		lblCustomerInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelCustomerInfoHeader.add(lblCustomerInfo);

		JLabel lblCustomerName = new JLabel("Navn");
		sl_panelCustomerInfo.putConstraint(SpringLayout.NORTH, lblCustomerName, 42, SpringLayout.NORTH,
				panelCustomerInfo);
		sl_panelCustomerInfo.putConstraint(SpringLayout.WEST, lblCustomerName, 15, SpringLayout.WEST,
				panelCustomerInfo);
		sl_panelCustomerInfo.putConstraint(SpringLayout.EAST, lblCustomerName, 130, SpringLayout.WEST,
				panelCustomerInfo);
		lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelCustomerInfo.add(lblCustomerName);

		textFieldCustomerName = new JTextField();
		sl_panelCustomerInfo.putConstraint(SpringLayout.NORTH, textFieldCustomerName, 39, SpringLayout.NORTH,
				panelCustomerInfo);
		sl_panelCustomerInfo.putConstraint(SpringLayout.WEST, textFieldCustomerName, 140, SpringLayout.WEST,
				panelCustomerInfo);
		sl_panelCustomerInfo.putConstraint(SpringLayout.EAST, textFieldCustomerName, -15, SpringLayout.EAST,
				panelCustomerInfo);
		textFieldCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldCustomerName.setEditable(false);
		textFieldCustomerName.setColumns(10);
		textFieldCustomerName.setBackground(Color.WHITE);
		panelCustomerInfo.add(textFieldCustomerName);

		JLabel lblCustomerPhone = new JLabel("Telefon nr");
		sl_panelCustomerInfo.putConstraint(SpringLayout.NORTH, lblCustomerPhone, 15, SpringLayout.SOUTH,
				lblCustomerName);
		sl_panelCustomerInfo.putConstraint(SpringLayout.WEST, lblCustomerPhone, 15, SpringLayout.WEST,
				panelCustomerInfo);
		lblCustomerPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelCustomerInfo.add(lblCustomerPhone);

		textFieldCustomerPhone = new JTextField();
		sl_panelCustomerInfo.putConstraint(SpringLayout.NORTH, textFieldCustomerPhone, 10, SpringLayout.SOUTH,
				textFieldCustomerName);
		sl_panelCustomerInfo.putConstraint(SpringLayout.WEST, textFieldCustomerPhone, 140, SpringLayout.WEST,
				panelCustomerInfo);
		sl_panelCustomerInfo.putConstraint(SpringLayout.EAST, textFieldCustomerPhone, 0, SpringLayout.EAST,
				textFieldCustomerName);
		textFieldCustomerPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldCustomerPhone.setEditable(false);
		textFieldCustomerPhone.setColumns(10);
		textFieldCustomerPhone.setBackground(Color.WHITE);
		panelCustomerInfo.add(textFieldCustomerPhone);

		JLabel lblCustomerEmail = new JLabel("E-mail");
		sl_panelCustomerInfo.putConstraint(SpringLayout.NORTH, lblCustomerEmail, 15, SpringLayout.SOUTH,
				lblCustomerPhone);
		sl_panelCustomerInfo.putConstraint(SpringLayout.WEST, lblCustomerEmail, 15, SpringLayout.WEST,
				panelCustomerInfo);
		lblCustomerEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelCustomerInfo.add(lblCustomerEmail);

		textFieldCustomerEmail = new JTextField();
		sl_panelCustomerInfo.putConstraint(SpringLayout.NORTH, textFieldCustomerEmail, 10, SpringLayout.SOUTH,
				textFieldCustomerPhone);
		sl_panelCustomerInfo.putConstraint(SpringLayout.WEST, textFieldCustomerEmail, 140, SpringLayout.WEST,
				panelCustomerInfo);
		sl_panelCustomerInfo.putConstraint(SpringLayout.EAST, textFieldCustomerEmail, -15, SpringLayout.EAST,
				panelCustomerInfo);
		textFieldCustomerEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldCustomerEmail.setEditable(false);
		textFieldCustomerEmail.setColumns(10);
		textFieldCustomerEmail.setBackground(Color.WHITE);
		panelCustomerInfo.add(textFieldCustomerEmail);

		JLabel lblCustomerId = new JLabel("Kunde nr.");
		sl_panelCustomerInfo.putConstraint(SpringLayout.NORTH, lblCustomerId, 15, SpringLayout.SOUTH, lblCustomerEmail);
		sl_panelCustomerInfo.putConstraint(SpringLayout.WEST, lblCustomerId, 15, SpringLayout.WEST, panelCustomerInfo);
		sl_panelCustomerInfo.putConstraint(SpringLayout.EAST, lblCustomerId, 0, SpringLayout.EAST, lblCustomerPhone);
		lblCustomerId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelCustomerInfo.add(lblCustomerId);

		textFieldCustomerNumber = new JTextField();
		sl_panelCustomerInfo.putConstraint(SpringLayout.NORTH, textFieldCustomerNumber, 10, SpringLayout.SOUTH,
				textFieldCustomerEmail);
		sl_panelCustomerInfo.putConstraint(SpringLayout.WEST, textFieldCustomerNumber, 140, SpringLayout.WEST,
				panelCustomerInfo);
		sl_panelCustomerInfo.putConstraint(SpringLayout.EAST, textFieldCustomerNumber, -15, SpringLayout.EAST,
				panelCustomerInfo);
		textFieldCustomerNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldCustomerNumber.setEditable(false);
		textFieldCustomerNumber.setColumns(10);
		textFieldCustomerNumber.setBackground(Color.WHITE);
		panelCustomerInfo.add(textFieldCustomerNumber);

		JPanel panelBookingRessources = new JPanel();
		panelCenter.add(panelBookingRessources);
		SpringLayout sl_panelBookingRessources = new SpringLayout();
		panelBookingRessources.setLayout(sl_panelBookingRessources);

		JPanel panelRessourcesHeader = new JPanel();
		sl_panelBookingRessources.putConstraint(SpringLayout.NORTH, panelRessourcesHeader, 0, SpringLayout.NORTH,
				panelBookingRessources);
		sl_panelBookingRessources.putConstraint(SpringLayout.WEST, panelRessourcesHeader, 0, SpringLayout.WEST,
				panelBookingRessources);
		sl_panelBookingRessources.putConstraint(SpringLayout.SOUTH, panelRessourcesHeader, 30, SpringLayout.NORTH,
				panelBookingRessources);
		sl_panelBookingRessources.putConstraint(SpringLayout.EAST, panelRessourcesHeader, 0, SpringLayout.EAST,
				panelBookingRessources);
		panelRessourcesHeader.setBackground(blueColor);
		panelBookingRessources.add(panelRessourcesHeader);

		JLabel lblBookingRessources = new JLabel("Booking ydelser");
		lblBookingRessources.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookingRessources.setForeground(Color.WHITE);
		lblBookingRessources.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelRessourcesHeader.add(lblBookingRessources);

		txtFieldRessourceName = new JTextField();
		txtFieldRessourceName.setRequestFocusEnabled(false);
		sl_panelBookingRessources.putConstraint(SpringLayout.NORTH, txtFieldRessourceName, 45, SpringLayout.NORTH,
				panelBookingRessources);
		sl_panelBookingRessources.putConstraint(SpringLayout.WEST, txtFieldRessourceName, 15, SpringLayout.WEST,
				panelBookingRessources);
		sl_panelBookingRessources.putConstraint(SpringLayout.EAST, txtFieldRessourceName, -100, SpringLayout.EAST,
				panelBookingRessources);
		txtFieldRessourceName.setForeground(Color.WHITE);
		txtFieldRessourceName.setText("Beskrivelse");
		txtFieldRessourceName.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtFieldRessourceName.setEditable(false);
		txtFieldRessourceName.setColumns(10);
		txtFieldRessourceName.setBackground(Color.BLACK);
		panelBookingRessources.add(txtFieldRessourceName);

		txtFieldPrice = new JTextField();
		sl_panelBookingRessources.putConstraint(SpringLayout.WEST, txtFieldPrice, 0, SpringLayout.EAST,
				txtFieldRessourceName);
		txtFieldPrice.setRequestFocusEnabled(false);
		sl_panelBookingRessources.putConstraint(SpringLayout.NORTH, txtFieldPrice, 45, SpringLayout.NORTH,
				panelBookingRessources);
		sl_panelBookingRessources.putConstraint(SpringLayout.EAST, txtFieldPrice, -15, SpringLayout.EAST,
				panelBookingRessources);
		txtFieldPrice.setForeground(Color.WHITE);
		txtFieldPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFieldPrice.setText("Pris");
		txtFieldPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtFieldPrice.setEditable(false);
		txtFieldPrice.setColumns(10);
		txtFieldPrice.setBackground(Color.BLACK);
		panelBookingRessources.add(txtFieldPrice);

		txtFieldShootingRange = new JTextField();
		sl_panelBookingRessources.putConstraint(SpringLayout.NORTH, txtFieldShootingRange, 5, SpringLayout.SOUTH,
				txtFieldRessourceName);
		txtFieldShootingRange.setRequestFocusEnabled(false);
		sl_panelBookingRessources.putConstraint(SpringLayout.WEST, txtFieldShootingRange, 15, SpringLayout.WEST,
				panelBookingRessources);
		sl_panelBookingRessources.putConstraint(SpringLayout.EAST, txtFieldShootingRange, -100, SpringLayout.EAST,
				panelBookingRessources);
		txtFieldShootingRange.setText("Skydebane");
		txtFieldShootingRange.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFieldShootingRange.setEditable(false);
		txtFieldShootingRange.setColumns(10);
		txtFieldShootingRange.setBackground(Color.WHITE);
		panelBookingRessources.add(txtFieldShootingRange);

		txtFieldShootingRangePrice = new JTextField();
		sl_panelBookingRessources.putConstraint(SpringLayout.NORTH, txtFieldShootingRangePrice, 5, SpringLayout.SOUTH,
				txtFieldPrice);
		sl_panelBookingRessources.putConstraint(SpringLayout.WEST, txtFieldShootingRangePrice, 0, SpringLayout.EAST,
				txtFieldShootingRange);
		txtFieldShootingRangePrice.setRequestFocusEnabled(false);
		sl_panelBookingRessources.putConstraint(SpringLayout.EAST, txtFieldShootingRangePrice, -15, SpringLayout.EAST,
				panelBookingRessources);
		txtFieldShootingRangePrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFieldShootingRangePrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFieldShootingRangePrice.setEditable(false);
		txtFieldShootingRangePrice.setColumns(10);
		txtFieldShootingRangePrice.setBackground(Color.WHITE);
		panelBookingRessources.add(txtFieldShootingRangePrice);

		textFieldInstructor = new JTextField();
		sl_panelBookingRessources.putConstraint(SpringLayout.NORTH, textFieldInstructor, 5, SpringLayout.SOUTH,
				txtFieldShootingRange);
		textFieldInstructor.setRequestFocusEnabled(false);
		sl_panelBookingRessources.putConstraint(SpringLayout.WEST, textFieldInstructor, 15, SpringLayout.WEST,
				panelBookingRessources);
		sl_panelBookingRessources.putConstraint(SpringLayout.EAST, textFieldInstructor, -100, SpringLayout.EAST,
				panelBookingRessources);
		textFieldInstructor.setText("Instrukt\u00F8r");
		textFieldInstructor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldInstructor.setEditable(false);
		textFieldInstructor.setColumns(10);
		textFieldInstructor.setBackground(Color.WHITE);
		panelBookingRessources.add(textFieldInstructor);

		textFieldWeapon = new JTextField();
		sl_panelBookingRessources.putConstraint(SpringLayout.NORTH, textFieldWeapon, 5, SpringLayout.SOUTH,
				textFieldInstructor);
		textFieldWeapon.setRequestFocusEnabled(false);
		sl_panelBookingRessources.putConstraint(SpringLayout.WEST, textFieldWeapon, 15, SpringLayout.WEST,
				panelBookingRessources);
		sl_panelBookingRessources.putConstraint(SpringLayout.EAST, textFieldWeapon, -100, SpringLayout.EAST,
				panelBookingRessources);
		textFieldWeapon.setText("V\u00E5ben");
		textFieldWeapon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldWeapon.setEditable(false);
		textFieldWeapon.setColumns(10);
		textFieldWeapon.setBackground(Color.WHITE);
		panelBookingRessources.add(textFieldWeapon);

		textFieldTotal = new JTextField();
		sl_panelBookingRessources.putConstraint(SpringLayout.NORTH, textFieldTotal, 5, SpringLayout.SOUTH,
				textFieldWeapon);
		textFieldTotal.setRequestFocusEnabled(false);
		sl_panelBookingRessources.putConstraint(SpringLayout.WEST, textFieldTotal, 15, SpringLayout.WEST,
				panelBookingRessources);
		sl_panelBookingRessources.putConstraint(SpringLayout.EAST, textFieldTotal, -100, SpringLayout.EAST,
				panelBookingRessources);
		textFieldTotal.setText("I alt");
		textFieldTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldTotal.setEditable(false);
		textFieldTotal.setColumns(10);
		textFieldTotal.setBackground(Color.WHITE);
		panelBookingRessources.add(textFieldTotal);

		textFieldInstructorPrice = new JTextField();
		sl_panelBookingRessources.putConstraint(SpringLayout.NORTH, textFieldInstructorPrice, 5, SpringLayout.SOUTH,
				txtFieldShootingRangePrice);
		sl_panelBookingRessources.putConstraint(SpringLayout.WEST, textFieldInstructorPrice, 0, SpringLayout.EAST,
				textFieldInstructor);
		textFieldInstructorPrice.setRequestFocusEnabled(false);
		sl_panelBookingRessources.putConstraint(SpringLayout.EAST, textFieldInstructorPrice, -15, SpringLayout.EAST,
				panelBookingRessources);
		textFieldInstructorPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldInstructorPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldInstructorPrice.setEditable(false);
		textFieldInstructorPrice.setColumns(10);
		textFieldInstructorPrice.setBackground(Color.WHITE);
		panelBookingRessources.add(textFieldInstructorPrice);

		textFieldWeaponPrice = new JTextField();
		sl_panelBookingRessources.putConstraint(SpringLayout.NORTH, textFieldWeaponPrice, 5, SpringLayout.SOUTH,
				textFieldInstructorPrice);
		sl_panelBookingRessources.putConstraint(SpringLayout.WEST, textFieldWeaponPrice, 0, SpringLayout.EAST,
				textFieldWeapon);
		textFieldWeaponPrice.setRequestFocusEnabled(false);
		sl_panelBookingRessources.putConstraint(SpringLayout.EAST, textFieldWeaponPrice, -15, SpringLayout.EAST,
				panelBookingRessources);
		textFieldWeaponPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldWeaponPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldWeaponPrice.setEditable(false);
		textFieldWeaponPrice.setColumns(10);
		textFieldWeaponPrice.setBackground(Color.WHITE);
		panelBookingRessources.add(textFieldWeaponPrice);

		textFieldTotalPrice = new JTextField();
		sl_panelBookingRessources.putConstraint(SpringLayout.NORTH, textFieldTotalPrice, 5, SpringLayout.SOUTH,
				textFieldWeaponPrice);
		sl_panelBookingRessources.putConstraint(SpringLayout.WEST, textFieldTotalPrice, 0, SpringLayout.EAST,
				textFieldTotal);
		textFieldTotalPrice.setRequestFocusEnabled(false);
		sl_panelBookingRessources.putConstraint(SpringLayout.EAST, textFieldTotalPrice, -15, SpringLayout.EAST,
				panelBookingRessources);
		textFieldTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldTotalPrice.setEditable(false);
		textFieldTotalPrice.setColumns(10);
		textFieldTotalPrice.setBackground(Color.WHITE);
		panelBookingRessources.add(textFieldTotalPrice);

		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(Color.WHITE);
		add(panelSouth, BorderLayout.SOUTH);

		JButton btnChoose = new JButton("Bekr\u00E6ft booking");
		btnChoose.addActionListener(e ->{
			try {
				if(confirmBooking()) {
					JOptionPane.showMessageDialog(panelCenter, "Din booking er registreret med bookingnummer " + bookingController.getCurrentBooking().getBookingNumber() + " - kvitering er sendt pr. mail");
					mainUI.backToStart();
				}
				else{
					JOptionPane.showMessageDialog(panelCenter, "Fejl! Din booking har en allerede optaget ressource - pr\u00F8v igen");
					mainUI.backToStart();
				};
			} catch (DataAccessException e1) {
				JOptionPane.showMessageDialog(panelCenter, "Fejl! Din booking er ikke registreret - check din internetforbindelse og pr\u00F8v igen");
				e1.printStackTrace();
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		JButton btnCancelBooking = new JButton("Annuller booking");
		btnCancelBooking.addActionListener(e -> backToStart());
		btnCancelBooking.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSouth.add(btnCancelBooking);
		btnChoose.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSouth.add(btnChoose);

		init(mainUI, bookingController);

		updateFields();
	}

	private void backToStart() {
		mainUI.backToStart();
	}

	private void init(MainUI mainUI, BookingController bookingController) {
		this.mainUI = mainUI;
		this.bookingController = bookingController;
		dayMontFormat = DateTimeFormatter.ofPattern("dd. MMM");
		twoDecimals = new DecimalFormat("0.00");
	}

	private void updateFields() {
		textFieldBookingShootingRange
				.setText(bookingController.getCurrentBooking().getShootingRange().getShootingRangeId() + "");
		textFieldBookingInstructor.setText(bookingController.getCurrentBooking().getInstructor().getFirstName() + " "
				+ bookingController.getCurrentBooking().getInstructor().getLastName());
		textFieldBookingWeapon.setText(bookingController.getCurrentBooking().getWeapon().getWeaponName());
		textFieldBookingDateTimeInfo.setText(dayMontFormat.format(bookingController.getCurrentBooking().getDate())
				+ " kl. " + bookingController.getCurrentBooking().getTime() + ":00");

		textFieldCustomerName.setText(bookingController.getCurrentBooking().getCustomer().getFirstName() + " "
				+ bookingController.getCurrentBooking().getCustomer().getLastName());
		textFieldCustomerPhone.setText(bookingController.getCurrentBooking().getCustomer().getPhone());
		textFieldCustomerEmail.setText(bookingController.getCurrentBooking().getCustomer().getEmail());
		textFieldCustomerNumber.setText(bookingController.getCurrentBooking().getCustomer().getCustomerId() + "");

		txtFieldShootingRangePrice.setText(twoDecimals.format(bookingController.getCurrentBooking().getShootingRange().getPrice().getPrice()) + " kr.");
		textFieldInstructorPrice.setText(twoDecimals.format(bookingController.getCurrentBooking().getInstructor().getPrice().getPrice()) + " kr.");
		textFieldWeaponPrice.setText(
				twoDecimals.format(bookingController.getCurrentBooking().getWeapon().getPrice().getPrice()) + " kr.");
		textFieldTotalPrice.setText(twoDecimals.format(bookingController.getCurrentBooking().getPriceTotal()) + " kr.");
	}

	private boolean confirmBooking() throws DataAccessException, SQLException {
		return bookingController.confirmBooking();
	}
}