package watch.file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.ParseException;

import load.file.FileLoader;
import manage.flights.FlightManager;

/**
 * continuously check the resource if there is any change in the files or not
 * 
 * @author vikantbhati
 *
 */
public class FileWatcher {
	FileLoader load = new FileLoader();

	/**
	 * it privide the watch service by continuously check the resource folder if
	 * there is any change
	 * 
	 * @param manager
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void watchService(FlightManager manager) throws IOException, InterruptedException, ParseException {
		WatchService watchService = FileSystems.getDefault().newWatchService();

		Path path = Paths.get("src", "main", "resources");

		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);

		WatchKey key;

		while (true) {
			key = watchService.take(); Thread.sleep(200);
			for (WatchEvent<?> event : key.pollEvents()) { 
				manager.update(event.kind() + "", event.context() + "");
			}
			key.reset();
		}

	}
}
