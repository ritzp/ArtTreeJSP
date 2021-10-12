<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="java.io.*" %>
<%
if (request.getMethod().equals("POST")) {
	String userId = request.getParameter("userId");
	Part icon = request.getPart("icon");
	try {
		File file = new File(request.getServletContext().getRealPath("/data/user/icon/" + userId + ".png"));
		if (file.exists()) {
			file.delete();
		}
		InputStream is = icon.getInputStream();
		FileOutputStream fos = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len=is.read(buffer))>0) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		is.close();
		out.print("SUCCESS");
	} catch (Exception e) {
		e.printStackTrace();
	}	
} else {
	out.print("ACCESS FAILED");
}
%>