package com.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.users.managent.UserManager;

import entities.Image;

/**
 * servlet to add image to the databse that handle the post data
 * @author vikantbhati
 *
 */
@MultipartConfig
public class AddImage extends HttpServlet {

	private static long MAX_IMAGE_SIZE_ALLOWED = 1050500;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userID = (Integer) session.getAttribute("userID");

		if (userID == null)
			response.sendRedirect("login");
		else {
			String imageName = request.getParameter("image-name");
			Part imageFile = request.getPart("image-file");
			long imageSize = imageFile.getSize();

			String message = "";

			if (imageSize > MAX_IMAGE_SIZE_ALLOWED) {
				message = "Image size is too large MAX( 1 MB )";
			} else {
				try {
					InputStream reader = imageFile.getInputStream();
					byte imageData[] = new byte[(int) imageSize];
					reader.read(imageData);

					Image image = new Image();
					image.setName(imageName);
					image.setData(imageData);
					image.setSize(imageSize);

					UserManager manager = UserManager.getManager();

					if (manager.addImage(userID, image)) {
						message = "<span style='color:green;'>File Uploaded Successfully...</span>";
					} else {
						message = "Total size of uploaded files exceeded 10 MB";
					}
				} catch (IOException e) {
					message = "There is problem in reading file...";
				}
			}

			session.setAttribute("message", message);

			response.sendRedirect("dashboard");
		}
	}

}
