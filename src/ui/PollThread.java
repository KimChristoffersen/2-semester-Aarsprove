package ui;

import java.sql.SQLException;
import java.time.LocalDateTime;

import controller.BookingController;
import database.DataAccessException;
/**
 * Class for PollThread.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class PollThread extends Thread {
	private TimeChoiceMonitor timeChoiceMonitor;
	private BookingController bookingController;
	
	private LocalDateTime lastDatabaseChangeTime;
	private boolean timeChoiceOpen;

	public PollThread() throws SQLException, DataAccessException {
		bookingController = new BookingController();
		timeChoiceMonitor = TimeChoiceMonitor.getInstance();
		timeChoiceOpen = true;
		lastDatabaseChangeTime = LocalDateTime.of(2017, 1, 1, 1, 0);
	}

	@Override
	public void run() {
		while (timeChoiceOpen)
			try {
				try {
					pollAndGetLastDataBaseChangeTime();
				} catch (DataAccessException e) {
					e.printStackTrace();
				}
				// System.out.println("polled");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	// gets last database change - if changed notifies threads
	public void pollAndGetLastDataBaseChangeTime() throws DataAccessException {
		if (lastDatabaseChangeTime.isBefore(bookingController.getLastDataBaseChangeTime())) {
			timeChoiceMonitor.notifyAllThreads();
			lastDatabaseChangeTime = bookingController.getLastDataBaseChangeTime();
		}
	}
	
	// boolean to indicate if threads should run (threads should only run when calendar is open)
	public void setTimeChoiceOpen(boolean open) {
		timeChoiceOpen = open;
	}
}