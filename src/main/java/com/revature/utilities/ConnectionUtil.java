package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() {
		
		String url = "jdbc:postgresql://localhost:5432/";
		String username = "postgres";
		String password = "password";
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
		}
		
		return conn;
	}
}