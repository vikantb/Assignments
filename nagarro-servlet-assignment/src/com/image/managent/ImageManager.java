package com.image.managent;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import connection.Connector;
import entities.Image;

/**
 * image related services are provided in this class
 * @author vikantbhati
 *
 */
public class ImageManager {
	Connector connector = new Connector();
	private static ImageManager manager=null;

	/**
	 * fetch the image from the database
	 * @param id
	 * @return
	 */
	public Image getImage(int id) {
		Session session = connector.getSession();
		return session.get(Image.class, id);
	}
	
	/**
	 * get the image manager to utilise the image services
	 * @return
	 */
	public static ImageManager getManager() {
		if( manager==null ) {
			manager=new ImageManager();
		}
		return manager;
	}
}
