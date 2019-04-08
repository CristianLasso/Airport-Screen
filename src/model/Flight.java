package model;

public class Flight implements Comparable<Flight>{
	private Date date;
	private Hour hour;
	private String airline;
	private String number;
	private String city;
	private int door;
	
	public Flight(Date date, Hour hour, String airline, String number, String city, int door) {
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
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

	@Override
	public int compareTo(Flight otherFlight) {
		int comparation;
		if(number.compareTo(otherFlight.number)<0) {
			comparation = -1;
		}else if(number.compareTo(otherFlight.number)>0) {
			comparation = 1;
		}else {
			comparation = 0;
		}
		return comparation;
	}
	
}
