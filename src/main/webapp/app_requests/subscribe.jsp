<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.SubscribeDao" %>
<%@ page import="dto.SubscribeDto" %>
<%
if (request.getMethod().equals("POST")) {
	try {	
		SubscribeDao subscribeDao = new SubscribeDao();
		SubscribeDto subscribeDto = new SubscribeDto();
		subscribeDto.setSubscribingUserId(request.getParameter("subscribingUserId"));
		subscribeDto.setSubscribedUserId(request.getParameter("subscribedUserId"));
		
		subscribeDao.insert(subscribeDto);
		out.print("SUCCESS");
	} catch(Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS DENIED");
}
%>