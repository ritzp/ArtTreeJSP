package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnection;

public class UserDao {
	Connection conn = DBConnection.getConnection();
	
	public int insert(String userId, String email, String phoneNumber, String password, String nickname, String introduction) {
		String sql = "insert into user values (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, userId);
			stat.setString(2, email);
			stat.setString(3, phoneNumber);
			stat.setString(4, password);
			stat.setString(5, nickname);
			stat.setString(6, introduction);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ResultSet select(String column, String value) {
		String sql = "select * from user where " + column + "=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, value);
			return stat.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int update(String column, String before, String after) {
		String sql = "update user set " + after + " where " + column + "=" + before;
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}