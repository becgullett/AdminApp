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
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		User update = (User) request.getSession().getAttribute("update");
		if (user != null && user.getSecurity().equals("50")/*and the security is correct*/) {
			if(update != null) {
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(request, response);
			} else {
				request.setAttribute("errors", errors);
				errors.add("Please choose a user to update");
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/view.jsp").forward(request, response);
			}
			
			
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
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String email = request.getParameter("email");
		String username =request.getParameter("uname");
		String locked = request.getParameter("locked");
		String reset = request.getParameter("resetPassword");
		
		boolean lock = false;
		if (locked != null) {
			lock = true;
		}
		String security = request.getParameter("security");
		
		User update = new User(-1, firstName, lastName, email, username, "", true, lock, security);
		
		boolean flag = true;
		if (!checkUser(update)) {
			flag = false;
			errors.add("Please fill out all fields");
			
		}
		if (!checkEmail(email)) {
			flag = false;
			errors.add("Please enter a valid email");
		}
		
		if (flag) {
			UserDao.update(update); 
			if (reset!= null) {
				UserDao.resetPassword(update);
			}
			success.add("User updated successfully");
		}
		request.getSession().setAttribute("update", update);
		request.setAttribute("errors", errors);
		request.setAttribute("success", success);
		doGet(request,response);

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
				|| user.getUsername() == null || user.getUsername().equals("") || user.getSecurity() == null
				|| user.getSecurity().equals("")) {
			return false;
		}
		return true;

	}
	
	/**
	 * Check if email is slightly valid
	 * @param email email to check
	 * @return true if valid, false otherwise
	 */
	private boolean checkEmail(String email) {
		return email.contains("@") && email.contains(".") && email.length() >= 6;
	}

}
