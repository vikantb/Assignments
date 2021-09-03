package com.springbootAssignment.webservice.service;

import java.text.ParseException;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbootAssignment.webservice.entities.User;

/**
 * it is UserService layer that provide that methods access the User databse
 * @author vikantbhati
 *
 */
@Component
public class UserService {

	@Autowired
	EntityManager entityManager ;
	
	/**
	 * verify the user and return the user
	 * @param user
	 * @return
	 * @throws ParseException
	 * @throws IndexOutOfBoundsException
	 */
	public User getUser(User user) throws ParseException,IndexOutOfBoundsException {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria=session.createCriteria(User.class) ;
		setCriteria(criteria,user.getUsername(),user.getPassword()) ;
		return (User)criteria.list().get(0) ;
	}
	
	/**
	 * set the filter restrictions to get result set
	 * 
	 * @param flight
	 * @param criteria
	 * @throws ParseException
	 */
	private void setCriteria(Criteria criteria,String username,String password) throws ParseException {
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", password));
	}
}
