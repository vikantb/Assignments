package load.file;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import entities.Flight;
import output.Output;

/**
 * use to load and fetch the data from the file
 * 
 * @author vikantbhati
 *
 */
public class FileLoader {

	/**
	 * load the file by filename from the resource directory
	 * 
	 * @param fileName
	 * @return set of flights in a file
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<Flight> loadFile(String fileName) throws IOException, ParseException {

		List<Flight> flights = new ArrayList<Flight>();
		Output print = new Output();

		try {

			ClassLoader classLoader = getClass().getClassLoader();

			FileReader file = new FileReader(classLoader.getResource(fileName).getFile());

			final CSVParser parser = new CSVParserBuilder().withSeparator('|').withIgnoreQuotations(true).build();
			final CSVReader reader = new CSVReaderBuilder(file).withSkipLines(1).withCSVParser(parser).build();

			String[] s;

			while ((s = reader.readNext()) != null) {

				String flightNum = s[0];
				String depLoc = s[1];
				String arrLoc = s[2];
				Date date = new SimpleDateFormat("dd-MM-yyyy").parse(s[3]);
				String flightTime = s[4];
				double flightDuration = Double.valueOf(s[5]);
				double fare = Double.valueOf(s[6]);
				char seatAvailability = s[7].charAt(0);
				char flightClass = (s[8].equalsIgnoreCase("E")) ? 'E' : 'B';

				if (flightClass == 'B') {
					fare = 1.4 * fare;
				}

				flights.add(new Flight(flightNum, depLoc, arrLoc, date, flightTime, flightDuration, fare,
						seatAvailability, flightClass));
			}
		} catch (Exception e) {
			print.printFileNotFoundError(fileName);
		}

		return flights;
	}

	/**
	 * load all the flights of all available files at resource directory
	 * 
	 * @return set of Flight
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<Flight> loadAllFiles() throws IOException, ParseException {
		List<Flight> flights = new ArrayList<Flight>();

		Path resourceDirectory = Paths.get("src", "main", "resources");
		String path = resourceDirectory.toFile().getAbsolutePath();

		File dir = new File(path);

		File[] allFiles = dir.listFiles();

		for (File file : allFiles) {
			flights.addAll(loadFile(file.getName()));
		}

		return flights;
	}
}
