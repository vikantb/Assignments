package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.image.managent.ImageManager;
import com.users.managent.UserManager;

/**
 * servlet that invoke at the time of deleting the image
 * @author vikantbhati
 *
 */
public class DeleteImage extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Integer userID = (Integer) session.getAttribute("userID");

		if (userID == null)
			response.sendRedirect("login");
		else {
			int imageID = Integer.parseInt(request.getParameter("id"));

			UserManager manager = UserManager.getManager();

			manager.deleteImage(userID, imageID);
			
			session.setAttribute("message", "<span style='color:green;'>Deleted Successfully...</span>");
			
			response.sendRedirect("dashboard");
		}
	}

}
