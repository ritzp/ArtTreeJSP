package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnection;
import dto.UserDto;

public class UserDao {
	Connection conn = DBConnection.getConnection();
	
	public int insert(UserDto dto) {
		String sql = "insert into arttree_user values (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, dto.getUserId());
			stat.setString(2, dto.getEmail());
			stat.setString(3, dto.getPassword());
			stat.setString(4, dto.getNickname());
			stat.setString(5, dto.getIntroduction());
			stat.setString(6, dto.getCreationDate());
			return stat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public UserDto select(String id) {
		String sql = "select * from arttree_user where (userId=? or email=?)";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, id);
			stat.setString(2, id);
			ResultSet result = stat.executeQuery();
			
			UserDto dto = new UserDto();
			while (result.next()) {
				dto.setUserId(result.getString(1));
				dto.setEmail(result.getString(2));
				dto.setPassword(null);
				dto.setNickname(result.getString(4));
				dto.setIntroduction(result.getString(5));
			}
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public UserDto selectForSignIn(String id, String password) {
		String sql = "select * from arttree_user where (userId=? or email=?) and password=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, id);
			stat.setString(2, id);
			stat.setString(3, password);
			ResultSet result = stat.executeQuery();

			UserDto dto = new UserDto();
			if (result.next()) {
				dto.setUserId(result.getString(1));
				dto.setEmail(result.getString(2));
				dto.setPassword(null);
				dto.setNickname(result.getString(4));
				dto.setIntroduction(result.getString(5));
				return dto;
			} else {
				dto = null;
				return dto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int selectForDeleteAccount(String userId, String password) {
		String sql = "select count(*) from arttree_user where userId=? and password=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, userId);
			stat.setString(2, password);
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
	
	public int updateFromMyPageEdit(String userId, String nickname, String introduction) {
		String sql = "update arttree_user set nickname=?, introduction=? where userId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, nickname);
			stat.setString(2, introduction);
			stat.setString(3, userId);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateForChangingEmail(String userId, String email) {
		String sql = "update arttree_user set email=? where userId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, email);
			stat.setString(2, userId);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateForChangingPassword(String userId, String newPassword) {		
		String sql = "update arttree_user set password=? where userId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, newPassword);
			stat.setString(2, userId);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateForForgotPassword(String email, String newPassword) {		
		String sql = "update arttree_user set password=? where email=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, newPassword);
			stat.setString(2, email);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int delete(String userId) {
		String sql = "delete from arttree_user where userId=?";
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