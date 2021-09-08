package nagarro.exitassignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nagarro.exitassignment.entities.User;

/**
 * UserRepository that implements the JPA repository and all CRUD operations
 * related to User are declare in this repo
 * 
 * @author vikantbhati
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * get the user related to the username and password
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public List<User> findByUsernameAndPassword(String username, String password);

	/**
	 * get the user based on username
	 * 
	 * @param username
	 * @return
	 */
	public List<User> findByUsername(String username);

	/**
	 * get the user based on ID
	 * 
	 * @param id
	 * @return
	 */
	public User findById(int id);
}
