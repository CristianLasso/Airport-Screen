package model;

import java.util.Comparator;

public class FlightDateComparator implements Comparator<Flight>{

	@Override
	public int compare(Flight f1, Flight f2) {
		int comparation;
		int year1 = f1.getDate().getYear();
		int month1 = f1.getDate().getMonth();
		int day1 = f1.getDate().getDay();
		String moment1 = f1.getHour().getMoment();
		int hour1 = f1.getHour().getHour();
		int minute1 = f1.getHour().getMinute();
		
		int year2 = f2.getDate().getYear();
		int month2 = f2.getDate().getMonth();
		int day2 = f2.getDate().getDay();
		String moment2 = f2.getHour().getMoment();
		int hour2 = f2.getHour().getHour();
		int minute2 = f2.getHour().getMinute();
		
		if(year1<year2) {
			comparation = -1;
		}else if(year1>year2) {
			comparation = 1;
		}else if(month1<month2) {
			comparation = -1;
		}else if(month1>month2) {
			comparation = 1;
		}else if(day1<day2) {
			comparation = -1;
		}else if(day1>day2) {
			comparation = 1;
		}else if(moment1.compareTo(moment2)<0) {
			comparation = -1;
		}else if(moment1.compareTo(moment2)>0) {
			comparation = 1;
		}else if(hour1<hour2) {
			comparation = -1;
		}else if(hour1>hour2) {
			comparation = 1;
		}else if(minute1<minute2) {
			comparation = -1;
		}else if(minute1>minute2) {
			comparation = 1;
		}else {
			comparation = 0;
		}
			
		return comparation;
	}

}