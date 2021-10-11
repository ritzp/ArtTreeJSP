<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.LikeDao" %>
<%@ page import="dto.LikeDto" %>
<%@ page import="org.json.simple.JSONObject" %>
<%
if (request.getMethod().equals("POST")) {
	LikeDao likeDao = new LikeDao();
	LikeDto likeDto = new LikeDto();
	
	likeDto.setContentId(request.getParameter("contentId"));
	likeDto.setUserId(request.getParameter("userId"));
	boolean isLiked = likeDao.isLiked(likeDto);
	
	try {
		JSONObject jsonObject = new JSONObject();
		
		if (isLiked) {
			likeDao.delete(likeDto);
			jsonObject.put("like", false);
			jsonObject.put("likes", likeDao.getLikes(likeDto.getContentId()));
		} else {
			likeDao.insert(likeDto);
			jsonObject.put("like", true);
			jsonObject.put("likes", likeDao.getLikes(likeDto.getContentId()));
		}
		out.print(jsonObject);
	} catch (Exception e) {
		out.print("SERVER ERROR");
	}
	
} else {
	out.print("ACCESS FAILED");
}
%>