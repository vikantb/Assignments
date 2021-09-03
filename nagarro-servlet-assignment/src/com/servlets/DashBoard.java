package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.image.managent.ImageManager;
import com.users.managent.UserManager;

import entities.Image;

/**
 * servlet that invoke after successful login and display that Home/Dashboard page
 * @author vikantbhati
 *
 */
public class DashBoard extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Integer userID = (Integer) session.getAttribute("userID");

		if (userID == null) {
			response.sendRedirect("login");
		} else {
			
			UserManager manager = UserManager.getManager();
			List<Image> images = manager.getUserImages(userID);

			session.setAttribute("images", images);

			response.sendRedirect("dashboard.jsp");
		}
	}

}
