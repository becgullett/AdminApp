package edu.ben.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ben.daos.UserDao;
import edu.ben.models.AddForm;
import edu.ben.models.User;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/Add")
public class AddUser extends Base{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null && user.getSecurity().equals("50")/*and the security is correct*/) {
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(request, response);
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
		User user = (User) request.getSession().getAttribute("user");
		String url = "/WEB-INF/jsp/add.jsp";
		// assign the form values to local variables
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		String reset = request.getParameter("reset");
		String security = request.getParameter("security");
		
		User userAdd = new User(fname, lname, email, username, password, security);
		AddForm af = new AddForm(fname, lname, email, username, password, security);
		if (reset == null) {
			boolean flag = true;
			if (!checkUser(userAdd)) {
				flag = false;
				errors.add("Please fill out all fields");
				// please fill out all fields msg
			} 
			if (!checkPassword(password)) {
				flag = false;
				errors.add("Password must contain a digit and special character");
				// password complexity method
			}
			if (!checkEmail(email)) {
				flag = false;
				errors.add("Please enter a valid email");
				// email validity method
			}
			if (flag) {
				// add user
				UserDao.insert(userAdd, user.getUsername());
				success.add("User added successfully.");
				af = new AddForm();
				request.setAttribute("success", success);
			}
		} else {
			af = new AddForm();
		}
		request.setAttribute("errors", errors);
		request.setAttribute("af", af);
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	/**
	 * User object is verified that nothing was left blank
	 * 
	 * @param user
	 *            Student object
	 * @return true if nothing is empty, false otherwise
	 */
	private boolean checkUser(User user) {
		if (user.getFirstName() == null || user.getFirstName().equals("") || user.getLastName() == null
				|| user.getLastName().equals("") || user.getEmail() == null || user.getEmail().equals("")
				|| user.getUsername() == null || user.getUsername().equals("") || user.getPassword() == null
				|| user.getPassword().equals("") || user.getSecurity() == null
				|| user.getSecurity().equals("")) {
			return false;
		}
		return true;

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

	private boolean checkEmail(String email) {
		return email.contains("@") && email.contains(".") && email.length() >= 6;
	}

}
