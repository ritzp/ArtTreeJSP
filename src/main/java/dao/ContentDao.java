package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBConnection;
import dto.ContentDto;

public class ContentDao {
	Connection conn = DBConnection.getConnection();
	
	public int insert(ContentDto dto) {
		String sql = "insert into content values (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, dto.getContentId());
			stat.setString(2, dto.getExtension());
			stat.setString(3, dto.getTitle());
			stat.setString(4, dto.getDescription());
			stat.setInt(5, dto.getViews());
			stat.setString(6, dto.getUserId());
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ContentDto select(String contentId) {
		String sql = "select * from content where contentId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, contentId);
			ResultSet result = stat.executeQuery();
			
			ContentDto dto = new ContentDto();
			while (result.next()) {
				dto.setContentId(result.getString(1));
				dto.setExtension(result.getString(2));
				dto.setTitle(result.getString(3));
				dto.setDescription(result.getString(4));
				dto.setViews(result.getInt(5));
				dto.setUserId(result.getString(6));
			}
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int update(String column, String value, String contentId) {
		String sql = "update content set " + column + "=? where contentId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, value);
			stat.setString(2, contentId);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int update(String column, int value, String contentId) {
		String sql = "update content set " + column + "=? where contentId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setInt(1, value);
			stat.setString(2, contentId);
			System.out.println(stat.toString());
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<ContentDto> selectForListByKeyword(String keyword) {
		String sql = "select * from content where concat(title, description, userId) like \"%" + keyword + "%\"";
		System.out.println(sql);
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet result = stat.executeQuery();
			
			ArrayList<ContentDto> list = new ArrayList<>();
			while (result.next()) {
				ContentDto dto = new ContentDto();
				dto.setContentId(result.getString(1));
				dto.setExtension(result.getString(2));
				dto.setTitle(result.getString(3));
				dto.setDescription(result.getString(4));
				dto.setViews(result.getInt(5));
				dto.setUserId(result.getString(6));
				list.add(dto);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<ContentDto> selectForListByCategory(String keyword) {
		String searchKey = null;
		if (keyword.equals("photo")) {
			searchKey = "PH";
		} else if (keyword.equals("drawing")) {
			searchKey = "DR";
		} else if (keyword.equals("music")) {
			searchKey = "MU";
		} else if (keyword.equals("video")) {
			searchKey = "VI";
		} else if (keyword.equals("cartoon")) {
			searchKey = "CA";
		} else if (keyword.equals("novel")) {
			searchKey = "NO";
		}
		
		String sql = "select * from content where contentId like \"" + searchKey + "%\"";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet result = stat.executeQuery();
			
			ArrayList<ContentDto> list = new ArrayList<>();
			while (result.next()) {
				ContentDto dto = new ContentDto();
				dto.setContentId(result.getString(1));
				dto.setExtension(result.getString(2));
				dto.setTitle(result.getString(3));
				dto.setDescription(result.getString(4));
				dto.setViews(result.getInt(5));
				dto.setUserId(result.getString(6));
				list.add(dto);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<ContentDto> selectForList(String userId) {
		String sql = "select * from content where userId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, userId);
			ResultSet result = stat.executeQuery();
			
			ArrayList<ContentDto> list = new ArrayList<>();
			while (result.next()) {
				ContentDto dto = new ContentDto();
				dto.setContentId(result.getString(1));
				dto.setExtension(result.getString(2));
				dto.setTitle(result.getString(3));
				dto.setDescription(result.getString(4));
				dto.setViews(result.getInt(5));
				dto.setUserId(result.getString(6));
				list.add(dto);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<ContentDto> selectForList() {
		String sql = "select * from content limit 50";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			//stat.setString(1, userId);
			ResultSet result = stat.executeQuery();
			
			ArrayList<ContentDto> list = new ArrayList<>();
			while (result.next()) {
				ContentDto dto = new ContentDto();
				dto.setContentId(result.getString(1));
				dto.setExtension(result.getString(2));
				dto.setTitle(result.getString(3));
				dto.setDescription(result.getString(4));
				dto.setViews(result.getInt(5));
				dto.setUserId(result.getString(6));
				list.add(dto);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int selectForUploadedcontent(String userId) {
		String sql = "select count(*) from content where userId=?";
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
}