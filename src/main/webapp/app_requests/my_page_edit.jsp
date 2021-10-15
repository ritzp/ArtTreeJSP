<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="java.io.*" %>
<%
if (request.getMethod().equals("POST")) {
	UserDao userDao = new UserDao();
	String userId = request.getParameter("userId");
	String nickname = request.getParameter("nickname");
	String introduction = request.getParameter("introduction");
	
	try {
		userDao.updateFromMyPageEdit(userId, nickname, introduction);
		out.print("SUCCESS");
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS DENIED");
}
%>