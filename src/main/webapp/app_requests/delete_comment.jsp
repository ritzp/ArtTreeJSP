<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.CommentDao" %>
<%@ page import="dto.CommentDto" %>
<%@ page import="java.io.File" %>
<%
if (request.getMethod().equals("POST")) {
	try {
		CommentDao commentDao = new CommentDao();
		commentDao.delete(Integer.parseInt(request.getParameter("commentId")));
		out.print("SUCCESS");
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS DENIED");
}
%>