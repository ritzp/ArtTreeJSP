package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnection;

public class CommentDao {
	Connection conn = DBConnection.getConnection();
	
	public int insert(String contentsId, String userId, String comment) {
		String sql = "insert into comment values (?, ?, ?)";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, contentsId);
			stat.setString(2, userId);
			stat.setString(3, comment);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ResultSet select(String column1, String value1, String column2, String value2) {
		String sql = "select * from comment where " + column1 + "=? and " + column2 + "=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, value1);
			stat.setString(2, value2);
			return stat.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int update(String column, String before, String after) {
		String sql = "update comment set " + after + " where " + column + "=" + before;
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
