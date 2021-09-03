package com.users.managent;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import connection.Connector;
import entities.Image;
import entities.User;

/**
 * all user utility services are provided in this class
 * @author vikantbhati
 *
 */
public class UserManager {
	private static long MAX_UPLOAD_CAPACITY = 10505000;

	Connector connector = new Connector();
	private static UserManager manager = null;

	/**
	 * if the user is present in database than return userID based on credentials
	 * @param username
	 * @param password
	 * @return userID
	 */
	public Integer getUserID(String username, String password) {
		Criteria criteria = connector.getCriteria();
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", password));

		List list=criteria.list();
		
		if (list.size()!=0) {
			User user = (User)list.get(0);
			return user.getId() ;
		}
		return null;
	}

	/**
	 * upload the image to database
	 * @param userID
	 * @param image
	 * @return
	 */
	public boolean addImage(int userID, Image image) {
		Session session = connector.getSession();
		Transaction transaction = session.beginTransaction();

		User user = (User) session.get(User.class, userID);
		List<Image> images = user.getImages();

		if (!isCapacityAvailable(images, image)) {
			transaction.rollback();
			return false;
		}

		image.setUser(user);
		session.save(image);
		user.getImages().add(image);

		transaction.commit();
		return true;
	}
	
	/**
	 * delete the image from the database
	 * @param userID
	 * @param imageID
	 */
	public void deleteImage(int userID, int imageID) {
		Session session = connector.getSession();
		Transaction transaction = session.beginTransaction();

		User user = (User) session.get(User.class, userID);
		Image image=(Image)session.get(Image.class, imageID);
		
		user.getImages().remove(image);
		session.delete(image);

		transaction.commit();
	}
	
	/**
	 * update the image 
	 * @param imageID
	 * @param imageData
	 */
	public void updateImageData(int imageID, byte[] imageData) {
		Session session = connector.getSession();
		Transaction transaction = session.beginTransaction();

		Image image = session.get(Image.class, imageID);
		image.setData(imageData);
		image.setSize(imageData.length);

		transaction.commit();
	}
	
	/**
	 * update the image name
	 * @param imageID
	 * @param name
	 */
	public void updateImageName(int imageID, String name) {
		Session session = connector.getSession();
		Transaction transaction = session.beginTransaction();

		Image image = session.get(Image.class, imageID);
		image.setName(name);

		transaction.commit();
	}

	/**
	 * list of uploaded images of a user
	 * @param id
	 * @return
	 */
	public List<Image> getUserImages(int id) {
		Session session = connector.getSession();
		User user = (User) session.get(User.class, id);
		return user.getImages();
	}

	/**
	 * check weather the space is available to store the images or not
	 * @param images
	 * @param newImage
	 * @return
	 */
	private boolean isCapacityAvailable(List<Image> images, Image newImage) {
		long imagesCapacity = 0;

		for (Image image : images) {
			imagesCapacity += image.getSize();
		}

		if ((imagesCapacity + newImage.getSize()) < MAX_UPLOAD_CAPACITY) {
			return true;
		}
		return false;
	}
	
	/**
	 * get the user manager to utilise the manager services
	 * @return
	 */
	public static UserManager getManager() {
		if (manager == null) {
			manager = new UserManager();
		}
		return manager;
	}
}
