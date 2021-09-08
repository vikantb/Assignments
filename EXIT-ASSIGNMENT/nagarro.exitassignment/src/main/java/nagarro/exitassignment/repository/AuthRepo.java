package nagarro.exitassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nagarro.exitassignment.entities.Auth;

/**
 * AUTH repository that implements the JPA repository and all CRUD operations
 * related to user and token verification are declare in this repo
 * 
 * @author vikantbhati
 *
 */
@Repository
public interface AuthRepo extends JpaRepository<Auth, Integer> {

	/**
	 * get the credentials based on the token
	 * 
	 * @param token
	 * @return
	 */
	public Auth findByToken(String token);
}
