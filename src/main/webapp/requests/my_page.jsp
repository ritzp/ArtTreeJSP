<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%@ page import="java.sql.ResultSet" %>
<%
	if (request.getMethod().equals("POST")) {
		UserDao userDao = new UserDao();
		UserDto userDto = new UserDto();
		try {
			userDto.setUserId(request.getParameter("userId"));
			ResultSet userResult = userDao.select("userId", userDto.getUserId());
			while (userResult.next()) {
				userDto.setNickname(userResult.getString(5));
				userDto.setIntroduction(userResult.getString(6));
			}
			out.print("{" +
				"\"nickname\":\"" + userDto.getNickname() + "\"," +
				"\"introduction\":\"" +	userDto.getIntroduction() + "\"," +
				"\"icon\":\"" + userDto.getIcon() + "\"," +
				"\"header\":\"" + userDto.getHeader() +
				"\"" + "}");
		} catch(Exception e) {
			out.print("SERVER ERROR");
			e.printStackTrace();
		}
	} else {
		out.print("REQUEST ERROR");
	}
%>