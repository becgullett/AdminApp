package edu.ben.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import edu.ben.daos.ProductDao;
import edu.ben.models.Product;
import edu.ben.models.ProductForm;
import edu.ben.models.User;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends Base {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProduct() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null && user.getSecurity()
				.equals("50")/* and the security is correct */) {
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/addProduct.jsp").forward(request, response);
		} else {
			errors.add("You are not authorized to access these pages. Please login or talk to site administrator.");
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		ProductForm pf = null;
		String url = "/WEB-INF/jsp/addProduct.jsp";
		// assign the form values to local variables
		String artist = request.getParameter("artist");
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		String p = request.getParameter("price");
		String s = request.getParameter("stock");

			System.out.println("NOT WHER");
			Product product = new Product();
			product.setArtist(artist);
			product.setGenre(genre);
			product.setTitle(title);
			boolean flag = true;
			try {
				Float price = Float.parseFloat(p);
				int stock = Integer.parseInt(s);
				product.setPrice(price);
				product.setStock(stock);
				pf = new ProductForm(artist, title, genre, price, stock);
			} catch (Exception e) {
				flag = false;
				errors.add("Please only include valid numbers in price and stock");
			}

			if (!checkProduct(product)) {
				flag = false;
				errors.add("Please fill out all fields.");
			}

			if (flag) {
				ProductDao.insert(product);
				success.add("Product added successfully");
			}

		request.setAttribute("pf", pf);
		request.setAttribute("errors", errors);
		request.setAttribute("success", success);
		getServletContext().getRequestDispatcher(url).forward(request, response);

	}

	private boolean checkProduct(Product product) {
		if (product.getArtist() != null && !product.getArtist().equals("") && product.getTitle() != null
				&& !product.getTitle().equals("") && product.getGenre() != null && !product.getGenre().equals("")) {
			return true;
		}
		return false;
	}
}
