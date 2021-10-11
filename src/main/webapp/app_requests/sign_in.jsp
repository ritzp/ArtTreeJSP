<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="java.util.Enumeration" %>
<%
if (request.getMethod().equals("POST")) {
	try {
		UserDao userDao = new UserDao();
		UserDto userDto = new UserDto();
		userDto = userDao.selectForLogin(request.getParameter("id"), request.getParameter("password"));
			
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", userDto.getUserId());
		jsonObject.put("password", userDto.getPassword());
			
		out.print(jsonObject);
	} catch(Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS FAILED");
}
%>