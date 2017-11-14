package edu.ben.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ben.daos.OrderDao;
import edu.ben.daos.OrderlineDao;
import edu.ben.daos.UserDao;
import edu.ben.models.Order;
import edu.ben.models.Orderline;
import edu.ben.models.User;

/**
 * Servlet implementation class Orders
 */
@WebServlet("/Orders")
public class Orders extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Orders() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null && user.getSecurity().equals("50")/*and the security is correct*/) {
			ArrayList<Order> orders = OrderDao.getAllOrders();
			if (orders != null) {
				User u;
				for(Order o :orders) {
					u = UserDao.getUserByUserId(o.getIdUser());
					o.setUsername(u.getUsername());
				}
			}
			request.setAttribute("orders", orders);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/orders.jsp").forward(request, response);
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
		
		String url="/WEB-INF/jsp/orders.jsp";
		String delete = request.getParameter("delete");
		String update = request.getParameter("uo");
		String idOrder = request.getParameter("idOrder");
		int id = Integer.parseInt(idOrder);
		
		if (delete!= null) {
			OrderDao.remove(id);
			success.add("Order successfully removed.");
			
			ArrayList<Order> orders = OrderDao.getAllOrders();
			if (orders != null) {
				User u;
				for(Order o :orders) {
					u = UserDao.getUserByUserId(o.getIdUser());
					o.setUsername(u.getUsername());
				}
			}
			request.setAttribute("orders", orders);
		} if (update != null) {
			Order order = OrderDao.getOrderByID(id);
			User u = UserDao.getUserByUserId(order.getIdUser());
			order.setUsername(u.getUsername());
			request.getSession().setAttribute("order", order);
			url = "/WEB-INF/jsp/updateOrder.jsp";
			
		}
		
		request.setAttribute("success",success);
		getServletContext().getRequestDispatcher(url).forward(request, response);;
	
		
	}

}
