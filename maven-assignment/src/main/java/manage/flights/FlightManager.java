package manage.flights;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import comparator.SortByFare;
import comparator.SortByFareAndFlightDuration;
import flight.storage.FlightStore;
import input.Input;
import load.file.FileLoader;
import output.Output;
import watch.file.FileWatcher;

/**
 * 
 * @author vikantbhati
 *
 */
public class FlightManager {

	FlightStore store = new FlightStore();
	FileLoader loader = new FileLoader();
	Output print = new Output();
	SortByFare sortByFare = new SortByFare();
	SortByFareAndFlightDuration sortByFFD = new SortByFareAndFlightDuration();
	Flight searchFlight;
	String outputPreference;

	public FlightManager() throws IOException, ParseException {
		store.updateTypeCreate(loader.loadAllFiles());
	}

	/**
	 * search the available flights from the database or files
	 * 
	 * @throws ParseException
	 */
	public void searchFlights() throws ParseException {
		startWatchService();

		while (true) {

			Input input = new Input();

			searchFlight = getSearchFlight();

			showAvailableFlights();

			String answer = input.askForAnotherSearch();
			if (answer.equalsIgnoreCase("n"))
				break;
		}
	}

	/**
	 * create the Flight Object that to be search
	 * 
	 * @return Flight to be search
	 * @throws ParseException
	 */
	private Flight getSearchFlight() throws ParseException {
		Input input = new Input();
		String departureLocation = input.getDepartureLocation();
		String arrivalLocation = input.getArrivalLocation();
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(input.getDate());
		char flightClass = input.getFlightClass();
		outputPreference = input.getOutputPreference();

		return new Flight(departureLocation, arrivalLocation, date, flightClass);
	}

	/**
	 * update the available flight data when there is any change in the files or
	 * database
	 * 
	 * @param filename
	 * @throws IOException
	 * @throws ParseException
	 */
	public void update(String updateType, String fileName) throws IOException, ParseException {
		HashSet<Flight> newFlights = loader.loadFile(fileName);

			if (updateType.equalsIgnoreCase("ENTRY_CREATE")) {
				store.updateTypeCreate(newFlights);
			} else if (updateType.equalsIgnoreCase("ENTRY_DELETE")) {
				store.updateTypeDelete(newFlights);
			} else {
				store.updateTypeDelete(newFlights);
				store.updateTypeCreate(loader.loadFile(fileName));
			}

			showAvailableFlights();
	}

	/**
	 * show the all available flights to the user
	 */
	private void showAvailableFlights() {
		ArrayList<Flight> availableFlights = store.getAvailableFlights(searchFlight);
		applyOutputPreference(availableFlights);

		print.print(availableFlights);
	}

	/**
	 * set the output preference according to user
	 */
	private void applyOutputPreference(ArrayList<Flight> availableFlights) {
		if (outputPreference.equalsIgnoreCase("fare")) {
			Collections.sort(availableFlights, sortByFare);
		} else {
			Collections.sort(availableFlights, sortByFFD);
		}
	}

	/**
	 * start new thread to start watch service
	 */
	private void startWatchService() {
		Thread watcherThread = new Thread(() -> {
			FileWatcher watcher = new FileWatcher();
			try {
				watcher.watchService(this);
			} catch (Exception e) {
			}
		});

		watcherThread.start();
	}

}
