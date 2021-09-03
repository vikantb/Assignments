package com.springbootAssignment.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootAssignment.webservice.entities.Employee;

/**
 * Employee DAO that directly intract with the database
 * @author vikantbhati
 *
 */
public interface EmployeeDao extends JpaRepository<Employee,Integer>{
  
}
