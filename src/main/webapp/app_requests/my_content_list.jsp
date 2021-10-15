<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.ContentDao" %>
<%@ page import="dto.ContentDto" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%
if (request.getMethod().equals("POST")) {
	try {
		ContentDao contentDao = new ContentDao();
		ArrayList<ContentDto> contentList = new ArrayList<>();
		contentList = contentDao.selectForList(request.getParameter("userId"));
	
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
	
		for (int i=0; i<contentList.size(); i++) {	
			JSONObject _jsonObject = new JSONObject();
			_jsonObject.put("contentId", contentList.get(i).getContentId());
			_jsonObject.put("title", contentList.get(i).getTitle());
			jsonArray.add(_jsonObject);
		}
		jsonObject.put("content", jsonArray);
		out.print(jsonObject);
	} catch(Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS DENIED");
}
%>