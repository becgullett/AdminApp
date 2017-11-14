package edu.ben.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ben.models.User;

/**
 * Servlet implementation class Base
 */
@WebServlet("/_")
public class Base extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> errors = new ArrayList<String>();
	ArrayList<String> success = new ArrayList<String>();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Base() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = (String) request.getParameter("action");
		
		User user = (User) request.getSession().getAttribute("user");
		// if no action, send to login page
		if (action == null) {
			action = "login";
		} 
		String url = "/WEB-INF/jsp/login.jsp";
		
		// if user is not null and security level is correct send them to that page
		if (user != null && user.getSecurity().equals("50")/*and the security is correct*/) {
			if (action.equals("add")) {
				url = "/WEB-INF/jsp/add.jsp";
			}
			// else if user send them to login
		} else {
			// if user is null kick them out to the register page
			url = "/WEB-INF/jsp/login.jsp";
			if (!action.equals("login")) {
				errors.add("please login before trying to access these pages");
			}
			
		}
		request.setAttribute("errors", errors);
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
