package ui;

import java.sql.SQLException;

import database.DataAccessException;

public class UpdateTimeChoiceUIThread extends Thread {
	private TimeChoice timeChoice;
	private TimeChoiceMonitor timeChoiceMonitor;

	public UpdateTimeChoiceUIThread(TimeChoice timeChoice, TimeChoiceMonitor timeChoiceMonitor) {
		this.timeChoice = timeChoice;
		this.timeChoiceMonitor = timeChoiceMonitor;
	}

	@Override
	public void run() {
		while (true) {
			timeChoiceMonitor.waitMethod();
			try {
				timeChoice.updateStatus();
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
