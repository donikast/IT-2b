package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import Repository.Repository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Repository collection;

	public void init(ServletConfig config) throws ServletException {
		collection = Repository.getInstance();	 
	}

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/LoginPage.jsp");
		rd.forward(request, response);
	}

 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User(username,password);
		
		if(collection.ifExist(user)) {
			
			User loggedUser = collection.getUserByUsername(username);
			
			response.sendRedirect("user?id="+loggedUser.getId()+"&action=edit");
			
			
		}
		
		else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<p>Невалидно потребителско име или парола!</p>");	
			RequestDispatcher rd = request.getRequestDispatcher("/LoginPage.jsp");
			rd.include(request, response);
		}
		
	}

}
