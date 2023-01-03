package ui;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import controller.BookingController;
import database.DataAccessException;
/**
 * Class for Start.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class Start extends JPanel {
	private MainUI mainUI;
	private ReadBookingsUI readBookingsUI;
	/**
	 * Create the panel.
	 */
	public Start(MainUI mainUI) throws DataAccessException {	
		this.mainUI = mainUI;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.GRAY);
		add(panelTop, BorderLayout.NORTH);
		
		JLabel lblMenuChoice = new JLabel("Menu");
		lblMenuChoice.setForeground(Color.WHITE);
		lblMenuChoice.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuChoice.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelTop.add(lblMenuChoice);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBorder(new LineBorder(new Color(255, 255, 255), 10));
		panelCenter.setBackground(Color.WHITE);
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(2, 2, 10, 10));
		
		JButton btnBooking = new JButton("<html><center><b>BOOKING AF BANE<br></b> <br />"+ "" +"</center></html>");
		btnBooking.setFocusable(false);
		btnBooking.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBooking.setForeground(Color.WHITE);
		btnBooking.setBackground(new Color(0, 94, 150));
		panelCenter.add(btnBooking);
		
		JButton btnBookingWithInstructor = new JButton("<html><center><font size=\"4\"><b>BOOKING AF BANE<br></font></b>"+ "med instrukt\u00F8r<br />" +"</center></html>");
		btnBookingWithInstructor.setFocusable(false);
		btnBookingWithInstructor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBookingWithInstructor.setForeground(Color.WHITE);
		btnBookingWithInstructor.setBackground(new Color(0, 94, 150));
		panelCenter.add(btnBookingWithInstructor);
		
		JButton btnMyAccount = new JButton("<html><center><font size=\"4\"><b>MIN KONTO</b></font><br /><br /></center></html>");
		btnMyAccount.setFocusable(false);
		btnMyAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMyAccount.setForeground(Color.WHITE);
		btnMyAccount.setBackground(new Color(0, 142, 138));
		panelCenter.add(btnMyAccount);
		
		JButton btnCreateYearCard = new JButton("<html><center><font size=\"4\"><b>K\u00D8B \u00C5RSKORT</b></font><br /><br /></center></html>");
		btnCreateYearCard.setFocusable(false);
		btnCreateYearCard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreateYearCard.setForeground(Color.WHITE);
		btnCreateYearCard.setBackground(new Color(0, 142, 138));
		panelCenter.add(btnCreateYearCard);
		
		JButton btnBooking_BookingWithLentWeapon = new JButton("<html><center><font size=\"4\"><b>BOOKING AF BANE</b></font><br> med l\u00E5nev\u00E5ben <br /><br /></center></html>");
		btnBooking_BookingWithLentWeapon.setFocusable(false);
		btnBooking_BookingWithLentWeapon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBooking_BookingWithLentWeapon.setForeground(Color.WHITE);
		btnBooking_BookingWithLentWeapon.setBackground(new Color(0, 94, 150));
		panelCenter.add(btnBooking_BookingWithLentWeapon);
		
		JButton btnBooking_BookingWithInstructorAndLentWeapon = new JButton("<html><center><font size=\"4\"><b>BOOKING AF BANE</b><br></font> med instrukt\u00F8r <br> og l\u00E5nev\u00E5ben  </center></html>");
		btnBooking_BookingWithInstructorAndLentWeapon.setFocusable(false);
		btnBooking_BookingWithInstructorAndLentWeapon.addActionListener(e -> {
			try {
				bookingWithInstructorAndWeapon();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DataAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnBooking_BookingWithInstructorAndLentWeapon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBooking_BookingWithInstructorAndLentWeapon.setForeground(Color.WHITE);
		btnBooking_BookingWithInstructorAndLentWeapon.setBackground(new Color(0, 94, 150));
		panelCenter.add(btnBooking_BookingWithInstructorAndLentWeapon);
		
		JButton btnMyBookings = new JButton("<html><center><font size=\"4\"><b>MINE BOOKINGER<br /><br /><br /></b></font></center></html>");
		btnMyBookings.addActionListener(e -> {
			try {
				readBookings();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DataAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnMyBookings.setFocusable(false);
		btnMyBookings.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMyBookings.setForeground(Color.WHITE);
		btnMyBookings.setBackground(new Color(0, 142, 138));
		panelCenter.add(btnMyBookings);
		
		JButton btnShop = new JButton("<html><center><font size=\"4\"><b>BUTIK</b><br><font size=\"4\"><br /><br /></font> </center></html>");
		btnShop.setFocusable(false);
		btnShop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShop.setForeground(Color.WHITE);
		btnShop.setBackground(new Color(0, 142, 138));
		panelCenter.add(btnShop);
	}

	private void readBookings() throws SQLException, DataAccessException {
		readBookingsUI = new ReadBookingsUI();
		readBookingsUI.setVisible(true);
		
	}

	private void bookingWithInstructorAndWeapon() throws SQLException, DataAccessException {
		mainUI.createBookingWithWeaponAndInstructor();
	}
}