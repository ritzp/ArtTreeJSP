package dto;

public class ContentsDto {
	private String contentsId;
	private String userId;
	private String title;
	private String description;
	private int views;
	private int likes;
	
	public String getContentsId() {
		return contentsId;
	}
	
	public void setContentsId(String contentsId) {
		this.contentsId = contentsId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getViews() {
		return views;
	}
	
	public void setViews(int views) {
		this.views = views;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
}
