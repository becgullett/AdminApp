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
 * Servlet implementation class UpdateOrder
 */
@WebServlet("/UpdateOrder")
public class UpdateOrder extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrder() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Orders");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// so sorry for this method it was my last one and i am tired of doing this
		String temp = request.getParameter("temp");
		String url = "";
		String quantity = request.getParameter("quantity");
		String update = request.getParameter("update");
		String delete = request.getParameter("delete");
		String submit = request.getParameter("submit");
		String idOrderline = request.getParameter("orderlineID");
		String idOrder = request.getParameter("orderID");
		int orderID = Integer.parseInt(idOrder);
		String username = request.getParameter("username");
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		String ship = request.getParameter("ship");
		
		
		boolean hasShipped = false;
		if (ship != null) {
			hasShipped = true;
		}
		
		if(update != null) {
			Orderline ol = new Orderline();
			ol.setIdOrderline(Integer.parseInt(idOrderline));
			ol.setQuantity(Integer.parseInt(quantity));
			OrderlineDao.update(ol);
			Order order = OrderDao.getOrderByID(orderID);
			User u = UserDao.getUserByUserId(order.getIdUser());
			order.setUsername(u.getUsername());
			request.getSession().setAttribute("order", order);
			success.add("Order updated successfully");
			request.setAttribute("success", success);
			url = "/WEB-INF/jsp/updateOrder.jsp";
		} else if (delete != null) {
			OrderlineDao.remove(Integer.parseInt(idOrderline));
			Order order = OrderDao.getOrderByID(orderID);
			User u = UserDao.getUserByUserId(order.getIdUser());
			order.setUsername(u.getUsername());
			request.getSession().setAttribute("order", order);
			success.add("Order updated successfully");
			request.setAttribute("success", success);
			url = "/WEB-INF/jsp/updateOrder.jsp";
		} else if (submit != null) {
			int type = fixType(temp);
			boolean flag = true;
			if(!checkOrder(address,name,type)) {
				flag = false;
				errors.add("Please fill out all fields");
			}
			
			Order order = OrderDao.getOrderByID(orderID);
			order.setShippingAddress(address);
			order.setShipped(hasShipped);
			order.setShippingName(name);
			//order.setShippingType(type);
			if (flag) {
				OrderDao.update(order);
				request.getSession().setAttribute("order", order);
				success.add("Order updated successfully");
				request.setAttribute("success", success);
				url = "/WEB-INF/jsp/updateOrder.jsp";
			} else {
				request.getSession().setAttribute("order", order);
				request.setAttribute("errors", errors);
				url = "/WEB-INF/jsp/updateOrder.jsp";
				
			}
			
			
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
		
	}

	private boolean checkOrder(String address, String name, int type) {
		if (address != null && !address.equals("") && name != null && !name.equals("") && (type == 0 || type==1|| type==2)) {
			return true;
		}
		return false;
	}
	
	
	private int fixType(String temp) {
		if (temp.equals("Standard")) {
			return 1;
		} else if(temp.equals("Two-Day")) {
			return 2;
		} else { 
			return 3;
		}
	}

}
