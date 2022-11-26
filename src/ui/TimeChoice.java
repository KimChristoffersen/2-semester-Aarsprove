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

public class TimeChoice extends JPanel {

	private JPanel panelCenter;
	private JPanel panelTop;
	private JButton btnDateBackward;
	private List<CalendarButton> calendarButtons;
	private JLabel lblDateFromTo;
	private DateTimeFormatter dayMontFormat;
	private DateTimeFormatter yearFormat;
	private static final int WAIT_LENGTH = 1000; // 1 second
	private LocalDate firstDayOfThisWeek;
	private BookingController bookingController;
	private MainUI mainUI;
	private JButton btnDateForward;

	/**
	 * Create the panel.
	 * 
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	public TimeChoice(MainUI mainUI) throws SQLException, DataAccessException {
		this.mainUI = mainUI;
		init();

		JButton btnNewButton = new JButton("TIMECHOICE");
		btnNewButton.addActionListener(e -> TimeChoice());
		setLayout(new BorderLayout(0, 0));
		add(btnNewButton);

		panelTop = new JPanel();
		panelTop.setLayout(null);
		panelTop.setPreferredSize(new Dimension(10, 50));
		add(panelTop, BorderLayout.NORTH);

		btnDateBackward = new JButton("Tilbage");
		btnDateBackward.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDateBackward.setEnabled(false);
		btnDateBackward.addActionListener(e -> {
			try {
				dateBackward();
			} catch (DataAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnDateBackward.setBounds(10, 10, 90, 30);
		panelTop.add(btnDateBackward);

		lblDateFromTo = new JLabel();
		lblDateFromTo.setText("21. Nov til 26. Nov 2022");
		lblDateFromTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateFromTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateFromTo.setBounds(99, 10, 174, 30);
		panelTop.add(lblDateFromTo);

		btnDateForward = new JButton("Frem");
		btnDateForward.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDateForward.addActionListener(e -> {
			try {
				dateForward();
			} catch (DataAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnDateForward.setBounds(283, 10, 90, 30);
		panelTop.add(btnDateForward);

		JButton btnClose = new JButton("Luk");
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClose.setBounds(590, 10, 90, 30);
		panelTop.add(btnClose);

		panelCenter = new JPanel();
		panelCenter.setBackground(Color.WHITE);
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(8, 6, 6, 3));

		createCalendarButtons();

	}

	private void init() throws SQLException, DataAccessException {
		firstDayOfThisWeek = LocalDate.now().with(DayOfWeek.MONDAY);
		dayMontFormat = DateTimeFormatter.ofPattern("dd. MMM");
		yearFormat = DateTimeFormatter.ofPattern("u");
		calendarButtons = new ArrayList<>();
		bookingController = new BookingController();
	}

	private void TimeChoice() {
		mainUI.bookingConfirmation();
	}

	// Create all the buttons and adds them to a button list
	public void createCalendarButtons() throws DataAccessException {
		// Clear buttonlist
		calendarButtons.clear();

		CalendarButton btnMonday = new CalendarButton("<html><center><b>MANDAG<br></b>"
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(0)) + "</center></html>",
				firstDayOfThisWeek.plusDays(0), "headerButton");
		calendarButtons.add(btnMonday);

		CalendarButton btnThuesday = new CalendarButton("<html><center><b>TIRSDAG<br></b>"
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(1)) + "</center></html>",
				firstDayOfThisWeek.plusDays(0), "headerButton");
		calendarButtons.add(btnThuesday);

		CalendarButton btnWednesday = new CalendarButton("<html><center><b>ONSDAG<br></b>"
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(2)) + "</center></html>",
				firstDayOfThisWeek.plusDays(0), "headerButton");
		calendarButtons.add(btnWednesday);

		CalendarButton btnThursday = new CalendarButton("<html><center><b>TORSDAG<br></b>"
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(3)) + "</center></html>",
				firstDayOfThisWeek.plusDays(0), "headerButton");
		calendarButtons.add(btnThursday);

		CalendarButton btnFriday = new CalendarButton("<html><center><b>FREDAG<br></b>"
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(4)) + "</center></html>",
				firstDayOfThisWeek.plusDays(0), "headerButton");
		calendarButtons.add(btnFriday);

		CalendarButton btnSaturday = new CalendarButton("<html><center><b>L\u00D8RDAG<br></b>"
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(5)) + "</center></html>",
				firstDayOfThisWeek.plusDays(0), "headerButton");
		calendarButtons.add(btnSaturday);

		CalendarButton btnMon10 = new CalendarButton("10:00-11:00", firstDayOfThisWeek.plusDays(0), 10, "timeButton");
		calendarButtons.add(btnMon10);

		CalendarButton btnTue10 = new CalendarButton("10:00-11:00", firstDayOfThisWeek.plusDays(1), 10, "timeButton");
		calendarButtons.add(btnTue10);

		CalendarButton btnWed10 = new CalendarButton("10:00-11:00", firstDayOfThisWeek.plusDays(2), 10, "timeButton");
		calendarButtons.add(btnWed10);

		CalendarButton btnThu10 = new CalendarButton("10:00-11:00", firstDayOfThisWeek.plusDays(3), 10, "timeButton");
		calendarButtons.add(btnThu10);

		CalendarButton btnFri10 = new CalendarButton("10:00-11:00", firstDayOfThisWeek.plusDays(4), 10, "timeButton");
		calendarButtons.add(btnFri10);

		CalendarButton btnSat10 = new CalendarButton("10:00-11:00", firstDayOfThisWeek.plusDays(5), 10, "timeButton");
		calendarButtons.add(btnSat10);

		CalendarButton btnMon11 = new CalendarButton("11:00-12:00", firstDayOfThisWeek.plusDays(0), 11, "timeButton");
		calendarButtons.add(btnMon11);

		CalendarButton btnTue11 = new CalendarButton("11:00-12:00", firstDayOfThisWeek.plusDays(1), 11, "timeButton");
		calendarButtons.add(btnTue11);

		CalendarButton btnWed11 = new CalendarButton("11:00-12:00", firstDayOfThisWeek.plusDays(2), 11, "timeButton");
		calendarButtons.add(btnWed11);

		CalendarButton btnThu11 = new CalendarButton("11:00-12:00", firstDayOfThisWeek.plusDays(3), 11, "timeButton");
		calendarButtons.add(btnThu11);

		CalendarButton btnFri11 = new CalendarButton("11:00-12:00", firstDayOfThisWeek.plusDays(4), 11, "timeButton");
		calendarButtons.add(btnFri11);

		CalendarButton btnSat11 = new CalendarButton("11:00-12:00", firstDayOfThisWeek.plusDays(5), 11, "timeButton");
		calendarButtons.add(btnSat11);

		CalendarButton btnMon12 = new CalendarButton("12:00-13:00", firstDayOfThisWeek.plusDays(0), 12, "timeButton");
		calendarButtons.add(btnMon12);

		CalendarButton btnTue12 = new CalendarButton("12:00-13:00", firstDayOfThisWeek.plusDays(1), 12, "timeButton");
		calendarButtons.add(btnTue12);

		CalendarButton btnWed12 = new CalendarButton("12:00-13:00", firstDayOfThisWeek.plusDays(2), 12, "timeButton");
		calendarButtons.add(btnWed12);

		CalendarButton btnThu12 = new CalendarButton("12:00-13:00", firstDayOfThisWeek.plusDays(3), 12, "timeButton");
		calendarButtons.add(btnThu12);

		CalendarButton btnFri12 = new CalendarButton("12:00-13:00", firstDayOfThisWeek.plusDays(4), 12, "timeButton");
		calendarButtons.add(btnFri12);

		CalendarButton btnSat12 = new CalendarButton("12:00-13:00", firstDayOfThisWeek.plusDays(5), 12, "timeButton");
		calendarButtons.add(btnSat12);

		CalendarButton btnMon13 = new CalendarButton("13:00-14:00", firstDayOfThisWeek.plusDays(0), 13, "timeButton");
		calendarButtons.add(btnMon13);

		CalendarButton btnTue13 = new CalendarButton("13:00-14:00", firstDayOfThisWeek.plusDays(1), 13, "timeButton");
		calendarButtons.add(btnTue13);

		CalendarButton btnWed13 = new CalendarButton("13:00-14:00", firstDayOfThisWeek.plusDays(2), 13, "timeButton");
		calendarButtons.add(btnWed13);

		CalendarButton btnThu13 = new CalendarButton("13:00-14:00", firstDayOfThisWeek.plusDays(3), 13, "timeButton");
		calendarButtons.add(btnThu13);

		CalendarButton btnFri13 = new CalendarButton("13:00-14:00", firstDayOfThisWeek.plusDays(4), 13, "timeButton");
		calendarButtons.add(btnFri13);

		CalendarButton btnSat13 = new CalendarButton("13:00-14:00", firstDayOfThisWeek.plusDays(5), 13, "timeButton");
		calendarButtons.add(btnSat13);

		CalendarButton btnMon14 = new CalendarButton("14:00-15:00", firstDayOfThisWeek.plusDays(0), 14, "timeButton");
		calendarButtons.add(btnMon14);

		CalendarButton btnTue14 = new CalendarButton("14:00-15:00", firstDayOfThisWeek.plusDays(1), 14, "timeButton");
		calendarButtons.add(btnTue14);

		CalendarButton btnWed14 = new CalendarButton("14:00-15:00", firstDayOfThisWeek.plusDays(2), 14, "timeButton");
		calendarButtons.add(btnWed14);

		CalendarButton btnThu14 = new CalendarButton("14:00-15:00", firstDayOfThisWeek.plusDays(3), 14, "timeButton");
		calendarButtons.add(btnThu14);

		CalendarButton btnFri14 = new CalendarButton("14:00-15:00", firstDayOfThisWeek.plusDays(4), 14, "timeButton");
		calendarButtons.add(btnFri14);

		CalendarButton btnSat14 = new CalendarButton("", firstDayOfThisWeek.plusDays(5), 14, "timeButton");
		calendarButtons.add(btnSat14);

		CalendarButton btnMon15 = new CalendarButton("15:00-16:00", firstDayOfThisWeek.plusDays(0), 15, "timeButton");
		calendarButtons.add(btnMon15);

		CalendarButton btnTue15 = new CalendarButton("15:00-16:00", firstDayOfThisWeek.plusDays(1), 15, "timeButton");
		calendarButtons.add(btnTue15);

		CalendarButton btnWed15 = new CalendarButton("15:00-16:00", firstDayOfThisWeek.plusDays(2), 15, "timeButton");
		calendarButtons.add(btnWed15);

		CalendarButton btnThu15 = new CalendarButton("15:00-16:00", firstDayOfThisWeek.plusDays(3), 15, "timeButton");
		calendarButtons.add(btnThu15);

		CalendarButton btnFri15 = new CalendarButton("15:00-16:00", firstDayOfThisWeek.plusDays(4), 15, "timeButton");
		calendarButtons.add(btnFri15);

		CalendarButton btnSat15 = new CalendarButton("", firstDayOfThisWeek.plusDays(5), 15, "timeButton");
		calendarButtons.add(btnSat15);

		CalendarButton btnMon16 = new CalendarButton("16:00-17:00", firstDayOfThisWeek.plusDays(0), 16, "timeButton");
		calendarButtons.add(btnMon16);

		CalendarButton btnTue16 = new CalendarButton("16:00-17:00", firstDayOfThisWeek.plusDays(1), 16, "timeButton");
		calendarButtons.add(btnTue16);

		CalendarButton btnWed16 = new CalendarButton("16:00-17:00", firstDayOfThisWeek.plusDays(2), 16, "timeButton");
		calendarButtons.add(btnWed16);

		CalendarButton btnThu16 = new CalendarButton("16:00-17:00", firstDayOfThisWeek.plusDays(3), 16, "timeButton");
		calendarButtons.add(btnThu16);

		CalendarButton btnFri16 = new CalendarButton("16:00-17:00", firstDayOfThisWeek.plusDays(4), 16, "timeButton");
		calendarButtons.add(btnFri16);

		CalendarButton btnSat16 = new CalendarButton("", firstDayOfThisWeek.plusDays(5), 16, "timeButton");
		calendarButtons.add(btnSat16);

		addButtonsFromList();
	}

	private void addButtonsFromList() throws DataAccessException {
		for (CalendarButton cb : calendarButtons) {
			panelCenter.add(cb);
			cb.setAvailableShootingRanges(bookingController.getAvailableShootingRanges(cb.getDate(), cb.getTime()));
			if (cb.getButtonType() != "headerButton") {
				cb.addActionListener(e -> {
					try {
						selectDate(cb.getDate(), cb.getTime(), cb);
					} catch (DataAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
			} else {

				cb.setText(cb.getLabel());
			}
		}
		updateStatus();
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

	// updates the status of the buttons
	private synchronized void updateStatus() throws DataAccessException {
		for (CalendarButton cb : calendarButtons) {
			cb.setAvailableShootingRanges(bookingController.getAvailableShootingRanges(cb.getDate(), cb.getTime()));
			if (!checkForDatePast(cb) || (!checkAvailability(cb))) {
				cb.setEnabled(false);
			}
		}
	}

	// Gets buttons date and time when a button is clicked
	private void selectDate(LocalDate date, int time, CalendarButton button) throws DataAccessException {
		// button.getAvailableShootingRanges().get(0));
	}

	// Shows last weeks buttons, displays first day, last day and year
	private void dateBackward() throws DataAccessException {
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
		// removes all buttons from the center panel, creates buttons anew and updates
		// status of the buttons
		panelCenter.removeAll();
		createCalendarButtons();
		updateStatus();
	}

	// Shows next weeks buttons, displays first day, last day and year
	private void dateForward() throws DataAccessException {
		// Enables button backwards
		btnDateBackward.setEnabled(true);

		// Adds 7 days to the first day of the week
		firstDayOfThisWeek = firstDayOfThisWeek.plusDays(7);

		// Sets text to label
		lblDateFromTo.setText(dayMontFormat.format(firstDayOfThisWeek.plusDays(0)) + " til "
				+ dayMontFormat.format(firstDayOfThisWeek.plusDays(5)) + " " + yearFormat.format(firstDayOfThisWeek));

		// removes all buttons from the center panel, creates buttons anew and updates
		// status of the buttons
		panelCenter.removeAll();
		createCalendarButtons();
		updateStatus();
	}

	// Check if button contains required ressourses
	private boolean checkAvailability(CalendarButton button) {
		boolean available = true;
		if (button.getAvailableShootingRanges().size() == 0) {
			available = false;
		}
		return available;
	}
}