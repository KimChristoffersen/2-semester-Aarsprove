package ui;
/**
 * Class for TimeChoiceMonitor.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class TimeChoiceMonitor {
	private static TimeChoiceMonitor instance;

	private TimeChoiceMonitor() {
	}

	public static TimeChoiceMonitor getInstance() {
		if (instance == null) {
			instance = new TimeChoiceMonitor();
		}
		return instance;
	}
	
	public synchronized void notifyAllThreads() {
		notifyAll();
	}
	
	public synchronized void waitMethod() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
