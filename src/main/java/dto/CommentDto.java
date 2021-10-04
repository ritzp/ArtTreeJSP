package dto;

public class CommentDto {
	private String contentsId;
	private String userId;
	private String comment;
	
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
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}	
}
