package com.springbootAssignment.webservice.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootAssignment.webservice.entities.Employee;
import com.springbootAssignment.webservice.entities.User;
import com.springbootAssignment.webservice.service.*;

/**
 * All the API ere declared here
 * 
 * @author vikantbhati
 *
 */
@RestController
public class RequestHandler {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private UserService userService;

	/**
	 * service that provide user corrospond to the given user credentials
	 * 
	 * @param requestUser
	 * @return
	 */
	@PostMapping(path = "/user", consumes = "application/json")
	public ResponseEntity<User> verifyUser(@RequestBody User requestUser) {
		User user = null;
		try {
			user = userService.getUser(requestUser);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		if (user == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(user);
	}

	/**
	 * service that provide the list of employees
	 * 
	 * @return
	 */
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> employees = employeeService.getEmployees();

		if (employees.size() == 0) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(employees);
	}

	/**
	 * service to update the employee details
	 * 
	 * @param employee
	 * @param code
	 * @return
	 */
	@GetMapping("/employee/{code}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("code") int code) {
		Employee employee = employeeService.getEmployees(code);
		if (employee == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(employee);
	}

	/**
	 * service to add employee
	 * 
	 * @param employee
	 * @return
	 */
	@PostMapping(path = "/employee", consumes = "application/json")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		boolean isAdded = employeeService.addEmployee(employee);

		if (isAdded)
			return ResponseEntity.status(201).body(employee);
		
		return ResponseEntity.status(406).build();
	}

	/**
	 * service to update the employee details
	 * 
	 * @param employee
	 * @param code
	 * @return
	 */
	@PostMapping(path = "/employee/{code}", consumes = "application/json")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("code") int code) {
		employeeService.updateEmployee(employee, code);
		return ResponseEntity.status(205).body(employee);
	}
}
