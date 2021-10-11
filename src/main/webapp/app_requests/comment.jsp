<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.CommentDao" %>
<%@ page import="dto.CommentDto" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%
if (request.getMethod().equals("POST")) {
	try {
		CommentDao commentDao = new CommentDao();
		CommentDto commentDto = new CommentDto();
	
		commentDto.setContentId(request.getParameter("contentId"));
		commentDto.setUserId(request.getParameter("userId"));
		commentDto.setComment(request.getParameter("comment"));
		
		commentDao.insert(commentDto);
		out.print("SUCCESS");
	} catch(Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS FAILED");
}
%>