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
    
    private Flight[] flights;
    
    private int type;

    @FXML
    void byAirline(ActionEvent event) {
    	clear();
    	Comparator<Flight> flightComparator = new FlightAirlineComparator();
		
		Arrays.sort(flights, flightComparator);
		fill();
    }

    @FXML
    void byCity(ActionEvent event) {
    	clear();
    	Comparator<Flight> flightComparator = new FlightCityComparator();
		
		Arrays.sort(flights, flightComparator);
		fill();
    }

    @FXML
    void byDate(ActionEvent event) {
    	clear();
    	Comparator<Flight> flightComparator = new FlightDateComparator();
		
		Arrays.sort(flights, flightComparator);
		fill();
    }

    @FXML
    void byDoor(ActionEvent event) {
    	clear();
    	Comparator<Flight> flightComparator = new FlightDoorComparator();
		
		Arrays.sort(flights, flightComparator);
		fill();
    }

    @FXML
    void byNumber(ActionEvent event) {
    	clear();
    	Arrays.sort(flights);
    	fill();
    }

    @FXML
    void generate(ActionEvent event) {
    	clear();
    	flights = new Flight[Integer.parseInt(maxFlights.getText())];
    	for(int i=0; i<Integer.parseInt(maxFlights.getText()); i++) {
    		
    		Random rNumber = new Random();
    		String number = rNumber.nextInt(9999)+"";
    		
    		Random rCity = new Random();
    		int option = rCity.nextInt(5)+1;
    		
    		Flight current = new Flight(createDate(), createHour(), createAirline(), number, createCity(option), option);
    		flights[i] = current;
    	}
    	
    	Comparator<Flight> flightComparator = new FlightDateComparator();
		
		Arrays.sort(flights, flightComparator);
    	fill();
    	
    	order.setDisable(false);
    }
    
    public void fill() {
    	if(page.getText().equals("1") && flights.length<=17) {
    		for(int i=0; i<flights.length; i++) {
    			Label airline = new Label(flights[i].getAirline());
    			Label number = new Label (flights[i].getNumber()+"");
    			Label city = new Label (flights[i].getCity());
    			Label date = new Label (flights[i].getDate()+"");
    			Label hour = new Label (flights[i].getHour()+"");
    			Label door = new Label (flights[i].getDoor()+"");
    		
    			airlineCl.getChildren().add(airline);
    			numberCl.getChildren().add(number);
    			cityCl.getChildren().add(city);
    			dateCl.getChildren().add(date);
    			hourCl.getChildren().add(hour);
    			doorCl.getChildren().add(door);
    		}
    	}else if(flights.length>17) {
    		int pages = (flights.length/17)+1;
    		for(int j=0; j<=pages; j++) {
    			if(j+1 == Integer.parseInt(page.getText())) {
    				for(int i=(17*j); i<17+(17*j) && i<flights.length; i++) {
    					Label airline = new Label(flights[i].getAirline());
    					Label number = new Label (flights[i].getNumber()+"");
    					Label city = new Label (flights[i].getCity());
    					Label date = new Label (flights[i].getDate()+"");
    					Label hour = new Label (flights[i].getHour()+"");
    					Label door = new Label (flights[i].getDoor()+"");
    					
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
		int option = rAirline.nextInt(5)+1;
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
    	String text = searchText.getText();
    	boolean stop = false;
    	Flight current = flights[0];
    	clear();
    	if(type == 1) {
    		for(int i=0; i<flights.length && !stop; i++) {
    			if(flights[i].getAirline().equals(text)) {
    				current = flights[i];
    				stop = true;
    			}
    		}
    		flights = new Flight[1];
    		flights[0] = current;
    		fill();
    		
    	}else if(type == 2) {
    		for(int i=0; i<flights.length && !stop; i++) {
    			if(flights[i].getCity().equals(text)) {
    				current = flights[i];
    				stop = true;
    			}
    		}
    		flights = new Flight[1];
    		flights[0] = current;
    		fill();
    		
    	}else if(type == 3) {
    		//Date
    		
    		
    	}else if(type == 4) {
    		for(int i=0; i<flights.length && !stop; i++) {
    			if(flights[i].getDoor() == Integer.parseInt(text)) {
    				current = flights[i];
    				stop = true;
    			}
    		}
    		flights = new Flight[1];
    		flights[0] = current;
    		fill();
    		
    	}else if(type == 5) {
    		//Hour
    		
    		
    	}else {
    		for(int i=0; i<flights.length && !stop; i++) {
    			if(flights[i].getNumber().equals(text)) {
    				current = flights[i];
    				stop = true;
    			}
    		}
    		flights = new Flight[1];
    		flights[0] = current;
    		fill();
    	}
    }

    @FXML
    void previus(ActionEvent event) {
    	int newPage = Integer.parseInt(page.getText())-1;
    	page.setText(newPage+"");
    	clear();
    	fill();
    }
    
    @FXML
    void next(ActionEvent event) {
    	int newPage = Integer.parseInt(page.getText())+1;
    	page.setText(newPage+"");
    	clear();
    	fill();
    }

    @FXML
    void initialize() {
    	
    }
    
}

