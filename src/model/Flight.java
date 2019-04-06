package model;

public class Flight {
	private Date date;
	private Hour hour;
	private String airline;
	private int number;
	private String city;
	private int door;
	
	public Flight(Date date, Hour hour, String airline, int number, String city, int door) {
		this.date = date;
		this.hour = hour;
		this.airline = airline;
		this.number = number;
		this.city = city;
		this.door = door;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Hour getHour() {
		return hour;
	}

	public void setHour(Hour hour) {
		this.hour = hour;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getDoor() {
		return door;
	}

	public void setDoor(int door) {
		this.door = door;
	}
	
}
