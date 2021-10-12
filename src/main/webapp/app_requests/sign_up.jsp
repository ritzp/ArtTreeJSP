<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%
if (request.getMethod().equals("POST")) {
	UserDao userDao = new UserDao();
	UserDto userDto = new UserDto();
	try {
		int method = Integer.parseInt(request.getParameter("method"));
		if (method == 0) {
			userDto.setEmail(request.getParameter("emailPhone"));
		} else if (method == 1) {
			userDto.setPhoneNumber(request.getParameter("emailPhone"));
		}
		userDto.setUserId(request.getParameter("userId"));
		userDto.setPassword(request.getParameter("password"));
		userDto.setNickname(request.getParameter("nickname"));
		userDto.setIntroduction(request.getParameter("introduction"));
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
	
	userDao.insert(userDto);
	out.print("SUCCESS");
} else {
	out.print("ACCESS FAILED");
}
%>