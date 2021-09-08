package nagarro.exitassignment.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

/**
 * Entity that store the user and token corrospond to that user that is
 * generated at the time of login and whenever a request after login is make
 * then this token is match with user if found then it allow to access API's
 * 
 * @author vikantbhati
 *
 */
@Entity
@Component
public class Auth {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String token;
	@OneToOne
	private User user;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Auth [token=" + token + ", user=" + user + "]";
	}

}
