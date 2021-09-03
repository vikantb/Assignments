package connection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entities.Flight;

public class Connector {

	Configuration con = new Configuration().configure().addAnnotatedClass(Flight.class);
	SessionFactory sf = con.buildSessionFactory();

	Session session = sf.openSession();;

	public Session getSession() {

		return session;
	}

	public Criteria getCriteria() {
		Criteria criteria = session.createCriteria(Flight.class);

		return criteria;
	}
	
	

}
