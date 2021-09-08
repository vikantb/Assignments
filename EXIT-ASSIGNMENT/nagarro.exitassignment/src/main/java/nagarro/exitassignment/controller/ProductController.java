package nagarro.exitassignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nagarro.exitassignment.entities.Product;
import nagarro.exitassignment.model.ProductModel;
import nagarro.exitassignment.service.ProductService;

/**
 * All the API related to that perform CRUD operations related to product
 * Product
 * 
 * @author vikantbhati
 *
 */
@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * search the product based either on name , brand or code of the product;
	 * 
	 * @param search_string
	 * @return
	 */
	@GetMapping("/api/product/search/{search_string}")
	public ResponseEntity<List<Product>> product(@PathVariable("search_string") String search_string) {
		List<Product> products = null;
		products = productService.getProductsByNameCodeBrand(search_string);
		return ResponseEntity.ok(products);
	}

	/**
	 * get the product of a perticular ID
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/api/product/{id}")
	public ResponseEntity<ProductModel> getProductByID(@PathVariable("id") int id) {
		ProductModel product = productService.getProductByID(id);
		if (product == null)
			return ResponseEntity.status(204).build();
		return ResponseEntity.ok(product);
	}

	/**
	 * add a new product
	 * 
	 * @param product
	 * @return
	 */
	@PostMapping("/api/product/add")
	public ResponseEntity<Void> addProduct(@RequestBody Product product) {
		boolean isAdded = productService.addProduct(product);
		System.out.println(product);
		if (!isAdded)
			return ResponseEntity.status(406).build();
		return ResponseEntity.ok().build();
	}

	/**
	 * fetch total number of products present in the database
	 * 
	 * @return
	 */
	@GetMapping("/app/products/count")
	public ResponseEntity<Integer> product() {
		int count = productService.countProducts();
		return ResponseEntity.ok(count);
	}
}
