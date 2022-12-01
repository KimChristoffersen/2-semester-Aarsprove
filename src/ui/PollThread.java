package ui;

import java.sql.SQLException;

import controller.BookingController;
import database.DataAccessException;

public class PollThread extends Thread {

	private TimeChoiceMonitor timeChoiceMonitor;
	private BookingController bookingController;
	private int bookingNumber;
	private boolean timeChoiceOpen;

	public PollThread() throws SQLException, DataAccessException {
		bookingController = new BookingController();
		timeChoiceMonitor = TimeChoiceMonitor.getInstance();
		timeChoiceOpen = true;
	}

	@Override
	public void run() {
		while (timeChoiceOpen)
			try {
				try {
					pollAndUpdateNewestBookingNumber();
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

	public void pollAndUpdateNewestBookingNumber() throws DataAccessException {
		if (bookingNumber != bookingController.getNewestBookingNumber()) {
			timeChoiceMonitor.notifyAllThreads();
			bookingNumber = bookingController.getNewestBookingNumber();
		}
	}
	
	public void setTimeChoiceOpen(boolean open) {
		timeChoiceOpen = false;
	}
}
