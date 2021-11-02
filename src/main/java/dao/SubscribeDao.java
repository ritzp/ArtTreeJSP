package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.SubscribeDto;
import util.DBConnection;

public class SubscribeDao {
Connection conn = DBConnection.getConnection();
	
	public int insert(SubscribeDto dto) {
		String sql = "insert into user_subscribe values (?, ?)";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, dto.getSubscribingUserId());
			stat.setString(2, dto.getSubscribedUserId());
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean isSubscribed(SubscribeDto dto) {
		String sql = "select count(*) from user_subscribe where subscribingUserId=? and subscribedUserId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, dto.getSubscribingUserId());
			stat.setString(2, dto.getSubscribedUserId());
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

	public int delete(SubscribeDto dto) {
		String sql = "delete from user_subscribe where subscribingUserId=? and subscribedUserId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, dto.getSubscribingUserId());
			stat.setString(2, dto.getSubscribedUserId());
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteForAccountDelete(String userId) {
		String sql = "delete from content_like where subscribingUserId=? or subscribedUserId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, userId);
			stat.setString(2, userId);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}	
}
