<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%
if (request.getMethod().equals("POST")) {
	try {
		UserDao userDao = new UserDao();
		UserDto userDto = new UserDto();
		userDto.setUserId(request.getParameter("userId"));
		userDto.setEmail(request.getParameter("email"));
		userDto.setPhoneNumber(request.getParameter("phoneNumber"));
		userDto.setPassword(request.getParameter("password"));
		userDto.setNickname(request.getParameter("nickname"));
		userDto.setIntroduction(request.getParameter("introduction"));
			
		int result = userDao.insert(
				userDto.getUserId(),
				userDto.getEmail(),
				userDto.getPhoneNumber(),
				userDto.getPassword(),
				userDto.getNickname(),
				userDto.getIntroduction());
			
		if (result==1) {
			out.print("SUCCESS");
		} else {
			out.print("FAILED");
		}
	} catch(Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS FAILED");
}
%>