
package com.springbootAssignment.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application start
 * @author vikantbhati
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class SpringBootAssignmentApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootAssignmentApplication.class, args);
		
	}

}
