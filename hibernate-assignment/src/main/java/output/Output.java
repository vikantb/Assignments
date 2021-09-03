package output;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entities.Flight;

/**
 * this class have all the methods that <br>
 * display the output,message and error message to the user
 * 
 * @author vikantbhati
 */
public class Output {

	int i;

	/**
	 * print the list of Flight Objects
	 * 
	 * @param list of Flight Objects
	 */
	public void print(List<Flight> list) {
		printHead();
		for (Flight item : list)
			print(item);

		System.out.println(ConsoleColors.BLUE_UNDERLINED + ConsoleColors.CYAN_BOLD
				+ "____________________________________________________________________________________________________________________________"
				+ ConsoleColors.RESET + "\n");
	}

	/**
	 * print the Flight Objects
	 * 
	 * @param item type <b>Flight Object</b>
	 */
	public void print(Flight item) {
		String flightNum = item.getFlightNum();
		String depLoc = item.getDepLoc();
		String arvLoc = item.getArrLoc();

		Date date = item.getValidTill();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		String validTill = sdf.format(date);

		String flightTime = item.getFlightTime();
		double flightDuration = item.getFlightDuration();
		double fare = item.getFare();
		char seatAvailability = item.getSeatAvailability();
		char flightClass = item.getFlightClass();

		System.out.printf("|  %-10s|  %-6s|  %-6s| %-11s|    %-9s|     %-12.2f|  %-9.2f|        %-11s|      %-7s| \n",
				flightNum, depLoc, arvLoc, validTill, flightTime, flightDuration, fare, seatAvailability, flightClass);
	}

	/**
	 * display the message, to enter the value <br>
	 * of the given message
	 * 
	 * @param message
	 */
	public void printMessage(String message) {
		String str = "Please enter the  " + message;
		System.out.println(ConsoleColors.GREEN + str + ConsoleColors.RESET);
	}

	/**
	 * warn and display the error message <br>
	 * 
	 * @param str
	 */
	public void printError(String str) {
		String message = "WARN!!! Enter valid " + str;
		System.out.printf("%10s%10s\n", "", ConsoleColors.RED + message + ConsoleColors.RESET);
	}

	/**
	 * print the message
	 * 
	 * @param str
	 */
	public void printAskMessage(String str) {
		System.out.println(ConsoleColors.PURPLE_BOLD + str + ConsoleColors.RESET);
	}

	/**
	 * display the error message <br>
	 */
	public void printFileNotFoundError(String fileName) {
		String message = "ERROR!!! {" + fileName + "} file Not Found.";
		System.out.printf("%10s%10s\n", "", ConsoleColors.RED + message + ConsoleColors.RESET);
	}

	/**
	 * print the header of the table <br>
	 */
	public void printHead() {

		i = i % 4;
		System.out.println(ConsoleColors.colors[i] + " AVAILABLE FLIGHTS ARE : " + ConsoleColors.RESET);

		System.out.println(ConsoleColors.BLUE_UNDERLINED + ConsoleColors.CYAN_BOLD
				+ "____________________________________________________________________________________________________________________________");

		System.out.printf("| %-11s| %-7s| %-7s| %-11s| %-12s| %-16s|   %-8s| %-18s| %-3s| \n", "Flight Num", "DepLoc",
				"ArvLoc", "Valid Till", "Flight Time", "Flight Duration", "Fare", "Seat Availability", "Flight Class");

		System.out.println(ConsoleColors.RESET);
		i++;
	}

	public void startMessage() {
		System.out
				.printf("\033[0;51m \n" + "copy and paste it ex-> 'DEL MUB 05-12-2013 B fare' \n" + ConsoleColors.RESET);
	}
}
