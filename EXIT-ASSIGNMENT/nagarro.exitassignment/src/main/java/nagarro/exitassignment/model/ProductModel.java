package nagarro.exitassignment.model;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * product model
 * 
 * @author vikantbhati
 *
 */
@Component
public class ProductModel {

	private int id;
	private String name;
	private String brand;
	private String code;
	private List<ReviewModel> reviews;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the reviews
	 */
	public List<ReviewModel> getReviews() {
		return reviews;
	}

	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(List<ReviewModel> reviews) {
		this.reviews = reviews;
	}

}
