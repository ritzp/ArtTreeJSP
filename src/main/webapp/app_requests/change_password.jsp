<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%
if (request.getMethod().equals("POST")) {
	UserDao userDao = new UserDao();
	try {
		int result = userDao.updateForChangingPassword(request.getParameter("userId"), request.getParameter("newPassword"));
		System.out.println("Changed Pass - " + request.getParameter("userId"));
		out.print("SUCCESS");
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS DENIED");
}
%>