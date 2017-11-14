package edu.ben.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ben.models.Orderline;
import edu.ben.models.Product;

public class OrderlineDao {
	private static String TABLE = "mydb.orderline";

	/**
	 * Insert order line into order line table
	 * 
	 * @param orderLine
	 *            order line object to be inserted into table
	 */
	public static void insertOrderLine(Orderline orderLine) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "INSERT INTO " + TABLE + " (id_order, id_product, quanity) VALUES (?,?,?)";
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderLine.getIdOrder());
			ps.setInt(2, orderLine.getIdProduct());
			ps.setInt(3, orderLine.getQuantity());
			ps.executeUpdate();
			ps.close();
			conn.close();
			dbc.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get all lines for an order
	 * 
	 * @param idOrder
	 *            integer representing order id for which to get all the lines
	 * @return array list of order line objects
	 */
	public static ArrayList<Orderline> getLinesForOrder(int idOrder) {
		ArrayList<Orderline> lines = new ArrayList<Orderline>();
		Orderline line = new Orderline();
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "SELECT * FROM " + TABLE + " WHERE id_order=? AND is_active=1";
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idOrder);
			rs = ps.executeQuery();

			while (rs.next()) {
				line.setIdOrder(rs.getInt("id_order"));
				line.setIdOrderline(rs.getInt("id_orderline"));
				line.setIdProduct(rs.getInt("id_product"));
				line.setActive(rs.getBoolean("is_active"));
				line.setQuantity(rs.getInt("quantity"));
				Product p = ProductDao.getCdByCdId(line.getIdProduct());
				line.setProduct(p);
				lines.add(line);
				line = new Orderline();
			}
			ps.close();
			conn.close();
			return lines;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			dbc.closeConnection();
		}
	}

	/**
	 * Remove order line from table. In other words, set is_active to false.
	 * 
	 * @param idOrderline
	 *            integer representing order line id to remove
	 */
	public static void remove(int idOrderline) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "UPDATE " + TABLE + " SET is_active=0 WHERE id_orderline=?";

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idOrderline);
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
	 * Remove all order lines from an order. In other words, set is_active to false
	 * for all order lines associated with specifc order
	 * 
	 * @param idOrder
	 *            integer representing the id of the order to remove order lines
	 *            from
	 */
	public static void removeFromOrder(int idOrder) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		ArrayList<Orderline> lines = OrderlineDao.getLinesForOrder(idOrder);
		if (lines != null) {
			for (Orderline l : lines) {
				String sql = "UPDATE " + TABLE + " SET is_active=0 WHERE id_order=? AND id_orderline=?";
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1, idOrder);
					ps.setInt(2, l.getIdOrderline());
					ps.executeUpdate();
					ps.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
	}

	/**
	 * Update order line information
	 * 
	 * @param ol
	 *            order line object to be updated
	 */
	public static void update(Orderline ol) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "UPDATE " + TABLE + " SET quantity=? WHERE id_orderline=?";
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ol.getQuantity());
			ps.setInt(2, ol.getIdOrderline());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			dbc.closeConnection();
		}

	}

}
