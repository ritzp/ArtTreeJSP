<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%@ page import="dao.ContentsDao" %>
<%@ page import="dto.ContentsDto" %>
<%@ page import="java.sql.ResultSet" %>
<%
	if (request.getMethod().equals("POST")) {
		UserDao userDao = new UserDao();
		ContentsDao contentsDao = new ContentsDao();
		try {
			ResultSet contentsResult = contentsDao.selectList();
			out.print("{\"contents\":[");
			while (contentsResult.next()) {
				ContentsDto contentsDto = new ContentsDto();
				
				contentsDto.setContentsId(contentsResult.getString(1));
				contentsDto.setTitle(contentsResult.getString(2));
				contentsDto.setViews(contentsResult.getInt(4));
				contentsDto.setUserId(contentsResult.getString(6));
				
				UserDto userDto = new UserDto();
				userDto.setUserId(contentsDto.getUserId());
				
				ResultSet userResult = userDao.select("userId", userDto.getUserId());
				while (userResult.next()) {
					userDto.setNickname(userResult.getString("nickname"));
				}
				
				out.print("{" +
						"\"contentsId\":\"" + contentsDto.getContentsId() + "\"," +
						"\"title\":\"" + contentsDto.getTitle() + "\"," +
						"\"views\":\"" + contentsDto.getViews() + "\"," +
						"\"userId\":\"" + userDto.getUserId() + "\"," +
						"\"nickname\":\"" + userDto.getNickname() + "\"," +
						"\"icon\":\"" + userDto.getIcon() +
						"\"" + "}");
			}
			out.print("]}");
		} catch(Exception e) {
			out.print("SERVER ERROR");
			e.printStackTrace();
		}
	} else {
		out.print("REQUEST ERROR");
	}
%>