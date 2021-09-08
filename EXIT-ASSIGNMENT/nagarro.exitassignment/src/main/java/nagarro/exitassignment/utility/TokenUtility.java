package nagarro.exitassignment.utility;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nagarro.exitassignment.entities.Auth;
import nagarro.exitassignment.entities.User;
import nagarro.exitassignment.model.Token;
import nagarro.exitassignment.repository.AuthRepo;

/**
 * all the services related to the Token are declare here
 * 
 * @author vikantbhati
 *
 */
@Service
public class TokenUtility {

	@Autowired
	private AuthRepo authRepo;

	@Autowired
	private Token token;

	@Autowired
	private Auth auth;

	/**
	 * create a unique token for login user
	 * 
	 * @param user
	 * @return
	 */
	public Token createToken(User user) {
		String uuid = UUID.randomUUID().toString();
		token.setId(uuid);

		saveToken(user, token);

		return token;
	}

	/**
	 * check weather the token is valid or not
	 * 
	 * @param token
	 * @return
	 */
	public boolean isValidToken(String token) {
		return getTokenUser(token) != null;
	}

	/**
	 * check weather the user is admin or not based on token
	 * 
	 * @param token
	 * @return
	 */
	public boolean isUserAdmin(String token) {
		User user = getTokenUser(token);
		if (user == null)
			return false;
		return user.isAdmin();
	}

	/**
	 * get the user based on toekn
	 * 
	 * @param token
	 * @return
	 */
	public User getTokenUser(String token) {
		auth = authRepo.findByToken(token);
		if (auth == null)
			return null;
		return auth.getUser();
	}

	/**
	 * get the username based on toekn
	 * 
	 * @param token
	 * @return
	 */
	public String getTokenUserName(String token) {
		return getTokenUser(token).getName();
	}

	/**
	 * save the token and user into the databse
	 * 
	 * @param user
	 * @param token
	 */
	private void saveToken(User user, Token token) {
		if (auth == null)
			auth = new Auth();
		auth.setToken(token.getId());
		auth.setUser(user);
		auth = authRepo.save(auth);
	}

}
