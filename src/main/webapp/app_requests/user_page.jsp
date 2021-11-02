<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%@ page import="dao.ContentDao" %>
<%@ page import="dao.LikeDao" %>
<%@ page import="dao.SubscribeDao" %>
<%@ page import="dto.SubscribeDto" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%
if (request.getMethod().equals("POST")) {
	try {
		UserDao userDao = new UserDao();
		ContentDao contentDao = new ContentDao();
		LikeDao likeDao = new LikeDao();
		SubscribeDao subscribeDao = new SubscribeDao();
		UserDto userDto = userDao.select(request.getParameter("userId"));
		SubscribeDto subscribeDto = new SubscribeDto();
		subscribeDto.setSubscribingUserId(request.getParameter("accessingUserId"));
		subscribeDto.setSubscribedUserId(request.getParameter("userId"));
	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("nickname", userDto.getNickname());
		jsonObject.put("introduction", userDto.getIntroduction());
		jsonObject.put("content", contentDao.selectForUploadedcontent(userDto.getUserId()));
		jsonObject.put("likes", likeDao.getLikesOfUser(userDto.getUserId()));
		jsonObject.put("subscribed", subscribeDao.isSubscribed(subscribeDto));
	
		out.print(jsonObject);
	} catch(Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS DENIED");
}
%>