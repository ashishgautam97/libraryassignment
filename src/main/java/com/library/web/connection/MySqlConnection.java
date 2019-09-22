package com.library.web.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

	private MySqlConnection() {}
	
	public static Connection getConnection() {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsys","root","123456")) {
		    System.out.println("Database connected!");
		    return connection;
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
}
