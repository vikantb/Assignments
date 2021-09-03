package flight.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import connection.Connector;
import entities.Flight;

/**
 * store all the flights present in the files
 * 
 * @author vikantbhati
 *
 */
public class FlightStore {

	Connector connector = new Connector();

	/**
	 * search the flight in the databse
	 * 
	 * @return list of availbale Flights
	 */
	public List<Flight> getAvailableFlights(Flight searchFlight, String outputPrefernce) {
		List<Flight> availableFlights=new ArrayList<>();
		
		Criteria criteria = connector.getCriteria();
		setCriteria(criteria, searchFlight, outputPrefernce);

		for( Object flight : criteria.list() ) {
			availableFlights.add( (Flight) flight) ;
		}
		
		return availableFlights;
	}

	/**
	 * add the new set of flights to the databse
	 * 
	 * @param newFlights
	 */
	public void addFlights(List<Flight> newFlights) {
		Session session = connector.getSession();
		Transaction transaction = session.beginTransaction();

		for (Flight flight : newFlights) {
			Flight targetFlight = session.get(Flight.class, flight.getFlightNum());
			if (targetFlight == null)
				session.save(flight);
		}
		transaction.commit();
	}

	/**
	 * Delete the set of Flights from the databse
	 * 
	 * @param newFlights
	 */
	public void deleteFlights(List<Flight> newFlights) {
		Session session = connector.getSession();
		Transaction transaction = session.beginTransaction();

		for (Flight flight : newFlights) {
			Flight targetFlight = session.get(Flight.class, flight.getFlightNum());
			if (targetFlight != null)
				session.delete(targetFlight);
		}
		transaction.commit();
	}

	/**
	 * set the filter restrictions to get result set
	 * 
	 * @param flight
	 * @param criteria
	 */
	private void setCriteria(Criteria criteria, Flight flight, String outputPrefernce) {
		String depLoc = flight.getDepLoc();
		String arrLoc = flight.getArrLoc();
		Date validTill = flight.getValidTill();
		char flightClass = flight.getFlightClass();
		char seatAvailability = 'Y';

		criteria.add(Restrictions.eq("depLoc", depLoc));
		criteria.add(Restrictions.eq("arrLoc", arrLoc));
		criteria.add(Restrictions.ge("validTill", validTill));
		criteria.add(Restrictions.eq("flightClass", flightClass));
		criteria.add(Restrictions.eq("seatAvailability", seatAvailability));
		
		criteria.addOrder(Order.asc("fare"));

		// if the outputPrefernce is ffd than sort on both fare and flightDuration
		if (outputPrefernce.equals("ffd")) 
			criteria.addOrder(Order.asc("flightDuration"));
	}

}
