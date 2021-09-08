package nagarro.exitassignment.model;

import org.springframework.stereotype.Component;

/**
 * model to send the response as object
 * 
 * @author vikantbhati
 *
 */
@Component
public class Message {

	private String message;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
