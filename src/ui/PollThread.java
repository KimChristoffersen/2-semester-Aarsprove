package ui;

import java.sql.SQLException;
import java.time.LocalDateTime;

import controller.BookingController;
import database.DataAccessException;

public class PollThread extends Thread {

	private TimeChoiceMonitor timeChoiceMonitor;
	private BookingController bookingController;
	private int bookingNumber;
	private LocalDateTime lastDatabaseChangeTime;
	
	private boolean timeChoiceOpen;

	public PollThread() throws SQLException, DataAccessException {
		bookingController = new BookingController();
		timeChoiceMonitor = TimeChoiceMonitor.getInstance();
		timeChoiceOpen = true;
		lastDatabaseChangeTime = LocalDateTime.of(2017, 1, 1, 1, 0);
		System.out.println(lastDatabaseChangeTime);
	}

	@Override
	public void run() {
		while (timeChoiceOpen)
			try {
				try {
					pollAndGetLastDataBaseChangeTime();
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("polled");
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void pollAndGetLastDataBaseChangeTime() throws DataAccessException {
		if (lastDatabaseChangeTime.isBefore(bookingController.getLastDataBaseChangeTime())) {
			timeChoiceMonitor.notifyAllThreads();
			lastDatabaseChangeTime = bookingController.getLastDataBaseChangeTime();
		}
	}
	
	public void setTimeChoiceOpen(boolean open) {
		timeChoiceOpen = false;
	}
}
