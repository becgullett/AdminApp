package edu.ben.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ben.daos.UserDao;
import edu.ben.models.User;

/**
 * Servlet implementation class ViewUsers
 */
@WebServlet("/ViewUsers")
public class ViewUsers extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUsers() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null && user.getSecurity().equals("50")/*and the security is correct*/) {
			ArrayList<User> users = UserDao.getUserByCreatedBy(user.getUsername());
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/view.jsp").forward(request, response);
			
		} else {
			errors.add("You are not authorized to access these pages. Please login or talk to site administrator.");
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/WEB-INF/jsp/view.jsp";
		String delete = request.getParameter("delete");
		String username = request.getParameter("username");
		String update = request.getParameter("update");
		if (delete!= null) {
			if (UserDao.userExists(username)) {
				UserDao.remove(username);
				success.add("User successfully removed.");
			}
		} else if (update != null) {
			if (UserDao.userExists(username)) {
				User updateUser = UserDao.getUserByUsername(username);
				request.getSession().setAttribute("update", updateUser);
				url = "/WEB-INF/jsp/update.jsp";
			}
		}
		request.setAttribute("success",success);
		getServletContext().getRequestDispatcher(url).forward(request, response);;
	}

}
