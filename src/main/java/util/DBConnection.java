package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() {
		try {
			String dbAddress = "localhost:3306";
			String dbUrl = "jdbc:mysql://" + dbAddress + "/arttree";
			String dbId = "root";
			String dbPass = "1234";
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection(dbUrl, dbId, dbPass);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}