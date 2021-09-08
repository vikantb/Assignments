package nagarro.exitassignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nagarro.exitassignment.entities.Product;

/**
 * ProductRepository that implements the JPA repository and all CRUD operations
 * related to product are declare in this repo
 * 
 * @author vikantbhati
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	/**
	 * get all the produts based on code,brand or name
	 * 
	 * @param name
	 * @param brand
	 * @param code
	 * @return
	 */
	public List<Product> findByNameOrBrandOrCode(String name, String brand, String code);

	/**
	 * get the product based on ID
	 * 
	 * @param id
	 * @return
	 */
	public Product findById(int id);
}
