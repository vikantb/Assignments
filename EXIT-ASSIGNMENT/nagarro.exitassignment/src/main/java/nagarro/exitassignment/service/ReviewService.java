package nagarro.exitassignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nagarro.exitassignment.entities.Product;
import nagarro.exitassignment.entities.Review;
import nagarro.exitassignment.repository.ReviewRepository;

/**
 * all the services related to the Review are declare here
 * 
 * @author vikantbhati
 *
 */
@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private ProductService productService;

	/**
	 * add the new review to the database
	 * 
	 * @param productID
	 * @param review
	 * @return
	 */
	public boolean addReview(int productID, Review review) {

		if (!isValidInput(review))
			return false;

		Product product = productService.addReviewToList(productID, review);
		review.setProduct(product);

		reviewRepository.save(review);
		return true;
	}

	/**
	 * check weather the inputs fields of new review are valid or not
	 * 
	 * @param review
	 * @return
	 */
	private boolean isValidInput(Review review) {
		if (review.getDescription().length() < 20 || review.getDescription().length() > 400
				|| review.getTitle().length() < 5 || review.getTitle().length() > 20 || review.getRating() < 1
				|| review.getRating() > 5)
			return false;
		return true;
	}

	/**
	 * verify the review based on ID
	 * 
	 * @param id
	 * @return
	 */
	public boolean verifyReview(int id) {
		Review review = reviewRepository.getById(id);
		if (review == null)
			return false;

		review.setVerified(true);
		reviewRepository.save(review);

		return true;
	}

	/**
	 * remove the review based on id
	 * 
	 * @param id
	 * @return
	 */
	public boolean rejectReview(int id) {
		Review review = reviewRepository.getById(id);
		if (review == null)
			return false;

		reviewRepository.delete(review);

		return true;
	}

	/**
	 * get the list of reviews
	 * 
	 * @return
	 */
	public List<Review> getReviews() {
		return reviewRepository.findAll();

	}

}
