package ui;

import javax.swing.JPanel;
import javax.swing.JButton;

public class TimeChoice extends JPanel {

	private MainUI mainUI;
	/**
	 * Create the panel.
	 */
	public TimeChoice(MainUI mainUI) {
		this.mainUI = mainUI;
		
		JButton btnNewButton = new JButton("TIMECHOICE");
		btnNewButton.addActionListener(e -> TimeChoice());
		add(btnNewButton);
	}

	private void TimeChoice() {
		mainUI.bookingConfirmation();
	}
}