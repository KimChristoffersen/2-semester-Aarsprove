package ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WeaponChoice extends JPanel {

	private MainUI mainUI;
	/**
	 * Create the panel.
	 */
	public WeaponChoice(MainUI mainUI) {
		this.mainUI = mainUI;
		
		JButton btnNewButton = new JButton("WEAPONCHOISE");
		btnNewButton.addActionListener(e -> weaponChoice());
		add(btnNewButton);
		
	}
	private void weaponChoice() {
		mainUI.timeChoice();
	}

}
