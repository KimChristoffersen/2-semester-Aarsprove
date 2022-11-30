package ui;

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
		System.out.println("Here we go again.... Yeeehaaaa!");

	}
	
	public synchronized void waitMethod() {
		try {
			wait();
			System.out.println("zZzzzZzzZZzz");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
