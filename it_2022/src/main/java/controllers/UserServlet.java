package controllers;

import java.io.IOException;

import Repository.Repository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Repository collection;
	public void init(ServletConfig config) throws ServletException {
		collection = Repository.getInstance();
}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		User loggedUser = collection.getUserById(id);
		request.setAttribute("loggedUser", loggedUser);	
		
		RequestDispatcher rd = request.getRequestDispatcher("/EditProfilePage.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		int id = Integer.parseInt(request.getParameter("id"));
		User updatedUser = collection.getUserById(id);
		
		updatedUser.setPersonalName(request.getParameter("personal-name"));
		
		for(int i=0; i<updatedUser.getProfessionalSkills().size(); i++) {
			String skillName = request.getParameter("prof-skill-name"+i);
			updatedUser.getProfessionalSkills().get(i).setSkillName(skillName);
			
			int skillValue = Integer.parseInt(request.getParameter("prof-skill-value"+i));
			updatedUser.getProfessionalSkills().get(i).setSkillValue(skillValue);
		}
		
		request.setAttribute("loggedUser", updatedUser);
		
		RequestDispatcher rd = request.getRequestDispatcher("/ProfilePage.jsp");
		rd.forward(request, response);	
	}
}
