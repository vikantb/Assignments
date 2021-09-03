package com.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.FlightDAO;
import com.entities.Flight;
import com.entities.UserInput;

/**
 * all the flight services are provided in FlightService
 * @author vikantbhati
 *
 */
@Component
public class FlightService {
	@Autowired
	FlightDAO flightDao;

	/**
	 * list of available flights according to the user input
	 * @param input
	 * @return
	 * @throws ParseException
	 */
	public List<Flight> getAvailableFlights(UserInput input) throws ParseException {
		 
		return flightDao.getFlights(input);
	}
}
