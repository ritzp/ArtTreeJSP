<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%
if (request.getMethod().equals("POST")) {
	UserDao userDao = new UserDao();
	UserDto userDto = new UserDto();
	JSONObject jsonObject = new JSONObject();
	try {
		userDto = userDao.selectForSignIn(request.getParameter("id"), request.getParameter("password"));
		if (userDto != null) {
			jsonObject.put("message", "SUCCESS");
			jsonObject.put("userId", userDto.getUserId());
			jsonObject.put("password", userDto.getPassword());
			jsonObject.put("nickname", userDto.getNickname());
			
			out.print(jsonObject);
		} else {			
			jsonObject.put("message", "FAILED");
			
			out.print(jsonObject);
		}
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS FAILED");
}
%>