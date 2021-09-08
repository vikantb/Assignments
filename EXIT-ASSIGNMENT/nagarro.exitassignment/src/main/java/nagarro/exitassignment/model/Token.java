package nagarro.exitassignment.model;

import org.springframework.stereotype.Component;

/**
 * token model
 * 
 * @author vikantbhati
 *
 */
@Component
public class Token {

	private String id;

	/**
	 * @return the tokken
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param tokken the tokken to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Token [id=" + id + "]";
	}

}
