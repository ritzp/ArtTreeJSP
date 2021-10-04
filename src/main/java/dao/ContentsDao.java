package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnection;

public class ContentsDao {
	Connection conn = DBConnection.getConnection();
	
	public int insert(String contentsId, String userId, String title, String description) {
		String sql = "insert into contents values (?, ?, ?, ?, 0, 0)";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, contentsId);
			stat.setString(2, userId);
			stat.setString(3, title);
			stat.setString(4,  description);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ResultSet select(String column, String value) {
		String sql = "select * from contents where " + column + "=?";
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
		String sql = "update contents set " + after + " where " + column + "=" + before;
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ResultSet selectList() {
		String sql = "select * from contents";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			return stat.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}