package validation;

import output.Output;

/**
 * this class have all the method that check or <br>
 * validate the given value is valid or not
 * 
 * @author vikantbhati
 */
public class Validation {

	Output out = new Output();

	/**
	 * check weather the given Departure Location value is valid format or not
	 * 
	 * @param departureLocation
	 * @return
	 */
	public boolean checkLocation(String location) {
		if ((location != null) && (!location.equals("")) && (location.matches("^[a-zA-Z]{3}$"))) {
			return true;
		}
		out.printError("location");
		return false;
	}

	/**
	 * check weather the given date is in valid format or not
	 * 
	 * @param date
	 * @return
	 */
	public boolean checkDate(String date) {
		if ((date != null) && (!date.equals(""))
				&& (date.matches("^(3[01]|[12][0-9]|0?[1-9])-(1[0-2]|0?[1-9])-(?:[0-9]{2})?[0-9]{2}$"))) {
			return true;
		}
		out.printError("Date");
		return false;
	}

	/**
	 * check weather the given flight Class is in valid or not
	 * 
	 * @param flightClass
	 * @return
	 */
	public boolean checkFlightClass(String flightClass) {
		if (flightClass.equalsIgnoreCase("E") || flightClass.equalsIgnoreCase("B"))
			return true;
		out.printError("flight Class");
		return false;
	}

	/**
	 * check weather the given Output Preference is in valid or not
	 * 
	 * @param outputPreference
	 * @return
	 */
	public boolean checkOutputPreference(String outputPreference) {
		if (outputPreference.equalsIgnoreCase("fare") || outputPreference.equalsIgnoreCase("ffd"))
			return true;
		out.printError("Output Preference");
		return false;
	}
	
	/**
	 * check weather the given string value is <b>y</b> or <b>n</b>
	 * 
	 * @param type </b>string</b>
	 * @return type </b>boolean</b>
	 */
	public boolean checkAns(String ans) {
		if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n"))
			return true;
		out.printError("Answer (y/n) only");
		return false;
	}
}
