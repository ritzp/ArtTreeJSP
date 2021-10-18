<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%
if (request.getMethod().equals("POST")) {
	UserDao userDao = new UserDao();
	try {
		int result = userDao.updateForForgotPassword(request.getParameter("email"), request.getParameter("newPassword"));
		out.print("SUCCESS" + result);
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS DENIED");
}
%>