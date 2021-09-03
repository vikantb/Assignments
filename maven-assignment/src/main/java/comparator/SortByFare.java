package comparator;

import java.util.Comparator;
import manage.flights.Flight;

public class SortByFare implements Comparator<Flight>{

	@Override
	public int compare(Flight flight1, Flight flight2) {
		if (flight1.getFare() > flight2.getFare())
			return 1;
		return -1;
	}

}
