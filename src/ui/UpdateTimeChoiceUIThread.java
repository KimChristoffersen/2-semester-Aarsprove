package ui;

import java.sql.SQLException;

import database.DataAccessException;
/**
 * Class for UpdateTimeChoiceUIThread.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
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
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
