<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%@ page import="dao.ContentsDao" %>
<%@ page import="dto.ContentsDto" %>
<%@ page import="java.sql.ResultSet" %>
<%
	if (request.getMethod().equals("POST")) {
		UserDao userDao = new UserDao();
		UserDto userDto = new UserDto();
		ContentsDao contentsDao = new ContentsDao();
		ContentsDto contentsDto = new ContentsDto();
		try {
			contentsDto.setContentsId(request.getParameter("contentsId"));
			ResultSet contentsResult = contentsDao.select("contentsId", contentsDto.getContentsId());
			while (contentsResult.next()) {
				contentsDto.setTitle(contentsResult.getString(2));
				contentsDto.setDescription(contentsResult.getString(3));
				contentsDto.setViews(contentsResult.getInt(4));
				contentsDto.setLikes(contentsResult.getInt(5));
				contentsDto.setUserId(contentsResult.getString(6));				
			}
			
			userDto.setUserId(contentsDto.getUserId());
			ResultSet userResult = userDao.select("userId", userDto.getUserId());
			while (userResult.next()) {
				userDto.setNickname(userResult.getString(5));
			}
			
			out.print("{" +
					"\"contentsId\":\"" + contentsDto.getContentsId() + "\"," +
					"\"title\":\"" + contentsDto.getTitle() + "\"," +
					"\"description\":\"" + contentsDto.getDescription() + "\"," +
					"\"views\":\"" + contentsDto.getViews() + "\"," +
					"\"likes\":\"" + contentsDto.getLikes() + "\"," +
					"\"userId\":\"" + userDto.getUserId() + "\"," +
					"\"nickname\":\"" + userDto.getNickname() + "\"," +
					"\"icon\":\"" + userDto.getIcon() +
					"\"" + "}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else {
		out.print("REQUEST ERROR");
	}
%>