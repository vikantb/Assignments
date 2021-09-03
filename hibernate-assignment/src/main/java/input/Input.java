package input;

import java.util.Scanner;
import output.Output;
import validation.Validation;

/**
 * All the methods that take input from the users
 * 
 * @author vikantbhati
 *
 */
public class Input {
	Scanner scanner = new Scanner(System.in);
	Output out = new Output();
	Validation valid = new Validation();
	String present;

	/**
	 * get the Departure location
	 * 
	 * @return
	 */
	public String getDepartureLocation() {
		out.printMessage("Departure location (ex- MUB,DEL)");
		while (true) {
			present = scanner.next();
			if (valid.checkLocation(present))
				return present;
		}
	}

	/**
	 * get the Arrival location
	 * 
	 * @return
	 */
	public String getArrivalLocation() {
		out.printMessage("Arrival location (ex- MUB,DEL)");
		while (true) {
			present = scanner.next();
			if (valid.checkLocation(present))
				return present;
		}
	}

	/**
	 * get the date of flight
	 * 
	 * @return
	 */
	public String getDate() {
		out.printMessage("Date (ex- day-month-year) format");
		while (true) {
			present = scanner.next();
			if (valid.checkDate(present))
				return present;
		}
	}

	/**
	 * get the flight class type
	 * 
	 * @return
	 */
	public char getFlightClass() {
		out.printMessage(" flight Class (ex- E or B)");
		while (true) {
			present = scanner.next();
			if (valid.checkFlightClass(present))
				return present.charAt(0);
		}
	}

	/**
	 * get the output prefernce
	 * 
	 * @return type <b>String</b>
	 */
	public String getOutputPreference() {
		out.printMessage("Output Preference (ex- fare or ffd (fare and flight duration) )");
		while (true) {
			present = scanner.next();
			if (valid.checkOutputPreference(present))
				return present;
		}
	}

	/**
	 * use to return the user answer <br>
	 * in <b> y </b> or <b> n </b> format
	 * 
	 * @return type <b>String</b>
	 */
	public String askForAnotherSearch() {
		out.printAskMessage("Do you want to search for another Flight");
		while (true) {
			present = scanner.next();
			if (valid.checkAns(present))
				return present;
		}
	}
}
