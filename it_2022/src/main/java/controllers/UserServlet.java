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
		String action = request.getParameter("action");
		
		User loggedUser = collection.getUserById(id);
		request.setAttribute("loggedUser", loggedUser);	
		
		if(action!=null && !action.isEmpty() && action.equals("edit")) {
			RequestDispatcher rd = request.getRequestDispatcher("/EditProfilePage.jsp");
		rd.forward(request, response);
		}
		else {		
		RequestDispatcher rd = request.getRequestDispatcher("/ProfilePage.jsp");
		rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		int id = Integer.parseInt(request.getParameter("id"));
		User updatedUser = collection.getUserById(id);
		
		updatedUser.setPersonalName(request.getParameter("personal-name"));
		updatedUser.setJobTitle(request.getParameter("job-title"));
		updatedUser.setDescription(request.getParameter("description"));
		updatedUser.setEmail(request.getParameter("email"));
		updatedUser.setPhone(request.getParameter("phone"));
		updatedUser.getAddress().setStreet(request.getParameter("street"));
		updatedUser.getAddress().setCity(request.getParameter("city"));
		
		for(int i=0; i<updatedUser.getProfessionalSkills().size();i++) {
			String skillName = request.getParameter("prof-skill-name"+i);
			updatedUser.getProfessionalSkills().get(i).setSkillName(skillName);
			
			int skillValue = Integer.parseInt(request.getParameter("prof-skill-value"+i));
			updatedUser.getProfessionalSkills().get(i).setSkillValue(skillValue);
		}
		
		for(int j=0; j<updatedUser.getPersonalSkills().size();j++) {
			String skillName = request.getParameter("personal-skill-name"+j);
			updatedUser.getPersonalSkills().get(j).setSkillName(skillName);
			
			int skillValue = Integer.parseInt(request.getParameter("personal-skill-value"+j));
			updatedUser.getPersonalSkills().get(j).setSkillValue(skillValue);	
		}
		
				response.sendRedirect("user?id="+updatedUser.getId());

		//request.setAttribute("loggedUser", updatedUser);
		
		
		//RequestDispatcher rd = request.getRequestDispatcher("/ProfilePage.jsp");
		//rd.forward(request, response);	
	}
}



