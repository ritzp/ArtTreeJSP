<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%
if (request.getMethod().equals("POST")) {
	UserDao userDao = new UserDao();
	UserDto userDto = new UserDto();
	try {
		userDto = userDao.select(request.getParameter("id"));
		if (userDto.getUserId() != null) {			
			out.print("EXISTS");
		} else {				
			out.print("AVAILABLE");
		}
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS DENIED");
}
%>