package nagarro.exitassignment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import nagarro.exitassignment.entities.User;
import nagarro.exitassignment.model.UserModel;
import nagarro.exitassignment.repository.UserRepository;

/**
 * all the services related to the user are declare here
 * 
 * @author vikantbhati
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	/**
	 * get all the user from the databse
	 * 
	 * @return
	 */
	public List<UserModel> allUsers() {
		List<User> users = userRepo.findAll();

		return createUserModelList(users);
	}

	/**
	 * create UserModel from user entity
	 * 
	 * @param users
	 * @return
	 */
	private List<UserModel> createUserModelList(List<User> users) {
		List<UserModel> list = new ArrayList<UserModel>();

		for (User user : users) {
			UserModel userModel = new UserModel();
			userModel.setId(user.getId());
			userModel.setName(user.getName());

			list.add(userModel);
		}

		return list;
	}

	/**
	 * make the given ID's user as admin
	 * 
	 * @param id
	 * @return
	 */
	public boolean makeAdmin(int id) {
		User user = userRepo.findById(id);
		if (user == null)
			return false;

		user.setAdmin(true);
		userRepo.save(user);

		return true;
	}

	/**
	 * remove the given ID's user as admin
	 * 
	 * @param id
	 * @return
	 */
	public boolean removeAdmin(int id) {
		User user = userRepo.findById(id);
		if (user == null)
			return false;

		user.setAdmin(false);
		userRepo.save(user);

		return true;
	}

	/**
	 * check wheater the user is valid or not based on username and password
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean isValidUser(String username, String password) {
		User user = getUser(username,password);
		if(user!=null) return true;
		return false;
	}

	/**
	 * get the users based on username
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public List<User> getUser(String username) {
		List<User> users = userRepo.findByUsername(username);

		return users;
	}

	/**
	 * get the users based on username and password
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUser(String username, String password) {
		List<User> users = getUser(username);

		for (User user : users) {
			String encryptPassword = user.getPassword();
			BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), encryptPassword);

			if (result.verified)
				return user;
		}
		return null;
	}

	/**
	 * save the user to the database
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user) {
		boolean isValidInputField = isValidInputFields(user);
		boolean isUserPresent = isUserPresent(user.getUsername());
		if (!isValidInputField || isUserPresent)
			return false;

		// ecrypt the password and save in database
		encryptPasswordAndSetToUser(user);
		
		userRepo.save(user);
		return true;
	}

	/**
	 * encrypt the password and save update in the user
	 * 
	 * @param user
	 */
	public void encryptPasswordAndSetToUser(User user) {
		String password = user.getPassword();

		String encryptPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

		user.setPassword(encryptPassword);
	}

	/**
	 * check weather the given username is already present in the database or not
	 * 
	 * @param username
	 * @return
	 */
	private boolean isUserPresent(String username) {
		List<User> users = getUser(username);
		if (users.size() != 0)
			return true;
		return false;
	}

	/**
	 * check weather the input fields of new user are valid or not
	 * 
	 * @param user
	 * @return
	 */
	private boolean isValidInputFields(User user) {
		String name = user.getName();
		String username = user.getUsername();
		String password = user.getPassword();

		if (name == null || name.length() < 5 || username == null || username.length() < 5 || password == null
				|| password.length() < 5)
			return false;
		return true;
	}

	/**
	 * get the number of users present in the databse
	 * 
	 * @return
	 */
	public int countUser() {
		List<User> users = userRepo.findAll();
		if (users == null)
			return 0;
		return users.size();
	}

}
