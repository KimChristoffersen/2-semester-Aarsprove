package ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.BookingController;
import database.DataAccessException;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookingConfirmation extends JPanel {

	private MainUI mainUI;
	private BookingController bookingController;
	/**
	 * Create the panel.
	 */
	public BookingConfirmation(MainUI mainUI, BookingController bookingController) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.GRAY);
		add(panelTop, BorderLayout.NORTH);
		
		JLabel lblBookingConfirmation = new JLabel("Bekr\u00E6ft booking");
		lblBookingConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookingConfirmation.setForeground(Color.WHITE);
		lblBookingConfirmation.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelTop.add(lblBookingConfirmation);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBorder(new LineBorder(new Color(255, 255, 255), 10));
		panelCenter.setBackground(Color.WHITE);
		add(panelCenter);
		panelCenter.setLayout(new GridLayout(2, 1, 10, 10));
		
		JButton btnNewButton = new JButton("Gem booking");
		btnNewButton.addActionListener(e -> {
			try {
				confirmBooking();
			} catch (DataAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		panelCenter.add(btnNewButton);
		
		init(mainUI, bookingController);
	}
	
	private void init(MainUI mainUI, BookingController bookingController) {
		this.mainUI = mainUI;
		this.bookingController = bookingController;
	}
	
	private void confirmBooking() throws DataAccessException {
		bookingController.confirmBooking();
	}

}
