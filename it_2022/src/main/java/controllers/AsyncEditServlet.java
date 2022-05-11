package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import Repository.Repository;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.JsonResult;
import models.User;

@WebServlet("/updateUserInfo")
public class AsyncEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Repository collection;
	Gson gson;
	
	public void init(ServletConfig config) throws ServletException {	
		collection = Repository.getInstance();	
		gson = new Gson();
		
	}
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		StringBuilder sb = new StringBuilder();
		String s;
		while ((s = request.getReader().readLine()) != null) {
		    sb.append(s);
		}

		User newUser = (User) gson.fromJson(sb.toString(), User.class);
		User user = collection.getUserById(newUser.getId());
		
		user.setPersonalName(newUser.getPersonalName());
		user.setJobTitle(newUser.getJobTitle());
		user.setDescription(newUser.getDescription());
		
		JsonResult result = new JsonResult();
		if(user!=null) {
			result.setMessage("Успешна актуализация");
		} else {
		result.setMessage("Неуспешна актуализация");

		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(result));
		out.flush();
	}
}
