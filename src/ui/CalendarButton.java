package ui;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
/**
 * Class for CalendarButton.
 *
 * @author (DMA-CSD-V221-Gruppe 1)
 */
public class CalendarButton extends JButton {
	private static final long serialVersionUID = 1L;
	private String label;
	private LocalDate date;
	private int time;
	private String buttonType;
	private List<Integer> shootingRanges = new ArrayList<>();
	private List<Integer> instructors = new ArrayList<>();
	private List<Integer> weapons = new ArrayList<>();

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

	public List<Integer> getAvailableShootingRanges() {
		return shootingRanges;
	}

	public List<Integer> getAvailableInstructors() {
		return instructors;
	}

	public List<Integer> getAvailableWeapons() {
		return weapons;
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

	public void setAvailableShootingRanges(List<Integer> shootingRanges) {
		this.shootingRanges = shootingRanges;
	}

	public void setAvailableInstructors(List<Integer> instructors) {
		this.instructors = instructors;
	}

	public void setAvailableWeapons(List<Integer> weapons) {
		this.weapons = weapons;
	}
}
