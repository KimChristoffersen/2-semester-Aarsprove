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
		// System.out.println("Here we go again.... Yeeehaaaa!");
		notifyAll();
	}
	
	public synchronized void waitMethod() {
		try {
			// System.out.println("zZzzzZzzZZzz");
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
