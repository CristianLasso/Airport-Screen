package ui;

import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.*;
import model.Date;

public class ScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Menu order;

    @FXML
    private TextField maxFlights;

    @FXML
    private Button generate;
    
    @FXML
    private VBox airlineCl;

    @FXML
    private Label airline;

    @FXML
    private VBox numberCl;

    @FXML
    private Label number;

    @FXML
    private VBox cityCl;

    @FXML
    private Label city;

    @FXML
    private VBox dateCl;

    @FXML
    private Label date;

    @FXML
    private VBox hourCl;

    @FXML
    private Label hour;

    @FXML
    private VBox doorCl;

    @FXML
    private Label door;
    
    @FXML
    private MenuButton searchType;

    @FXML
    private TextField searchText;

    @FXML
    private Button search;
    
    @FXML
    private Label page;
    
    @FXML
    private Label time;
    
    private Flight firstFlight;
    
    private int type;

    @FXML
    void byAirline(ActionEvent event) {
    	Long time = System.currentTimeMillis();
    	Flight[] flights = getFlightList();
    	clear();
    	Comparator<Flight> flightComparator = new FlightAirlineComparator();
		
		Arrays.sort(flights, flightComparator);
		fill();
		this.time.setText("Time of Operation: "+(System.currentTimeMillis()-time));
    }

    @FXML
    void byCity(ActionEvent event) {
    	Long time = System.currentTimeMillis();
    	Flight[] flights = getFlightList();
    	clear();
    	Comparator<Flight> flightComparator = new FlightCityComparator();
		
		Arrays.sort(flights, flightComparator);
		fill();
		this.time.setText("Time of Operation: "+(System.currentTimeMillis()-time));
    }

    @FXML
    void byDate(ActionEvent event) {
    	Long time = System.currentTimeMillis();
    	Flight[] flights = getFlightList();
    	clear();
    	Comparator<Flight> flightComparator = new FlightDateComparator();
		
		Arrays.sort(flights, flightComparator);
		fill();
		this.time.setText("Time of Operation: "+(System.currentTimeMillis()-time));
    }

    @FXML
    void byDoor(ActionEvent event) {
    	Long time = System.currentTimeMillis();
    	Flight[] flights = getFlightList();
    	clear();
    	Comparator<Flight> flightComparator = new FlightDoorComparator();
		
		Arrays.sort(flights, flightComparator);
		fill();
		this.time.setText("Time of Operation: "+(System.currentTimeMillis()-time));
    }

    @FXML
    void byNumber(ActionEvent event) {
    	Long time = System.currentTimeMillis();
    	Flight[] flights = getFlightList();
    	clear();
    	Arrays.sort(flights);
    	fill();
    	this.time.setText("Time of Operation: "+(System.currentTimeMillis()-time));
    }

    @FXML
    void generate(ActionEvent event) {
    	Long time = System.currentTimeMillis();
    	clear();
    	for(int i=0; i<Integer.parseInt(maxFlights.getText()); i++) {
    		
    		Random rNumber = new Random();
    		String number = rNumber.nextInt(9999)+"";
    			while(number.length()<4) {
    				number = number+"0";
    			}
    		
    		Random rCity = new Random();
    		int option = rCity.nextInt(6);
    		
    		Flight newFlight = new Flight(createDate(), createHour(), createAirline(), number, createCity(option), option+1);
    		
    		if(firstFlight == null) {
    			firstFlight = newFlight;
    		}else {
    			Flight current = firstFlight;
    			while(current.getNextFlight() != null) {
    				current = current.getNextFlight();
    			}
    			current.setNextFlight(newFlight);
    			newFlight.setPreviousFlight(current);
    		}
    	}
    	fill();
    	this.time.setText("Time of Operation: "+(System.currentTimeMillis()-time));
    	
    	order.setDisable(false);
    }
    
    public void fill() throws NullPointerException{

    	
    	int pages = (Integer.parseInt(maxFlights.getText())/17);
    	for(int j=0; j<=pages; j++) {
    		if(j+1 == Integer.parseInt(page.getText())) {
    			for(int i=(17*j); i<17+(17*j) && i<Integer.parseInt(maxFlights.getText()); i++) {
    				Flight current = firstFlight;
    				for(int k=0; k<i && current != null; k++) {
    					current = current.getNextFlight();
    				}
    				Label airline = new Label(current.getAirline());
    				Label number = new Label (current.getNumber()+"");
    				Label city = new Label (current.getCity());
    				Label date = new Label (current.getDate()+"");
    				Label hour = new Label (current.getHour()+"");
    				Label door = new Label (current.getDoor()+"");
    				
    				airlineCl.getChildren().add(airline);
    				numberCl.getChildren().add(number);
    				cityCl.getChildren().add(city);
    				dateCl.getChildren().add(date);
    				hourCl.getChildren().add(hour);
    				doorCl.getChildren().add(door);
    				
    				
    			}
    		}
    	}
    }
    
    public Flight[] getFlightList() {
		Flight[] list = new Flight[Integer.parseInt(maxFlights.getText())];;
		int i = 0;
		Flight current = firstFlight;
		while(current != null) {
			list[i] = current;
			i++;
			current = current.getNextFlight();
		}
		return list;
	}
    
    public void clear() {
    	airlineCl.getChildren().clear();
    	airlineCl.getChildren().add(airline);
    	
    	numberCl.getChildren().clear();
    	numberCl.getChildren().add(number);
    	
    	cityCl.getChildren().clear();
    	cityCl.getChildren().add(city);
    	
    	dateCl.getChildren().clear();
		dateCl.getChildren().add(date);
		
		hourCl.getChildren().clear();
		hourCl.getChildren().add(hour);
		
		doorCl.getChildren().clear();
		doorCl.getChildren().add(door);
    	
    }
    
    public Date createDate() {
    	Random rDay = new Random();
		int day = rDay.nextInt(30)+1;
		Random rMonth = new Random();
		int month = rMonth.nextInt(11)+1;
		Random rYear = new Random();
		int year = rYear.nextInt(7)+2012;
		Date date = new Date(day, month, year);
    	return date;
    }
    
    public Hour createHour() {
    	Random rHour = new Random();
		int h = rHour.nextInt(11)+1;
		Random rMinute = new Random();
		int minute = rMinute.nextInt(59)+1;
		Random rMoment = new Random();
		int option = rMoment.nextInt(2);
		String moment = "";
		if(option == 0) {
			moment = "AM";
		}else {
			moment = "PM";
		}
		Hour hour = new Hour(h, minute, moment);
    	return hour;
    }
    
    public String createCity(int option) {
		String city = "";
		if(option == 0) {
			city = "Madrid";
		}else if(option == 1) {
			city = "Miami";
		}else if(option == 2) {
			city = "Bogotá";
		}else if(option == 3) {
			city = "Londres";
		}else if(option == 4) {
			city = "Berlin";
		}else if(option == 5) {
			city = "Tokyo";
		}
    	return city;
    }
    
    public String createAirline() {
    	Random rAirline = new Random();
		int option = rAirline.nextInt(6);
    	String airline = "";
		if(option == 0) {
			airline = "Avianca";
		}else if(option == 1) {
			airline = "LATAM";
		}else if(option == 2) {
			airline = "VivaColombia";
		}else if(option == 3) {
			airline = "Wingo";
		}else if(option == 4) {
			airline = "Ares";
		}else if(option == 5) {
			airline = "Copa Airlines";
		}
    	return airline;
    }
    
    @FXML
    void airlineSearch(ActionEvent event) {
    	searchType.setText("Airline");
    	type = 1;
    	search.setDisable(false);
    }
    
    @FXML
    void citySearch(ActionEvent event) {
    	searchType.setText("City");
    	type = 2;
    	search.setDisable(false);
    }

    @FXML
    void dateSearch(ActionEvent event) {
    	searchType.setText("Date");
    	type = 3;
    	search.setDisable(false);
    }

    @FXML
    void doorSearch(ActionEvent event) {
    	searchType.setText("Door");
    	type = 4;
    	search.setDisable(false);
    }
    
    @FXML
    void hourSearch(ActionEvent event) {
    	searchType.setText("Hour");
    	type = 5;
    	search.setDisable(false);
    }

    @FXML
    void numberSearch(ActionEvent event) {
    	searchType.setText("Number");
    	type = 6;
    	search.setDisable(false);
    }

    @FXML
    void search(ActionEvent event) {
    	Long time = System.currentTimeMillis();
    	String text = searchText.getText();
    	boolean stop = false;
    	Flight current = firstFlight;
    	clear();
    	if(type == 1) {
    		while(current != null && !stop) {
    			if(current.getAirline().equalsIgnoreCase(text)) {
    				stop = true;
    			}else {
    					current = current.getNextFlight();
    			}
    		}
    		
    		firstFlight = current;
    		firstFlight.setPreviousFlight(null);
    		firstFlight.setNextFlight(null);
    		fill();
    		
    	}else if(type == 2) {
    		while(current != null && !stop) {
    			if(current.getCity().equalsIgnoreCase(text)) {
    				stop = true;
    			}else {
    					current = current.getNextFlight();
    			}
    		}
    		
    		firstFlight = current;
    		firstFlight.setPreviousFlight(null);
    		firstFlight.setNextFlight(null);
    		fill();
    		
    	}else if(type == 3) {
    		while(current != null && !stop) {
    			if(current.getDate().toString().equalsIgnoreCase(text)) {
    				stop = true;
    			}else {
    					current = current.getNextFlight();
    			}
    		}
    		
    		firstFlight = current;
    		firstFlight.setPreviousFlight(null);
    		firstFlight.setNextFlight(null);
    		fill();
    		
    		
    	}else if(type == 4) {
    		while(current != null && !stop) {
    			if(current.getDoor() == Integer.parseInt(text)) {
    				stop = true;
    			}else {
    					current = current.getNextFlight();
    			}
    		}
    		
    		firstFlight = current;
    		firstFlight.setPreviousFlight(null);
    		firstFlight.setNextFlight(null);
    		fill();
    		
    	}else if(type == 5) {
    		while(current != null && !stop) {
    			if(current.getHour().toString().equalsIgnoreCase(text)) {
    				stop = true;
    			}else {
    					current = current.getNextFlight();
    			}
    		}
    		
    		firstFlight = current;
    		firstFlight.setPreviousFlight(null);
    		firstFlight.setNextFlight(null);
    		fill();
    		
    	}else {
    		while(current != null && !stop) {
    			if(current.getNumber().equalsIgnoreCase(text)) {
    				stop = true;
    			}else {
    					current = current.getNextFlight();
    			}
    		}
    		
    		firstFlight = current;
    		firstFlight.setPreviousFlight(null);
    		firstFlight.setNextFlight(null);
    		fill();
    	}
    	this.time.setText("Time of Operation: "+(System.currentTimeMillis()-time));
    }

    @FXML
    void previus(ActionEvent event) {
    	int newPage = Integer.parseInt(page.getText())-1;
    	if(newPage>0) {
    		page.setText(newPage+"");
    		clear();
    		fill();
    	}
    }
    
    @FXML
    void next(ActionEvent event) {
    	int newPage = Integer.parseInt(page.getText())+1;
    	if(newPage<(Integer.parseInt(maxFlights.getText())/17)+2) {
    		page.setText(newPage+"");
    		clear();
    		fill();
    	}
    }

    @FXML
    void initialize() {
    	
    }
    
}

