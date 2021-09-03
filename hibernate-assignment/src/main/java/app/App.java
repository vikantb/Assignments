package app;

import java.io.IOException;
import java.text.ParseException;

import manage.flights.FlightManager;

public class App {

	public static void main(String[] args) throws IOException, ParseException {
		
		FlightManager manager = new FlightManager();

		manager.searchFlights();

	}

}
