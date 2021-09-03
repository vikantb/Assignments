package flight.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import manage.flights.Flight;

/**
 * store all the flights present in the files
 * 
 * @author vikantbhati
 *
 */
public class FlightStore {

	HashMap<String, HashMap<Character, HashSet<Flight>>> allFlights = new HashMap<>();

	/**
	 * search the flight in the store data
	 * 
	 * @return list of availbale Flights
	 */
	public ArrayList<Flight> getAvailableFlights(Flight searchFlight) {
		HashSet<Flight> searchAbleFlights = getSearchAbleFlightList(searchFlight);
		ArrayList<Flight> availableFlights = new ArrayList<Flight>();

		for (Flight flight : searchAbleFlights) {
			if (match(flight, searchFlight)) {
				availableFlights.add(flight);
			}
		}

		return availableFlights;
	}

	/**
	 * set of Flights that to be search with the searchable flight
	 * 
	 * @param searchFlight
	 * @return ArrayList<Flight>
	 */
	private HashSet<Flight> getSearchAbleFlightList(Flight searchFlight) {
		String locationKey = getLocationKey(searchFlight);
		char flightClass = searchFlight.getFlightClass();
		try {
			return allFlights.get(locationKey).get(flightClass);
		} catch (Exception e) {
			return new HashSet<>();
		}
	}

	/**
	 * check weather the two Flight Object match or not
	 * 
	 * @param flight1
	 * @param flight2
	 * @return
	 */
	private boolean match(Flight flight1, Flight flight2) {
		if (flight1 == flight2)
			return true;
		if (flight1.getValidTill() == null) {
			if (flight2.getValidTill() != null)
				return false;
		} else if (flight1.getValidTill().before(flight2.getValidTill()))
			return false;
		if (flight1.getSeatAvailability() == 'N')
			return false;
		return true;
	}

	/**
	 * add the new set of flights to the allFlights
	 * 
	 * @param newFlights
	 */
	public void updateTypeCreate(HashSet<Flight> newFlights) {
		for (Flight flight : newFlights) {
			String locationKey = getLocationKey(flight);
			char flightClass = flight.getFlightClass();
			if (allFlights.containsKey(locationKey)) {
				HashMap<Character, HashSet<Flight>> newHash = allFlights.get(locationKey);
				if (newHash.containsKey(flightClass)) {
					newHash.get(flightClass).add(flight);
				} else {
					HashSet<Flight> newSet = new HashSet<>();
					newSet.add(flight);
					newHash.put(flightClass, newSet);
				}
			} else {
				HashMap<Character, HashSet<Flight>> newHash = new HashMap<>();
				HashSet<Flight> newSet = new HashSet<>();
				newSet.add(flight);
				newHash.put(flightClass, newSet);

				allFlights.put(locationKey, newHash);
			}
		}
	}

	/**
	 * Delete the set of Flights from the allFlights
	 * 
	 * @param newFlights
	 */
	public void updateTypeDelete(HashSet<Flight> newFlights) {
		for (Flight flight : newFlights) {
			String locationKey = getLocationKey(flight);
			char flightClass = flight.getFlightClass();
			allFlights.get(locationKey).get(flightClass).remove(flight);
		}
	}

	/**
	 * return the locationKey by fetching from the Flight Object
	 * 
	 * @param flight
	 * @return
	 */
	private String getLocationKey(Flight flight) {
		return flight.getDepLoc() + "-" + flight.getArrLoc();
	}
}
