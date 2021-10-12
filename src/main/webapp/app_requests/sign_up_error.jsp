<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%@ page import="java.io.*" %>
<%
if (request.getMethod().equals("POST")) {
	UserDao userDao = new UserDao();
	String userId = request.getParameter("userId");
	try {
		userDao.delete(userId);
		File iconFile = new File(request.getServletContext().getRealPath("/data/user/icon/" + userId + ".png"));
		if (iconFile.exists()) {
			iconFile.delete();
		}
		File headerFile = new File(request.getServletContext().getRealPath("/data/user/header/" + userId + ".jpg"));
		if (headerFile.exists()) {
			headerFile.delete();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}	
} else {
	out.print("ACCESS FAILED");
}
%>