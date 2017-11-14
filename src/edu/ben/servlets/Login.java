package edu.ben.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import edu.ben.daos.UserDao;
import edu.ben.models.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/WEB-INF/jsp/login.jsp";
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		User user = new User(username, password);
		
		// check that values are valid
		if (username != null && !username.equals("") && password != null && !password.equals("")) {
			// check that username is in db
			if (UserDao.userExists(username)) {
				user = UserDao.getUser(user);
				// if the uname and password is correct
				if (user != null) {
					request.getSession().setAttribute("user", user);
					response.sendRedirect("ViewUsers");
				} else{
					errors.add("Incorrect username or password, or your account is locked.");
					request.setAttribute("errors", errors);
					getServletContext().getRequestDispatcher(url).forward(request, response);
				}
			} else {
				errors.add("Incorrect username or password");
				request.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}

		} 
		
		
		
	}

}
