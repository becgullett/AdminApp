package edu.ben.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ben.models.Product;

public class ProductDao {
	private static String TABLE = "mydb.product";

	/**
	 * Insert product into product table
	 * 
	 * @param product
	 *            product object to be inserted
	 */
	public static void insert(Product product) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "INSERT INTO " + TABLE + " (artist, title, genre, price, stock, url) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getArtist());
			ps.setString(2, product.getTitle());
			ps.setString(3, product.getGenre());
			ps.setFloat(4, product.getPrice());
			ps.setInt(5, product.getStock());
			ps.setString(6, product.getUrl());
			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection();
		}

	}

	/**
	 * Decrease number of items in stock
	 * 
	 * @param num
	 *            integer representing the number to decrease the stock by
	 * @param idProduct
	 *            integer representing id of the product to be updated
	 */
	public static void decreaseStock(int num, int idProduct) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "SELECT * FROM " + TABLE + " WHERE id_=" + idProduct;
		PreparedStatement ps, ps2;
		ResultSet rs;
		int prevStock = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			prevStock = rs.getInt("stock");
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int newStock = prevStock - num;

		String sql2 = "UPDATE " + TABLE + " SET stock=" + newStock + " WHERE id_product=" + idProduct;

		try {
			ps2 = conn.prepareStatement(sql2);
			ps2.executeUpdate();
			ps2.close();
			conn.close();
			dbc.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get cd by the id
	 * 
	 * @param cdId
	 *            integer representing cd id
	 * @return product object if id is valid, null otherwise
	 */
	public static Product getCdByCdId(int cdId) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();
		Product cd = new Product();
		String sql = "SELECT * FROM " + TABLE + " WHERE id_product=?";

		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cdId);
			rs = ps.executeQuery();
			if (rs.next()) {
				cd.setIdProduct(rs.getInt("id_product"));
				cd.setArtist(rs.getString("artist"));
				cd.setTitle(rs.getString("title"));
				cd.setGenre(rs.getString("genre"));
				cd.setPrice(rs.getFloat("price"));
				cd.setStock(rs.getInt("stock"));
				cd.setUrl(rs.getString("url"));
			}

			ps.close();
			rs.close();
			conn.close();

			return cd;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			dbc.closeConnection();
		}

	}

	/**
	 * Get all cds
	 * 
	 * @return array list of product objects
	 */
	public static ArrayList<Product> getAllCds() {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "SELECT * FROM " + TABLE + " WHERE is_active=1";

		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Product> cdArrayList = new ArrayList<Product>();
		Product cd = new Product();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				cd.setIdProduct(rs.getInt("id_product"));
				cd.setArtist(rs.getString("artist"));
				cd.setTitle(rs.getString("title"));
				cd.setGenre(rs.getString("genre"));
				cd.setPrice(rs.getFloat("price"));
				cd.setStock(rs.getInt("stock"));
				cd.setUrl(rs.getString("url"));
				cdArrayList.add(cd);
				cd = new Product();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cdArrayList;
	}

	/**
	 * Remove product from product table. In other words, set is_active to false.
	 * 
	 * @param idProduct
	 *            integer representing product id to be removed
	 */
	public static void remove(int idProduct) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "UPDATE " + TABLE + " SET is_active=0 WHERE id_product=?";

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idProduct);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			dbc.closeConnection();
		}
	}

	/**
	 * Update product information in product table
	 * 
	 * @param product
	 *            product object to be updated
	 */
	public static void update(Product product) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "UPDATE " + TABLE + " SET artist=?, title=?, genre=?, price=?, stock=? WHERE id_product=?";
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getArtist());
			ps.setString(2, product.getTitle());
			ps.setString(3, product.getGenre());
			ps.setFloat(4, product.getPrice());
			ps.setInt(5, product.getStock());
			ps.setInt(6, product.getIdProduct());
			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection();
		}

	}

}
