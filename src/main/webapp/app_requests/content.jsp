<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%@ page import="dao.ContentDao" %>
<%@ page import="dto.ContentDto" %>
<%@ page import="dao.CommentDao" %>
<%@ page import="dto.CommentDto" %>
<%@ page import="dao.LikeDao" %>
<%@ page import="dto.LikeDto" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%
if (request.getMethod().equals("POST")) {
	UserDao userDao = new UserDao();
	ContentDao contentDao = new ContentDao();;
	CommentDao commentDao = new CommentDao();
	LikeDao likeDao = new LikeDao();
	try {
		ContentDto contentDto = new ContentDto();
		UserDto userDto = new UserDto();
		LikeDto likeDto = new LikeDto();
		ArrayList<CommentDto> commentArray = new ArrayList<>();
		
		contentDto = contentDao.select(request.getParameter("contentId"));
		userDto = userDao.select(contentDto.getUserId());
		likeDto.setUserId(request.getParameter("userId"));
		likeDto.setContentId(contentDto.getContentId());
		boolean isLiked = likeDao.isLiked(likeDto);
		commentArray = commentDao.select(contentDto.getContentId());
	
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
	
		if (request.getParameter("isFirst").equals("true")) {
			contentDto.setViews(contentDto.getViews()+1);
			contentDao.update("views", contentDto.getViews(), contentDto.getContentId());
		}
		
		JSONObject _jsonObject = new JSONObject();
		JSONArray _jsonArray = new JSONArray();
		
		_jsonObject.put("contentId", contentDto.getContentId());
		_jsonObject.put("extension", contentDto.getExtension());
		_jsonObject.put("title", contentDto.getTitle());
		_jsonObject.put("description", contentDto.getDescription());
		_jsonObject.put("tag", contentDto.getTag());
		_jsonObject.put("views", contentDto.getViews());
		_jsonObject.put("likes", likeDao.getLikes(contentDto.getContentId()));
		_jsonObject.put("userId", contentDto.getUserId());
		_jsonObject.put("nickname", userDto.getNickname());
		_jsonObject.put("isLiked", isLiked);
		_jsonObject.put("comments", commentArray.size());
		jsonArray.add(_jsonObject);
		jsonObject.put("content", jsonArray);
		
		for (int i=0; i<commentArray.size(); i++) {
			UserDto __userDto = new UserDto();
			__userDto = userDao.select(commentArray.get(i).getUserId());
		
			JSONObject __jsonObject = new JSONObject();
	
			__jsonObject.put("commentId",commentArray.get(i).getCommentId());
			__jsonObject.put("userId", __userDto.getUserId());
			__jsonObject.put("nickname", __userDto.getNickname());
			__jsonObject.put("comment", commentArray.get(i).getComment());
			_jsonArray.add(__jsonObject);					
		}
		jsonObject.put("comment", _jsonArray);
	
		out.print(jsonObject);
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS DENIED");
}
%>