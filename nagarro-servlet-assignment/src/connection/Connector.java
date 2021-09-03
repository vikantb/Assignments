package connection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entities.Image;
import entities.User;

/**
 * established all the configuration in this class and make the connection to
 * the database and provide all services to get the Session and Criteria
 * 
 * @author vikantbhati
 *
 */
public class Connector {

	Configuration con = new Configuration().configure().addAnnotatedClass(Image.class).addAnnotatedClass(User.class);
	SessionFactory sf = con.buildSessionFactory();

	Session session = sf.openSession();

	/**
	 * return the Session Object
	 * @return
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * return the Criteria Object
	 * @return
	 */
	public Criteria getCriteria() {
		Criteria criteria = session.createCriteria(User.class);
		return criteria;
	}

}
