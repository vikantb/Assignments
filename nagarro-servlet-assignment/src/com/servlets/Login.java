package com.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.users.managent.UserManager;

/**
 * login servlet that invoke for the login page
 * @author vikantbhati
 *
 */
public class Login extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username") ;
		String password=request.getParameter("password") ;
		
		HttpSession session=request.getSession() ;
		
		UserManager manager= UserManager.getManager();
		
		Integer userID=manager.getUserID(username, password) ;
		if ( userID!=null ) {
			session.setAttribute("userID", userID);
			response.sendRedirect("dashboard");
		}else {
			session.setAttribute("error", "Username or Password is incorrect...");
			response.sendRedirect("login.jsp");
		}
	}
	
}
