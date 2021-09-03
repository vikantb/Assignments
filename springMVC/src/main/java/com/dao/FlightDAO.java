package com.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.entities.Flight;
import com.entities.UserInput;

/**
 * FlightDAO that directly intract with the database using hibernateTemplate and
 * execute all query related to the database
 * 
 * @author vikantbhati
 *
 */
@Component
public class FlightDAO {
	@Autowired
	HibernateTemplate hibernateTemplate;

	/**
	 * list of available flights in the database by filtering according to the user
	 * @param input
	 * @return
	 * @throws ParseException
	 */
	public List<Flight> getFlights(UserInput input) throws ParseException {

		DetachedCriteria criteria = DetachedCriteria.forClass(Flight.class);
		setCriteria(criteria, input);

		return (List<Flight>) hibernateTemplate.findByCriteria(criteria);
	}

	/**
	 * set the filter restrictions to get result set
	 * 
	 * @param flight
	 * @param criteria
	 * @throws ParseException
	 */
	private void setCriteria(DetachedCriteria criteria, UserInput input) throws ParseException {
		String depLoc = input.getDepLoc();
		String arrLoc = input.getArrLoc();
		Date validTill = new SimpleDateFormat("dd-MM-yyyy").parse(input.getDate());
		char flightClass = input.getFlightClass().charAt(0);
		char seatAvailability = 'Y';

		criteria.add(Restrictions.eq("depLoc", depLoc));
		criteria.add(Restrictions.eq("arrLoc", arrLoc));
		criteria.add(Restrictions.ge("validTill", validTill));
		criteria.add(Restrictions.eq("flightClass", flightClass));
		criteria.add(Restrictions.eq("seatAvailability", seatAvailability));

		if (input.getOp().equals("fare"))
			criteria.addOrder(Order.asc("fare"));
		else
			criteria.addOrder(Order.asc("flightDuration"));

	}
}
