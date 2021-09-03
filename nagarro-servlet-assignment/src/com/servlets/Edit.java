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

import com.image.managent.ImageManager;
import com.users.managent.UserManager;

/**
 * edit servlet that invoke at the time of editing the name or image
 * 
 * @author vikantbhati
 *
 */
@MultipartConfig
public class Edit extends HttpServlet {
	private static long MAX_IMAGE_SIZE_ALLOWED = 1050500;

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
			String idPart = request.getParameter("id");
			if (idPart != null) {
				int id = Integer.parseInt(idPart);
				session.setAttribute("id", id);
				response.sendRedirect("edit.jsp");
			} else {
				response.sendRedirect("dashboard");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserManager im = UserManager.getManager();

		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("id");
		session.removeAttribute("id");

		String message = "";

		String imageName = request.getParameter("new-name").trim();

		if (id != null) {
			if (!imageName.equalsIgnoreCase("")) {
				try {
					im.updateImageName(id, imageName);
					message = "<span style='color:green;'>File renamed Seccessfully...</span>";
				} catch (NullPointerException e) {
					message = "<span style='color:red;'>!!! image it no further available to edit...</span>";
				}
			}

			Part imageFile = request.getPart("new-file");

			if (imageFile != null && imageFile.getSize() != 0) {
				long imageSize = imageFile.getSize();

				if (imageSize > MAX_IMAGE_SIZE_ALLOWED) {
					message += "<br> Image size is too large MAX( 1 MB )";
				} else {
					try {
						InputStream reader = imageFile.getInputStream();
						byte imageData[] = new byte[(int) imageSize];
						reader.read(imageData);
						try {
							im.updateImageData(id, imageData);
							message += "<br><span style='color:green;'>File editted successfully...</span>";
						} catch (NullPointerException e) {
							message += "<span style='color:red;'>!!! image it no further available to edit...</span>";
						}

					} catch (IOException e) {
						message += "<br> There is some problem in editing the file...";
					}
				}
			}
		}
		session.setAttribute("message", message);
		response.sendRedirect("dashboard");
	}

}
