package ui;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

public class CalendarButton extends JButton {

	private static final long serialVersionUID = 1L;
	private String label;
	private LocalDate date;
	private int time;
	private String buttonType;
	private List<Integer> availableShootingRanges = new ArrayList<>();
	private List<Integer> availableInstructors = new ArrayList<>();

	public CalendarButton(String label, LocalDate date, String buttonType) { // WEEK NAMES
		this.label = label;
		this.date = date;
		this.buttonType = buttonType;
		this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
		this.setEnabled(false);
	}

	public CalendarButton(String label, LocalDate date, int time, String buttonType) { // TIMESLOTS
		this.label = label;
		this.date = date;
		this.time = time;
		this.buttonType = buttonType;
		this.setText(label);
		this.setForeground(Color.BLACK);
		this.setBackground(new Color(180, 200, 220));
	}

	public void addAvailableShootingRange(int id) {
		availableShootingRanges.add(id);
	}

	public void addAvailableInstructors(int id) {
		availableInstructors.add(id);
	}

	public void removeAvailableShootingRange(int id) {
		availableShootingRanges.remove(id);
	}

	public void removeAvailableInstructors(int id) {
		availableInstructors.remove(id);
	}

	// GETTERS & SETTERS
	public String getLabel() {
		return label;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getTime() {
		return time;
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Integer> getAvailableShootingRanges() {
		return availableShootingRanges;
	}

	public void setAvailableShootingRanges(List<Integer> availableShootingRanges) {
		this.availableShootingRanges = availableShootingRanges;
	}

	public List<Integer> getAvailableInstructors() {
		return availableInstructors;
	}

	public void setAvailableInstructors(List<Integer> availableInstructors) {
		this.availableInstructors = availableInstructors;
	}

}
