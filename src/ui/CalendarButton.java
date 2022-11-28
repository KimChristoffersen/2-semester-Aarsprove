package ui;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

import model.Instructor;
import model.ShootingRange;

public class CalendarButton extends JButton {

	private static final long serialVersionUID = 1L;
	private String label;
	private LocalDate date;
	private int time;
	private String buttonType;
	private List<ShootingRange> shootingRanges = new ArrayList<>();
	private List<Instructor> instructors = new ArrayList<>();

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

	public void removeShootingRange(int id) {
		shootingRanges.remove(id);
	}

	public void removeInstructor(int id) {
		instructors.remove(id);
	}

	// GETTERS
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
	
	public List<ShootingRange> getShootingRanges() {
		return shootingRanges;
	}
	
	public List<Instructor> getInstructors() {
		return instructors;
	}
	
	// SETTERS
	public void setTime(int time) {
		this.time = time;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setShootingRanges(List<ShootingRange> shootingRanges) {
		this.shootingRanges = shootingRanges;
	}

	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}

}
