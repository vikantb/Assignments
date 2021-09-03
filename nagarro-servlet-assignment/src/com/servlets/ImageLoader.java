package com.servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.image.managent.ImageManager;

import entities.Image;

/**
 * servlet that display the image at the view side
 * @author vikantbhati
 *
 */
public class ImageLoader extends HttpServlet {

	/**
	 * process the get request to display the image
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Integer userID = (Integer) session.getAttribute("userID");

		if (userID == null)
			response.sendRedirect("login");
		else {
			int id = Integer.parseInt(request.getParameter("id"));

			ImageManager im = new ImageManager();

			Image image = im.getImage(id);
			if (image != null) {
				OutputStream writer = response.getOutputStream();
				writer.write(image.getData());
			}
		}
	}

}
