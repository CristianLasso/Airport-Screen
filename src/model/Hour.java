package model;

public class Hour {
	public final static String AM = "AM";
	public final static String PM = "PM";
	
	private int hour;
	private int minute;
	private String moment;
	
	public Hour(int hour, int minute, String moment) {
		this.hour = hour;
		this.minute = minute;
		this.moment = moment;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public String getMoment() {
		return moment;
	}

	public void setMoment(String moment) {
		this.moment = moment;
	}
	
	@Override
	public String toString() {
		String nMinute = "";
		if(minute<10) {
			nMinute = "0"+minute;
		}else {
			nMinute = minute+"";
		}
		String object = hour + ":" + nMinute + " " + moment;
		
		return object;
	}

}
