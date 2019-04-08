package model;

import java.util.Comparator;

public class FlightCityComparator implements Comparator<Flight>{

	@Override
	public int compare(Flight f1, Flight f2) {
		int comparation;
		String city1 = f1.getCity();
		String city2 = f2.getCity();
		
		if(city1.compareTo(city2)<0) {
			comparation = -1;
		}else if(city1.compareTo(city2)>0) {
			comparation = 1;
		}else {
			comparation = 0;
		}
			
		return comparation;
	}

}
