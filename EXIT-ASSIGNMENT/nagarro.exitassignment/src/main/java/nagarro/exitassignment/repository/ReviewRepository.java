package nagarro.exitassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nagarro.exitassignment.entities.Review;

/**
 * ReviewRepository that implements the JPA repository and all CRUD operations
 * related to Review are declare in this repo
 * 
 * @author vikantbhati
 *
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
