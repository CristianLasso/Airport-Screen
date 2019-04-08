package ui;

import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
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
    
    private Flight[] flights;

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
    	flights = new Flight[Integer.parseInt(maxFlights.getText())];
    	for(int i=0; i<Integer.parseInt(maxFlights.getText()); i++) {
    		
    		Random rNumber = new Random();
    		String number = rNumber.nextInt(9999)+"";
    		
    		Random rCity = new Random();
    		int option = rCity.nextInt(5)+1;
    		
    		Flight current = new Flight(createDate(), createHour(), createAirline(), number, createCity(option), option);
    		flights[i] = current;
    		System.out.println("Generando "+i);
    	}
    	
    	Comparator<Flight> flightComparator = new FlightDateComparator();
		
		Arrays.sort(flights, flightComparator);
    	fill();
    	
    	generate.setDisable(true);
    	order.setDisable(false);
    }
    
    public void fill() {
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
    		System.out.println("Llenando "+i);
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
    void initialize() {
    	
    }
    
}

