package nagarro.exitassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import nagarro.exitassignment.entities.User;
import nagarro.exitassignment.model.Message;
import nagarro.exitassignment.model.Token;
import nagarro.exitassignment.model.UserModel;
import nagarro.exitassignment.service.UserService;
import nagarro.exitassignment.utility.TokenUtility;

/**
 * All the API related to that perform CRUD operations on the USER
 * 
 * @author vikantbhati
 *
 */
@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenUtility tokenUtility;

	@Autowired
	private Message message;

	/**
	 * API to authenticate the user and if user found then create a token of that
	 * user
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("app/auth/login")
	public ResponseEntity<Token> authenticateUser(@RequestBody User user) {
		user = userService.getUser(user.getUsername(), user.getPassword());
		if (user == null) {
			return ResponseEntity.status(401).build();
		}
		Token token = tokenUtility.createToken(user);

		return ResponseEntity.ok(token);
	}

	/**
	 * API to create a new USER
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("app/auth/register")
	public ResponseEntity<Message> createUser(@RequestBody User user) {
		boolean isUserCreated = userService.saveUser(user);
		if (isUserCreated) {
			message.setMessage("User registered sucessfully");
			return ResponseEntity.status(201).body(message);
		}

		message.setMessage(
				"ERROR : " + "#All the Fields should have length greater than 4 " + "#Username should be unique");
		return ResponseEntity.status(401).body(message);
	}

	/**
	 * API to get a user corrospond to it's token
	 * 
	 * @param token
	 * @return
	 */
	@GetMapping("/api/user")
	public ResponseEntity<UserModel> getName(@RequestHeader("Authorization") String token) {
		User user = tokenUtility.getTokenUser(token);

		UserModel userModel = new UserModel();
		userModel.setName(user.getName());
		userModel.setAdmin(user.isAdmin());
		return ResponseEntity.ok().body(userModel);
	}

	/**
	 * API to make a perticular ID user as admin
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/user/makeAdmin/{id}")
	public ResponseEntity<Boolean> makeAdmin(@PathVariable int id) {
		boolean isAdminNow = userService.makeAdmin(id);

		return ResponseEntity.ok().body(isAdminNow);
	}

	/**
	 * API to remove a perticular ID user from admin
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/user/removeAdmin/{id}")
	public ResponseEntity<Boolean> removeAdmin(@PathVariable int id) {
		boolean isAdminRemoveNow = userService.removeAdmin(id);

		return ResponseEntity.ok().body(isAdminRemoveNow);
	}

	/**
	 * API to get total number of users
	 * 
	 * @return
	 */
	@GetMapping("/app/users/count")
	public ResponseEntity<Integer> getUserCount() {
		int count = userService.countUser();
		return ResponseEntity.ok().body(count);

	}

}
