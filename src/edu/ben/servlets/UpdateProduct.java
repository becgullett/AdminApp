package edu.ben.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ben.daos.ProductDao;
import edu.ben.models.Product;
import edu.ben.models.ProductForm;
import edu.ben.models.User;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/UpdateProduct")
public class UpdateProduct extends Base {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		Product updateProd = (Product) request.getSession().getAttribute("updateProduct");
		if (user != null && user.getSecurity().equals("50")/*and the security is correct*/) {
			if(updateProd != null) {
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/updateProduct.jsp").forward(request, response);
			} else {
				request.setAttribute("errors", errors);
				errors.add("Please choose a product to update");
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/viewProducts.jsp").forward(request, response);
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
		String artist = request.getParameter("artist");
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		String url = request.getParameter("URL");
		String p = request.getParameter("price");
		String s = request.getParameter("stock");
		String idProduct = request.getParameter("idProduct");
		int id = Integer.parseInt(idProduct);
		
		Product product = new Product();
		product.setArtist(artist);
		product.setGenre(genre);
		product.setTitle(title);
		product.setIdProduct(id);
		ProductForm pf = null;
		
		boolean flag = true;
		try {
			Float price = Float.parseFloat(p);
			int stock = Integer.parseInt(s);
			product.setPrice(price);
			product.setStock(stock);
			pf = new ProductForm(artist, title, genre, price, stock);
		} catch(Exception e) {
			flag = false;
			pf = new ProductForm(artist, title, genre, 0 , 0);
			errors.add("Please only include valid numbers in price and stock");
		}
		
		if (!checkProduct(product)) {
			flag = false;
			errors.add("Please fill out all fields.");
		}
		
		if (flag) {
			ProductDao.update(product);
			success.add("Product updated.");
		}
		
		request.setAttribute("pf", pf);
		request.setAttribute("errors", errors);
		request.setAttribute("success", success);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/updateProduct.jsp").forward(request, response);

		
		
		
	}
	
	private boolean checkProduct(Product product) {
		if (product.getArtist() != null && !product.getArtist().equals("") && product.getTitle() != null
				&& !product.getTitle().equals("") && product.getGenre() != null && !product.getGenre().equals("")) {
			return true;
		}
		return false;
	}

}
