<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%@ page import="dao.ContentDao" %>
<%@ page import="dto.ContentDto" %>
<%@ page import="dao.LikeDao" %>
<%@ page import="dto.LikeDto" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%
if (request.getMethod().equals("POST")) {
	try {
		UserDao userDao = new UserDao();
		ContentDao contentDao = new ContentDao();
		LikeDao likeDao = new LikeDao();
		String searchMethod = request.getParameter("searchMethod");
		String keyword = request.getParameter("keyword");
		ArrayList<ContentDto> contentList = new ArrayList<>();
		if (searchMethod.equals("category")) {
			contentList = contentDao.selectForListByCategory(keyword);
		} else if (searchMethod.equals("search")) {
			contentList = contentDao.selectForListByKeyword(keyword);
		} else {
			contentList = contentDao.selectForList();
		}
		
	
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
	
		for (int i=0; i<contentList.size(); i++) {
			UserDto userDto = new UserDto();
			userDto = userDao.select(contentList.get(i).getUserId());
		
			JSONObject _jsonObject = new JSONObject();
			_jsonObject.put("contentId", contentList.get(i).getContentId());
			_jsonObject.put("title", contentList.get(i).getTitle());
			_jsonObject.put("views", contentList.get(i).getViews());
			_jsonObject.put("likes", likeDao.getLikes(contentList.get(i).getContentId()));
			_jsonObject.put("userId", userDto.getUserId());
			_jsonObject.put("nickname", userDto.getNickname());
			jsonArray.add(_jsonObject);
		}
		jsonObject.put("content", jsonArray);
		out.print(jsonObject);
	} catch(Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS DENIED");
}
%>