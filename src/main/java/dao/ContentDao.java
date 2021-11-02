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
		String sql = "insert into content values (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, dto.getContentId());
			stat.setString(2, dto.getExtension());
			stat.setString(3, dto.getTitle());
			stat.setString(4, dto.getDescription());
			stat.setString(5, dto.getTag());
			stat.setInt(6, dto.getViews());
			stat.setString(7, dto.getUserId());
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
				dto.setTag(result.getString(5));
				dto.setViews(result.getInt(6));
				dto.setUserId(result.getString(7));
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
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int delete(String contentId) {
		String sql = "delete from content where contentId=?";
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
		String sql = "delete from content where userId=?";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, userId);
			return stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<ContentDto> selectForHome() {
		String sql = "select c.*, count(l.contentId) as likes from (select * from content as c order by cast(substring(contentId, 3, 12) as unsigned) desc limit 50) as c " +
				"left outer join content_like as l " +
				"on c.contentId = l.contentId " +
				"group by c.contentId " +
				"order by likes desc";
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
				dto.setTag(result.getString(5));
				dto.setViews(result.getInt(6));
				dto.setUserId(result.getString(7));
				list.add(dto);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return null;
		}
	
	public ArrayList<ContentDto> selectForListByKeyword(String keyword) {
		String sql = "select * from content as c " +
						"inner join (select userId, nickname from arttree_user) as u " +
						"on c.userId = u.userId " +
						"where concat(title, description, tag, nickname) like \"%" + keyword + "%\"";
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
				dto.setTag(result.getString(5));
				dto.setViews(result.getInt(6));
				dto.setUserId(result.getString(7));
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
		if (keyword.equals("Photo")) {
			searchKey = "PH";
		} else if (keyword.equals("Drawing")) {
			searchKey = "DR";
		} else if (keyword.equals("Music")) {
			searchKey = "MU";
		} else if (keyword.equals("Video")) {
			searchKey = "VI";
		} else if (keyword.equals("Cartoon")) {
			searchKey = "CA";
		} else if (keyword.equals("Novel")) {
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
				dto.setTag(result.getString(5));
				dto.setViews(result.getInt(6));
				dto.setUserId(result.getString(7));
				list.add(dto);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<ContentDto> selectForListByLiked(String keyword) {
		String sql = "select c.* from content as c " +
						"inner join (select * from content_like where userId=\"" + keyword + "\") as l " +
				 		"on c.contentId = l.contentId;";
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
				dto.setTag(result.getString(5));
				dto.setViews(result.getInt(6));
				dto.setUserId(result.getString(7));
				list.add(dto);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<ContentDto> selectForListBySubscriptions(String keyword) {
		String sql = "select c.* from content as c " +
						"inner join (select * from user_subscribe where subscribingUserId=\"" + keyword + "\") as s " +
						"on c.userId = s.subscribedUserId";
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
				dto.setTag(result.getString(5));
				dto.setViews(result.getInt(6));
				dto.setUserId(result.getString(7));
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
				dto.setTag(result.getString(5));
				dto.setViews(result.getInt(6));
				dto.setUserId(result.getString(7));
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
				dto.setTag(result.getString(5));
				dto.setViews(result.getInt(6));
				dto.setUserId(result.getString(7));
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