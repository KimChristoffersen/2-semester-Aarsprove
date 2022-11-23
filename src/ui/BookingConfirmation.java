package ui;

import javax.swing.JPanel;
import javax.swing.JButton;

public class BookingConfirmation extends JPanel {

	private MainUI mainUI;
	/**
	 * Create the panel.
	 */
	public BookingConfirmation(MainUI mainUI) {
		this.mainUI = mainUI;
		
		JButton btnNewButton = new JButton("BOOKINGCONFIRMATION");
		add(btnNewButton);
		
	}

}
