package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBConnection;
import dto.CommentDto;

public class CommentDao {
	Connection conn = DBConnection.getConnection();
	
	public int insert(CommentDto dto) {
		String sql = "insert into content_comment values (null, ?, ?, ?)";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, dto.getContentId());
			stat.setString(2, dto.getUserId());
			stat.setString(3, dto.getComment());
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<CommentDto> select(String contentId) {
		String sql = "select * from content_comment where contentId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, contentId);
			ResultSet result = stat.executeQuery();
			
			ArrayList<CommentDto> list = new ArrayList<>();
			while (result.next()) {
				CommentDto dto = new CommentDto();
				dto.setCommentId(result.getInt(1));
				dto.setContentId(result.getString(2));
				dto.setUserId(result.getString(3));
				dto.setComment(result.getString(4));
				list.add(dto);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int delete(int commentId) {
		String sql = "delete from content_comment where commentId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setInt(1, commentId);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteForContentDelete(String contentId) {
		String sql = "delete from content_comment where contentId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, contentId);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteForAccountDelete(String userId) {
		String sql = "delete from content_comment where userId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, userId);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/*public int update(String column, String before, String after) {
		String sql = "update comment set " + after + " where " + column + "=" + before;
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}*/
	
	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
