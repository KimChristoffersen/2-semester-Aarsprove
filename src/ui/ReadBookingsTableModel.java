package ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import controller.BookingController;
import model.Booking;

/**
 * Class for CustomerController.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class ReadBookingsTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private List<Booking> data;
//	private BookingController bookingController;
	private static final String[] NAMES = {"Bookingnummer","Dato", "Tid", "Pris"};
	
	public ReadBookingsTableModel() {
		data = new ArrayList<>();
//		this.bookingController = bookingController;
	}
	
	public void setData(List<Booking> bookings) {
		this.data = bookings;
		super.fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int col) {
		return NAMES[col];
	}
	
	@Override
	public int getRowCount() { 
		return data.size();
	}
 	
	@Override
	public int getColumnCount() {
		return NAMES.length;
	}
	
	public String getValueAt(int row, int col) {
		DecimalFormat dc = new DecimalFormat("0.00");
		Booking booking = data.get(row);
		switch(col) {
			case 0: return booking.getBookingNumber() + "";
			case 1: return booking.getDate() + "";
			case 2: return booking.getTime() + "";
			case 3: return dc.format(booking.getPriceTotal())+" kr.";
			default: return "Something went odd in the pipe";
		}
	}

	public Booking getLine(int row) {
		return data.get(row);
	}
}