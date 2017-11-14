package edu.ben.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ben.models.Order;
import edu.ben.models.Orderline;
import edu.ben.models.User;

public class OrderDao {
	private static String TABLE = "mydb.order";
	
	/**
	 * Insert order into order table
	 * @param order Order object to be inserted
	 */
	public void insert(Order order) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();
		
		String sql = "INSERT INTO " + TABLE + " (id_user, shipping_address, shipping_name, shipping_type ) VALUES (?,?,?,?)";
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, order.getIdUser());
			ps.setString(2, order.getShippingAddress());
			ps.setString(3, order.getShippingName());
			ps.setInt(4, order.getShippingType());
			
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
	 * Get order by the order ID
	 * @param idOrder integer representing the order id key
	 * @return order object if orderID is valid value in table, null otherwise
	 */
	public static Order getOrderByID(int idOrder){
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();
		Order order = new Order();
		
		try {
		String sql = "SELECT * FROM " + TABLE + " WHERE id_order=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idOrder);
		ResultSet rs = ps.executeQuery();
		
		
		if(rs.next()) {
			order.setIdOrder(rs.getInt("id_order"));
			order.setIdUser(rs.getInt("id_user"));
			order.setShippingAddress(rs.getString("shipping_address"));
			order.setShipped((rs.getBoolean("has_shipped")));
			order.setShippingName(rs.getString("shipping_name"));
			order.setShippingType(rs.getInt("shipping_type"));
			order.setLines(OrderlineDao.getLinesForOrder(order.getIdOrder()));
			return order;
		}
		ps.close();
		conn.close();
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			dbc.closeConnection();
		}
		return null;
		
	}
	
	/**
	 * Sets is_active parameter to false to disable the order
	 * @param orderID idOrder integer representing the order id key
	 */
	public static void remove(int orderID) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();
		OrderlineDao.removeFromOrder(orderID);
		String sql = "UPDATE " + TABLE + " SET is_active=0 WHERE id_order=?";

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}catch(SQLException e) {
			System.out.println(e);
		} finally {
			dbc.closeConnection();
		}
	}
	
	/**
	 * Update order information
	 * @param order order with updated inforamtion
	 */
	public static void update(Order order) {
		
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "UPDATE " + TABLE
				+ " SET shipping_address=?, shipping_name=?, has_shipped=?, shipping_type=? WHERE id_order=?";

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, order.getShippingAddress());
			ps.setString(2, order.getShippingName());
			ps.setBoolean(3, order.isShipped());
			ps.setInt(4, order.getShippingType());
			ps.setInt(5, order.getIdOrder());
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
	 * Get all orders in table
	 * @return ArrayList of orders if there are any, null otherwise
	 */
	public static ArrayList<Order> getAllOrders(){
		ArrayList<Order> orders = new ArrayList<Order>();
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();
		Order order = new Order();
		ArrayList<Orderline> lines = new ArrayList<Orderline>();
		
		
		try {
		String sql = "SELECT * FROM " + TABLE + " WHERE is_active=1 order by id_user";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			order.setIdOrder(rs.getInt("id_order"));
			order.setIdUser(rs.getInt("id_user"));
			order.setShippingAddress(rs.getString("shipping_address"));
			order.setShipped((rs.getBoolean("has_shipped")));
			order.setShippingName(rs.getString("shipping_name"));
			order.setShippingType(rs.getInt("shipping_type"));
			order.setLines(OrderlineDao.getLinesForOrder(order.getIdOrder()));
			orders.add(order);
			order = new Order();
		}
		ps.close();
		conn.close();
		return orders;
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			return null;
		} finally {
			dbc.closeConnection();
		}
		
	}
	
	/**
	 * Get orders by user who ordered them
	 * @param user user object to choose all associated orders of
	 * @return ArrayList of orders for that user
	 */
	public static ArrayList<Order> getOrdersByUser(User user) {
		ArrayList<Order> orders = new ArrayList<Order>();
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();
		Order order = new Order();
		
		String sql = "SELECT * FROM " + TABLE + " WHERE id_user=? AND is_active=1";

		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getIdUser());
			rs = ps.executeQuery();

			while (rs.next()) {
				order.setIdOrder(rs.getInt("id_order"));
				order.setIdUser(rs.getInt("id_user"));
				order.setShippingAddress(rs.getString("shipping_address"));
				order.setShipped((rs.getBoolean("has_shipped")));
				order.setShippingName(rs.getString("shipping_name"));
				order.setShippingType(rs.getInt("shipping_type"));
				orders.add(order);
				order = new Order();
			}
			return orders;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection();
		}
		return null;
		
	}
}
