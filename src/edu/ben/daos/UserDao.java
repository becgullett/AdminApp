package edu.ben.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import edu.ben.models.User;
import edu.ben.util.TestMail;

public class UserDao {
	private static String TABLE = "mydb.user";

	/**
	 * Insert user object into user table
	 * 
	 * @param user
	 *            user object to be inserted into table
	 * @param username
	 *            String of admin username that created user
	 */
	public static void insert(User user, String username) {

		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "INSERT INTO " + TABLE
				+ " (first_name, last_name, email, username, password, created_by, security) VALUES (?,?,?,?,?,?,?)";

		int security = getSecurityType(user.getSecurity());
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getUsername());
			ps.setString(5, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			ps.setString(6, username);
			ps.setInt(7, security);
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
	 * Get user from database
	 * 
	 * @param username
	 *            username for user
	 * @return user object or null if user info is incorrect does not exist
	 */
	public static User getUser(User user) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "SELECT * FROM " + TABLE + " WHERE username=? AND is_active=1 AND is_locked=0";

		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			if (rs.next()) {
				if (BCrypt.checkpw(user.getPassword(), rs.getString("password"))) {
					user.setIdUser(rs.getInt("id_user"));
					user.setFirstName(rs.getString("first_name"));
					user.setLastName(rs.getString("last_name"));
					user.setEmail(rs.getString("email"));
					user.setUsername(rs.getString("username"));
					user.setSecurity(rs.getString("security"));
					ps.close();
					conn.close();
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection();
		}
		return null;

	}

	/**
	 * Get a user by username
	 * 
	 * @param username
	 *            String to select row from
	 * @return user object if username exists and is active or null otherwise
	 */
	public static User getUserByUsername(String username) {
		User user = new User();
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "SELECT * FROM " + TABLE + " WHERE username=? AND is_active=1";

		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				user.setIdUser(rs.getInt("id_user"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setSecurity(rs.getString("security"));
				user.setLocked(rs.getBoolean("is_locked"));
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			dbc.closeConnection();
		}
		return null;
	}

	/**
	 * Get user by userID
	 * 
	 * @param idUser
	 *            integer represnting user id
	 * @return user object if userID is valid and is active, null otherwise
	 */
	public static User getUserByUserId(int idUser) {
		User user = new User();
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "SELECT * FROM " + TABLE + " WHERE id_user=?";

		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idUser);
			rs = ps.executeQuery();

			if (rs.next()) {
				user.setIdUser(rs.getInt("id_user"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setSecurity(rs.getString("security"));
				user.setLocked(rs.getBoolean("is_locked"));
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			dbc.closeConnection();
		}
		return null;
	}

	/**
	 * Check if the username exists in the database
	 * 
	 * @param username
	 *            string usernmae
	 * @return true if user exists, false otherwise
	 */
	public static boolean userExists(String username) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "SELECT username FROM " + TABLE + " WHERE username=?";

		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();

			return rs.next();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		} finally {
			dbc.closeConnection();
		}

	}

	/**
	 * get all users created by single specified admin
	 * 
	 * @param username
	 *            string representing admin thay created users
	 * @return array list of user objects associated with that admin creator
	 */
	public static ArrayList<User> getUserByCreatedBy(String username) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();
		ArrayList<User> users = new ArrayList<User>();
		User user = new User();

		String sql = "SELECT * FROM " + TABLE + " WHERE created_by=? AND is_active=1";

		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();

			while (rs.next()) {
				user.setIdUser(rs.getInt("id_user"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setSecurity(rs.getString("security"));
				user.setLocked(rs.getBoolean("is_locked"));
				users.add(user);
				user = new User();

			}
			ps.close();
			conn.close();
			return users;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnection();
		}
		return null;
	}

	/**
	 * Remove a user by username. In other words, set is_active to false
	 * 
	 * @param username
	 *            String of username associated with user to remove
	 */
	public static void remove(String username) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "UPDATE " + TABLE + " SET is_active=0 WHERE username=?";

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
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
	 * Update user information
	 * 
	 * @param user
	 *            user object to be updated
	 */
	public static void update(User user) {

		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();

		String sql = "UPDATE " + TABLE
				+ " SET first_name=?, last_name=?, email=?, security=?, is_locked=? WHERE username=?";

		int security = getSecurityType(user.getSecurity());
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setInt(4, security);
			ps.setBoolean(5, user.isLocked());
			ps.setString(6, user.getUsername());
			user.setSecurity(Integer.toString(security));
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
	 * Reset password of a user and send them an email of their new password
	 * 
	 * @param user
	 *            user object to reset password for
	 */
	public static void resetPassword(User user) {
		String from = "buwebhound";
		String pass = "PuppyChow#17";
		String password = createRandomPassword();
		// String admin_username = userModel.getAdmin_username();
		String body = "Hello " + user.getFirstName() + " " + user.getLastName() + "!\n\n" + "Password reset for "
				+ "your Disc Drop account:" + "\n\n" + "Login: " + user.getUsername() + "\n" + "\n" + "New Password: "
				+ password + "\nFirst Name: " + user.getFirstName() + "\n" + "Last Name: " + user.getLastName() + "\n\n"
				+ "You should change your password upon logging in!\n\n" + "Sincerely,\n\n" + "Disc Drop\n"
				+ "buwebhound@gmail.com";
		String[] to = { user.getEmail() };
		String subject = "Updated Information for DISC Drop";

		TestMail.sendFromGMail(from, pass, to, subject, body);

		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();
		String sql = "UPDATE " + TABLE + " SET password=? WHERE username=?";
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, BCrypt.hashpw(password, BCrypt.gensalt()));
			ps.setString(2, user.getUsername());
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
	 * Update password of a user
	 * 
	 * @param user
	 *            user object to update password for
	 * @return true if password was updated, false if an exception was thrown
	 */
	public static boolean updatePassword(User user) {
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConn();
		String sql = "UPDATE " + TABLE + " SET password=? WHERE username=?";
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			ps.setString(2, user.getUsername());
			ps.executeUpdate();
			ps.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			dbc.closeConnection();
		}

	}

	/**
	 * Generate a random password
	 * 
	 * @return String represdenting randomly generated password
	 */
	private static String createRandomPassword() {
		String pass = "";
		String[] alphabet = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };
		String[] numbers = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		while (pass.length() < 8) {
			int rand1 = (int) (Math.random() * 2 + 1);
			int rand2 = (int) (Math.random() * 25 + 1);
			int rand3 = (int) (Math.random() * 9 + 1);
			if (rand1 == 1) {
				pass += alphabet[rand2];
			} else {
				pass += numbers[rand3];
			}
		}
		return pass;
	}

	/**
	 * Alternative to actually using the role table, get the integer representation
	 * associated with the security type
	 * 
	 * @param security
	 *            String representing the security type
	 * @return integer representation of corresponding security type
	 */
	private static int getSecurityType(String security) {
		if (security.equalsIgnoreCase("shopper")) {
			return 20;
		} else if (security.equalsIgnoreCase("administrator")) {
			return 50;
		}
		return 10;
	}

}
