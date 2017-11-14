package edu.ben.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ben.daos.ProductDao;
import edu.ben.daos.UserDao;
import edu.ben.models.Product;
import edu.ben.models.User;

/**
 * Servlet implementation class ViewProducts
 */
@WebServlet("/ViewProducts")
public class ViewProducts extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProducts() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null && user.getSecurity().equals("50")/*and the security is correct*/) {
			ArrayList<Product> prods = ProductDao.getAllCds();
			request.setAttribute("prods", prods);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/viewProducts.jsp").forward(request, response);
			
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
		String url="/WEB-INF/jsp/viewProducts.jsp";
		String delete = request.getParameter("delete");
		String idProduct = request.getParameter("idProduct");
		String update = request.getParameter("update");
		
		int id = Integer.parseInt(idProduct);
		if (delete!= null) {
				ProductDao.remove(id);
				success.add("Product successfully removed.");
				ArrayList<Product> prods = ProductDao.getAllCds();
				request.setAttribute("prods", prods);
			
		} else if (update != null) {
			Product updateProd = ProductDao.getCdByCdId(id);
			request.getSession().setAttribute("updateProduct", updateProd);
			url = "/WEB-INF/jsp/updateProduct.jsp";
		}
		request.setAttribute("success",success);
		getServletContext().getRequestDispatcher(url).forward(request, response);;
	}


}
