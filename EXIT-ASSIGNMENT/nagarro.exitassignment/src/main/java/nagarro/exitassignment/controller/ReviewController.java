package nagarro.exitassignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import nagarro.exitassignment.entities.Review;
import nagarro.exitassignment.model.Message;
import nagarro.exitassignment.service.ReviewService;

/**
 * All the API related to that perform CRUD operations related to Review
 * 
 * @author vikantbhati
 *
 */
@RestController
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private Message message;

	/**
	 * API to add a new review
	 * 
	 * @param token
	 * @param productID
	 * @param review
	 * @return
	 */
	@PostMapping("/api/review/{productID}/add")
	public ResponseEntity<Message> addReview(@RequestHeader("Authorization") String token, @PathVariable int productID,
			@RequestBody Review review) {

		boolean isAdded = reviewService.addReview(productID, review);

		if (!isAdded)
			return ResponseEntity.status(406).build();

		message.setMessage("Review Added Successfully");
		return ResponseEntity.ok(message);
	}

	/**
	 * API to verify the review by the admin
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/review/verify/{id}")
	public ResponseEntity<Boolean> verifyReview(@PathVariable int id) {
		boolean isVerified = reviewService.verifyReview(id);

		return ResponseEntity.ok(isVerified);
	}

	/**
	 * API to reject the review by the admin
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/review/reject/{id}")
	public ResponseEntity<Boolean> rejectReview(@PathVariable int id) {
		boolean isDeleted = reviewService.rejectReview(id);

		return ResponseEntity.ok(isDeleted);
	}

	/**
	 * API to retrieve all the reviews
	 * 
	 * @return
	 */
	@GetMapping("/admin/reviews")
	public ResponseEntity<List<Review>> getReviews() {
		return ResponseEntity.ok(reviewService.getReviews());
	}

}
