package edu.ben.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ben.daos.UserDao;
import edu.ben.models.User;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/changePassword.jsp").forward(request, response);
		} else {
			errors.add("Please login before accessing this page.");
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm");
		String url = "/WEB-INF/jsp/changePassword.jsp";
		User user = (User) request.getSession().getAttribute("user");
		boolean flag = true;
		if (!password.equals(confirm)) {
			flag = false;
			errors.add("Passwords do not match");
		} 
		if (!checkPassword(password)) {
			flag = false;
			errors.add("Password must contain a digit and special character and be at least six characters long");
		}
		
		if (flag) {
			user.setPassword(password);
			if (UserDao.updatePassword(user)) {
				success.add("Password updated.");
			} else {
				errors.add("An error occurred, please try again");
			}
			
		}
		
		request.setAttribute("errors", errors);
		request.setAttribute("success", success);
		getServletContext().getRequestDispatcher(url).forward(request, response);
		
		
	}
	
	/**
	 * Check the password for enough characters and digits and special
	 * characters
	 * 
	 * @param password
	 *            password string
	 * @return boolean if the password meets difficulty protocol
	 */
	private boolean checkPassword(String password) {

		boolean hasSpecial = false;
		boolean hasDigit = false;
		if (password.length() >= 6) {
			for (int i = 0; i < password.length(); i++) {

				if (Character.isDigit(password.charAt(i))) {
					hasDigit = true;
				} else if (!Character.isLetterOrDigit(password.charAt(i))
						|| !Character.isWhitespace(password.charAt(i))) {
					hasSpecial = true;
				}

			}
			return (hasSpecial && hasDigit);

		} else {
			return false;
		}
	}

}
