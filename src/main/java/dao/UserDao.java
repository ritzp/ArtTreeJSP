package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnection;
import dto.UserDto;

public class UserDao {
	Connection conn = DBConnection.getConnection();
	
	public int insert(String userId, String email, String phoneNumber, String password, String nickname, String introduction) {
		String sql = "insert into creators_user values (?, ?, ?, ?, ?, ?)";
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
		return 0;
	}
	
	public UserDto select(String userId) {
		String sql = "select * from creators_user where userId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, userId);
			ResultSet result = stat.executeQuery();
			
			UserDto dto = new UserDto();
			while (result.next()) {
				dto.setUserId(result.getString(1));
				dto.setEmail(result.getString(2));
				dto.setPhoneNumber(result.getString(3));
				dto.setPassword(result.getString(4));
				dto.setNickname(result.getString(5));
				dto.setIntroduction(result.getString(6));
			}
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public UserDto selectForLogin(String id, String password) {
		String sql = "select * from creators_user where (userId=? or email=? or phoneNumber=?) and password=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, id);
			stat.setString(2, id);
			stat.setString(3, id);
			stat.setString(4, password);
			ResultSet result = stat.executeQuery();

			UserDto dto = new UserDto();
			if (result.next()) {
				dto.setUserId(result.getString(1));
				dto.setEmail(result.getString(2));
				dto.setPhoneNumber(result.getString(3));
				dto.setPassword(result.getString(4));
				dto.setNickname(result.getString(5));
				dto.setIntroduction(result.getString(6));
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
	
	public int updateFromMyPageEdit(String userId, String nickname, String introduction) {
		String sql = "update creators_user set nickname=?, introduction=? where userId=?";
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
}