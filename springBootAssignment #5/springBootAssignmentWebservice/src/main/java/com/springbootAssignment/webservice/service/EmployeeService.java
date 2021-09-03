package com.springbootAssignment.webservice.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbootAssignment.webservice.dao.EmployeeDao;
import com.springbootAssignment.webservice.entities.Employee;

/**
 * it is Employee service layer that provide that methods to interact the
 * EmployeeDAO
 * 
 * @author vikantbhati
 *
 */
@Component
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	EntityManager entityManager;

	/**
	 * provide the list of employees
	 * 
	 * @return
	 */
	public List<Employee> getEmployees() {
		return employeeDao.findAll();
	}

	/**
	 * provide the employee of given ID
	 * 
	 * @return
	 */
	public Employee getEmployees(int id) {
		return entityManager.find(Employee.class, id);
	}

	/**
	 * add the employee to the database
	 * 
	 * @param employee
	 */
	public boolean addEmployee(Employee employee) {
		Employee existEmployee = entityManager.find(Employee.class, employee.getCode());
		if (existEmployee != null)
			return false;

		employeeDao.save(employee);
		return true;
	}

	/**
	 * update the employee details
	 * 
	 * @param newEmployee
	 * @param code
	 */
	@Transactional
	public void updateEmployee(Employee newEmployee, int code) {
		Employee oldEmployee = entityManager.find(Employee.class, code);
		entityManager.remove(oldEmployee);
		employeeDao.save(newEmployee);
	}
}
