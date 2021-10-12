<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%
if (request.getMethod().equals("POST")) {
	UserDao userDao = new UserDao();
	UserDto userDto = new UserDto();
	try {
		userDto = userDao.select(request.getParameter("userId"));
		if (userDto != null) {			
			out.print("EXISTS");
		} else {				
			out.print("NOT EXISTS");
		}
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS FAILED");
}
%>