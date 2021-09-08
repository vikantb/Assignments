package nagarro.exitassignment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nagarro.exitassignment.entities.Product;
import nagarro.exitassignment.entities.Review;
import nagarro.exitassignment.model.ProductModel;
import nagarro.exitassignment.model.ReviewModel;
import nagarro.exitassignment.repository.ProductRepository;

/**
 * all the services related to the Product are declare here
 * 
 * @author vikantbhati
 *
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;

	/**
	 * get the products based on code , name or brand
	 * 
	 * @param str
	 * @return
	 */
	public List<Product> getProductsByNameCodeBrand(String str) {
		return productRepo.findByNameOrBrandOrCode(str, str, str);
	}

	/**
	 * get the product based on it's ID
	 * 
	 * @param id
	 * @return
	 */
	public ProductModel getProductByID(int id) {
		Product product = productRepo.findById(id);
		if (product == null)
			return null;
		return createProductModel(product);

	}

	/**
	 * create a product model by fetching all the details from the product entity
	 * 
	 * @param product
	 * @return
	 */
	private ProductModel createProductModel(Product product) {
		ProductModel productModel = new ProductModel();
		productModel.setBrand(product.getBrand());
		productModel.setCode(product.getCode());
		productModel.setName(product.getName());
		productModel.setId(product.getId());

		List<ReviewModel> reviews = new ArrayList<>();
		for (Review review : product.getReviews()) {
			if (review.isVerified()) {
				ReviewModel reviewModel = new ReviewModel();
				reviewModel.setId(review.getId());
				reviewModel.setDescription(review.getDescription());
				reviewModel.setRating(review.getRating());
				reviewModel.setTitle(review.getTitle());
				reviews.add(reviewModel);
			}
		}
		productModel.setReviews(reviews);

		return productModel;
	}

	/**
	 * add the review to the product's reviews list
	 * 
	 * @param id
	 * @param review
	 * @return
	 */
	public Product addReviewToList(int id, Review review) {
		Product product = productRepo.getById(id);
		product.getReviews().add(review);
		productRepo.save(product);
		return product;
	}

	/**
	 * add the new product tot he database
	 * 
	 * @param product
	 * @return
	 */
	public boolean addProduct(Product product) {
		if (isValidInput(product)) {
			productRepo.save(product);
			return true;
		}
		return false;
	}

	/**
	 * count the total number of products present in the database
	 * 
	 * @return
	 */
	public int countProducts() {
		List<Product> products = productRepo.findAll();
		if (products == null)
			return 0;
		return products.size();
	}

	/**
	 * check weather the input field of the new product are valid or not
	 * 
	 * @param product
	 * @return
	 */
	private boolean isValidInput(Product product) {
		if (product.getBrand().length() < 5 || product.getName().length() < 5 || product.getCode().length() < 5)
			return false;
		return true;
	}

}
