package edu.ben.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	/**
	 * username for database connection
	 */
	private String username = "root";

	/**
	 * password for database connection
	 */
	private String password = "root";

	/**
	 * database url string
	 */
	private String url = "jdbc:mysql://localhost:3306/mydb";

	private Connection conn = null;

	/**
	 * gets the connection with database
	 */
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Cant get connection");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Driver manager not foun");
			e.printStackTrace();
		}
	}

	/**
	 * getter for connection variable
	 * 
	 * @return
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * closes connection
	 */
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Can't close connection");
			e.printStackTrace();
		}
	}

}
