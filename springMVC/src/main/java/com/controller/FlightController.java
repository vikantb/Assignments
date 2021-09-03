package com.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.Flight;
import com.entities.UserInput;
import com.service.FlightService;

/**
 * FlightController that handle all the request 
 * @author vikantbhati
 *
 */
@Controller
public class FlightController {

	@Autowired
	FlightService serviceProvider;

	/**
	 * home page request
	 * @return
	 */
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	/**
	 * search request to get available flights 
	 * @param userInput
	 * @param m
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchFlights(@ModelAttribute UserInput userInput, Model m) throws ParseException {

		List<Flight> availableFlights = serviceProvider.getAvailableFlights(userInput);

		m.addAttribute("flights", availableFlights);

		return "flights";
	}

}
