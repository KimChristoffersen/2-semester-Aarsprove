package ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import controller.BookingController;
import database.DataAccessException;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Class for TimeChoice.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class TimeChoice extends JPanel {

	private JPanel panelCalendar;
	private JPanel panelWeekChooser;
	private JButton btnDateBackward;
	private List<CalendarButton> calendarButtons;
	private JLabel lblDateFromTo;
	private JLabel lblTimeChoice;
	private DateTimeFormatter dayMontFormat;
	private DateTimeFormatter yearFormat;
	private LocalDate firstDayOfThisWeek;
	private BookingController bookingController;
	private MainUI mainUI;
	private JButton btnDateForward;
	private JPanel panel;
	private JPanel panelTop;

	// Threads and monitor
	private TimeChoiceMonitor timeChoiceMonitor;
	private PollThread pollThread;
	private UpdateTimeChoiceUIThread updateTimeChoiceUIThread;

	/**
	 * Create the panel.
	 * 
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	public TimeChoice(MainUI mainUI, BookingController bookingController) throws SQLException, DataAccessException {
		init(mainUI, bookingController);
		setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 10));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		panelWeekChooser = new JPanel();
		panelWeekChooser.setBackground(UIManager.getColor("EditorPane.disabledBackground"));
		panelWeekChooser.setLayout(null);
		panelWeekChooser.setPreferredSize(new Dimension(400, 50));
		panel.add(panelWeekChooser, BorderLayout.NORTH);

		btnDateBackward = new JButton("Tilbage");
		btnDateBackward.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDateBackward.setEnabled(false);
		btnDateBackward.addActionListener(e -> {
			try {
				dateBackward();
			} catch (DataAccessException | SQLException e1) {
				e1.printStackTrace();
			}
		});
		btnDateBackward.setBounds(10, 10, 90, 30);
		panelWeekChooser.add(btnDateBackward);

		lblDateFromTo = new JLabel();
		lblDateFromTo.setText("21. Nov til 26. Nov 2022");
		lblDateFromTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateFromTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateFromTo.setBounds(100, 10, 174, 30);
		lblDateFromTo.setText(dayMontFormat.format(firstDayOfThisWeek.plusDays(0)) + " til " + dayMontFormat.format(firstDayOfThisWeek.plusDays(5)) + " " + yearFormat.format(firstDayOfThisWeek));
		panelWeekChooser.add(lblDateFromTo);

		btnDateForward = new JButton("Frem");
		btnDateForward.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDateForward.addActionListener(e -> {
			try {
				dateForward();
			} catch (DataAccessException | SQLException e1) {
				e1.printStackTrace();
			}
		});
		btnDateForward.setBounds(283, 10, 90, 30);
		panelWeekChooser.add(btnDateForward);
		
		JButton btnAnnullerBooking = new JButton("Annuller booking");
		btnAnnullerBooking.addActionListener(e -> backToStart());
		btnAnnullerBooking.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAnnullerBooking.setBounds(559, 10, 181, 30);
		panelWeekChooser.add(btnAnnullerBooking);

		panelCalendar = new JPanel();
		panelCalendar.setBackground(Color.WHITE);
		panel.add(panelCalendar, BorderLayout.CENTER);
		panelCalendar.setLayout(new GridLayout(7, 5, 6, 3));

		panelTop = new JPanel();
		panelTop.setBackground(Color.GRAY);
		add(panelTop, BorderLayout.NORTH);

		lblTimeChoice = new JLabel("V\u00E6lg tid");
		lblTimeChoice.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeChoice.setForeground(Color.WHITE);
		lblTimeChoice.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelTop.add(lblTimeChoice);

		createCalendarButtons();

		// initializing threads and monitor
		timeChoiceMonitor = TimeChoiceMonitor.getInstance();
		updateTimeChoiceUIThread = new UpdateTimeChoiceUIThread(this, timeChoiceMonitor);
		pollThread = new PollThread();
		pollThread.start();
		// System.out.println("PollStart");
		updateTimeChoiceUIThread.start();
		// System.out.println("UI Thread");
	}

	private void init(MainUI mainUI, BookingController bookingController) throws SQLException, DataAccessException {
		this.mainUI = mainUI;
		firstDayOfThisWeek = LocalDate.now().with(DayOfWeek.MONDAY);
		dayMontFormat = DateTimeFormatter.ofPattern("dd. MMM");
		yearFormat = DateTimeFormatter.ofPattern("u");
		calendarButtons = new ArrayList<>();
		this.bookingController = bookingController;
	}

	public synchronized void createCalendarButtons() throws DataAccessException, SQLException {
		// Clear buttonlist and removes buttons from panel
		calendarButtons.clear();
		panelCalendar.removeAll();
		
		// Defines the buttons, adds them to a list and adds them to the panel (headerbuttons directly and timebuttons with method)
		CalendarButton btnMonday = new CalendarButton("<html><center><b>MANDAG<br></b>"
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(0)) + "</center></html>",
				firstDayOfThisWeek.plusDays(0), "headerButton");
		calendarButtons.add(btnMonday);
		panelCalendar.add(btnMonday);
		btnMonday.setText(btnMonday.getLabel());

		CalendarButton btnThuesday = new CalendarButton("<html><center><b>TIRSDAG<br></b>"
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(1)) + "</center></html>",
				firstDayOfThisWeek.plusDays(0), "headerButton");
		calendarButtons.add(btnThuesday);
		panelCalendar.add(btnThuesday);
		btnThuesday.setText(btnThuesday.getLabel());

		CalendarButton btnWednesday = new CalendarButton("<html><center><b>ONSDAG<br></b>"
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(2)) + "</center></html>",
				firstDayOfThisWeek.plusDays(0), "headerButton");
		calendarButtons.add(btnWednesday);
		panelCalendar.add(btnWednesday);
		btnWednesday.setText(btnWednesday.getLabel());

		CalendarButton btnThursday = new CalendarButton("<html><center><b>TORSDAG<br></b>"
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(3)) + "</center></html>",
				firstDayOfThisWeek.plusDays(0), "headerButton");
		calendarButtons.add(btnThursday);
		panelCalendar.add(btnThursday);
		btnThursday.setText(btnThursday.getLabel());

		CalendarButton btnFriday = new CalendarButton("<html><center><b>FREDAG<br></b>"
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(4)) + "</center></html>",
				firstDayOfThisWeek.plusDays(0), "headerButton");
		calendarButtons.add(btnFriday);
		panelCalendar.add(btnFriday);
		btnFriday.setText(btnFriday.getLabel());

		CalendarButton btnMon10 = new CalendarButton("10:00-11:00", firstDayOfThisWeek.plusDays(0), 10, "timeButton");
		calendarButtons.add(btnMon10);
		addButton(btnMon10);

		CalendarButton btnTue10 = new CalendarButton("10:00-11:00", firstDayOfThisWeek.plusDays(1), 10, "timeButton");
		calendarButtons.add(btnTue10);
		addButton(btnTue10);

		CalendarButton btnWed10 = new CalendarButton("10:00-11:00", firstDayOfThisWeek.plusDays(2), 10, "timeButton");
		calendarButtons.add(btnWed10);
		addButton(btnWed10);

		CalendarButton btnThu10 = new CalendarButton("10:00-11:00", firstDayOfThisWeek.plusDays(3), 10, "timeButton");
		calendarButtons.add(btnThu10);
		addButton(btnThu10);

		CalendarButton btnFri10 = new CalendarButton("10:00-11:00", firstDayOfThisWeek.plusDays(4), 10, "timeButton");
		calendarButtons.add(btnFri10);
		addButton(btnFri10);

		CalendarButton btnMon11 = new CalendarButton("11:00-12:00", firstDayOfThisWeek.plusDays(0), 11, "timeButton");
		calendarButtons.add(btnMon11);
		addButton(btnMon11);

		CalendarButton btnTue11 = new CalendarButton("11:00-12:00", firstDayOfThisWeek.plusDays(1), 11, "timeButton");
		calendarButtons.add(btnTue11);
		addButton(btnTue11);

		CalendarButton btnWed11 = new CalendarButton("11:00-12:00", firstDayOfThisWeek.plusDays(2), 11, "timeButton");
		calendarButtons.add(btnWed11);
		addButton(btnWed11);

		CalendarButton btnThu11 = new CalendarButton("11:00-12:00", firstDayOfThisWeek.plusDays(3), 11, "timeButton");
		calendarButtons.add(btnThu11);
		addButton(btnThu11);

		CalendarButton btnFri11 = new CalendarButton("11:00-12:00", firstDayOfThisWeek.plusDays(4), 11, "timeButton");
		calendarButtons.add(btnFri11);
		addButton(btnFri11);

		CalendarButton btnMon12 = new CalendarButton("12:00-13:00", firstDayOfThisWeek.plusDays(0), 12, "timeButton");
		calendarButtons.add(btnMon12);
		addButton(btnMon12);

		CalendarButton btnTue12 = new CalendarButton("12:00-13:00", firstDayOfThisWeek.plusDays(1), 12, "timeButton");
		calendarButtons.add(btnTue12);
		addButton(btnTue12);

		CalendarButton btnWed12 = new CalendarButton("12:00-13:00", firstDayOfThisWeek.plusDays(2), 12, "timeButton");
		calendarButtons.add(btnWed12);
		addButton(btnWed12);

		CalendarButton btnThu12 = new CalendarButton("12:00-13:00", firstDayOfThisWeek.plusDays(3), 12, "timeButton");
		calendarButtons.add(btnThu12);
		addButton(btnThu12);

		CalendarButton btnFri12 = new CalendarButton("12:00-13:00", firstDayOfThisWeek.plusDays(4), 12, "timeButton");
		calendarButtons.add(btnFri12);
		addButton(btnFri12);

		CalendarButton btnMon13 = new CalendarButton("13:00-14:00", firstDayOfThisWeek.plusDays(0), 13, "timeButton");
		calendarButtons.add(btnMon13);
		addButton(btnMon13);

		CalendarButton btnTue13 = new CalendarButton("13:00-14:00", firstDayOfThisWeek.plusDays(1), 13, "timeButton");
		calendarButtons.add(btnTue13);
		addButton(btnTue13);

		CalendarButton btnWed13 = new CalendarButton("13:00-14:00", firstDayOfThisWeek.plusDays(2), 13, "timeButton");
		calendarButtons.add(btnWed13);
		addButton(btnWed13);

		CalendarButton btnThu13 = new CalendarButton("13:00-14:00", firstDayOfThisWeek.plusDays(3), 13, "timeButton");
		calendarButtons.add(btnThu13);
		addButton(btnThu13);

		CalendarButton btnFri13 = new CalendarButton("13:00-14:00", firstDayOfThisWeek.plusDays(4), 13, "timeButton");
		calendarButtons.add(btnFri13);
		addButton(btnFri13);

		CalendarButton btnMon14 = new CalendarButton("14:00-15:00", firstDayOfThisWeek.plusDays(0), 14, "timeButton");
		calendarButtons.add(btnMon14);
		addButton(btnMon14);

		CalendarButton btnTue14 = new CalendarButton("14:00-15:00", firstDayOfThisWeek.plusDays(1), 14, "timeButton");
		calendarButtons.add(btnTue14);
		addButton(btnTue14);

		CalendarButton btnWed14 = new CalendarButton("14:00-15:00", firstDayOfThisWeek.plusDays(2), 14, "timeButton");
		calendarButtons.add(btnWed14);
		addButton(btnWed14);

		CalendarButton btnThu14 = new CalendarButton("14:00-15:00", firstDayOfThisWeek.plusDays(3), 14, "timeButton");
		calendarButtons.add(btnThu14);
		addButton(btnThu14);

		CalendarButton btnFri14 = new CalendarButton("14:00-15:00", firstDayOfThisWeek.plusDays(4), 14, "timeButton");
		calendarButtons.add(btnFri14);
		addButton(btnFri14);

		CalendarButton btnMon15 = new CalendarButton("15:00-16:00", firstDayOfThisWeek.plusDays(0), 15, "timeButton");
		calendarButtons.add(btnMon15);
		addButton(btnMon15);

		CalendarButton btnTue15 = new CalendarButton("15:00-16:00", firstDayOfThisWeek.plusDays(1), 15, "timeButton");
		calendarButtons.add(btnTue15);
		addButton(btnTue15);

		CalendarButton btnWed15 = new CalendarButton("15:00-16:00", firstDayOfThisWeek.plusDays(2), 15, "timeButton");
		calendarButtons.add(btnWed15);
		addButton(btnWed15);

		CalendarButton btnThu15 = new CalendarButton("15:00-16:00", firstDayOfThisWeek.plusDays(3), 15, "timeButton");
		calendarButtons.add(btnThu15);
		addButton(btnThu15);

		CalendarButton btnFri15 = new CalendarButton("15:00-16:00", firstDayOfThisWeek.plusDays(4), 15, "timeButton");
		calendarButtons.add(btnFri15);
		addButton(btnFri15);
	}

	private void addButton(CalendarButton button) throws DataAccessException {
		// gets buttons date and time
		LocalDate buttonDate = button.getDate();
		int buttonTime = button.getTime();
		// adds lists of available ressources to the buttons
		addRessourcesToButttons(button);
		// adds the button to the panel
		panelCalendar.add(button);
		// creates actionlisteners to the buttons
		button.addActionListener(e -> {
			try {
				selectDate(button);
			} catch (DataAccessException | SQLException | InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		// checks for availability
		checkAvailability();
	}

	// Gets buttons date, time, shootingrange, instructor when a button is clicked
	private void selectDate(CalendarButton button) throws DataAccessException, SQLException, InterruptedException {
		pollThread.setTimeChoiceOpen(false);
		LocalDate date = button.getDate();
		int time = button.getTime();
		int shootingRange = button.getAvailableShootingRanges().get(0);
		int instructor = button.getAvailableInstructors().get(0);
		mainUI.gotoBookingConfirmation(date, time, shootingRange, instructor);
	}

	// Checks if buttons date is before current date
	private boolean checkForDatePast(CalendarButton button) {
		boolean after = false;
		LocalDate today = LocalDate.now();
		LocalDate buttonDate = button.getDate();
		if (today.isBefore(buttonDate.plusDays(+1))) {
			after = true;
		}
		return after;
	}

	private void addRessourcesToButttons(CalendarButton button) throws DataAccessException {
		button.setAvailableShootingRanges(bookingController.getAvailableShootingRanges(button.getDate(), button.getTime()));
		button.setAvailableInstructors(bookingController.getAvailableInstructors(button.getDate(), button.getTime()));
		button.setAvailableWeapons(bookingController.getAvailableWeapons(button.getDate(), button.getTime(), bookingController.getCurrentBooking().getWeapon().getWeaponId()));
	}
	
	// updates the status of the buttons
	public void updateStatus() throws DataAccessException, SQLException {
		for (CalendarButton cb : calendarButtons) {
			if (!cb.getButtonType().equals("headerButton")) {
				// adds lists of available ressources to the buttons
				addRessourcesToButttons(cb);
			}
		}
		checkAvailability();
	}

	private void checkAvailability() {
		for (CalendarButton cb : calendarButtons)
			// checks that availability lists excists
			if (cb.getAvailableShootingRanges() != null && cb.getAvailableInstructors() != null && cb.getAvailableWeapons() != null) {
				// checks that availability lists aren't empty and if the button date is before today
				if (cb.getAvailableShootingRanges().size() == 0 || cb.getAvailableInstructors().size() == 0 || cb.getAvailableWeapons().size() == 0
						|| !checkForDatePast(cb) ) {
					cb.setEnabled(false);
				}
			}
	}

	// Shows last weeks buttons, displays first day, last day and year
	private void dateBackward() throws DataAccessException, SQLException {
		// Disables button when shown week is currentweek
		LocalDate today = LocalDate.now();
		if (firstDayOfThisWeek.isBefore(today.plusDays(7))) {
			btnDateBackward.setEnabled(false);
		}
		// Subtracts 7 days to the first day of the week
		firstDayOfThisWeek = firstDayOfThisWeek.plusDays(-7);
		// Sets text to label
		lblDateFromTo.setText(dayMontFormat.format(firstDayOfThisWeek.plusDays(0)) + " til "
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(5)) + " " + yearFormat.format(firstDayOfThisWeek));
		createCalendarButtons();
	}

	// Shows next weeks buttons, displays first day, last day and year
	private void dateForward() throws DataAccessException, SQLException {
		// Enables button backwards
		btnDateBackward.setEnabled(true);
		// Adds 7 days to the first day of the week
		firstDayOfThisWeek = firstDayOfThisWeek.plusDays(7);
		// Sets text to label
		lblDateFromTo.setText(dayMontFormat.format(firstDayOfThisWeek.plusDays(0)) + " til "
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(5)) + " " + yearFormat.format(firstDayOfThisWeek));
		createCalendarButtons();
	}
	
	private void backToStart() {
		mainUI.backToStart();
	}
}