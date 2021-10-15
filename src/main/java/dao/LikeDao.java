package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.LikeDto;
import util.DBConnection;


public class LikeDao {
	Connection conn = DBConnection.getConnection();
	
	public int insert(LikeDto dto) {
		String sql = "insert into content_like values (?, ?)";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, dto.getUserId());
			stat.setString(2, dto.getContentId());
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean isLiked(LikeDto dto) {
		String sql = "select count(*) from content_like where userId=? and contentId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, dto.getUserId());
			stat.setString(2, dto.getContentId());
			ResultSet result = stat.executeQuery();
			int count = 0;
			while (result.next()) {
				count = result.getInt(1);
			}
			if (count == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getLikes(String contentId) {
		String sql = "select count(*) from content_like where contentId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, contentId);
			ResultSet result = stat.executeQuery();
			int count = 0;
			while (result.next()) {
				count = result.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getLikesOfUser(String userId) {
		String sql = "select count(*) from content_like as cl " +
				"inner join (select * from content where userId=?) as c " +
				"on cl.contentId = c.contentId;";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, userId);
			ResultSet result = stat.executeQuery();
			int count = 0;
			while (result.next()) {
				count = result.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

	public int delete(LikeDto dto) {
		String sql = "delete from content_like where userId=? and contentId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, dto.getUserId());
			stat.setString(2, dto.getContentId());
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteForAccountDelete(String userId) {
		String sql = "delete from content_like where userId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, userId);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
