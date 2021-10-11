<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="java.io.*" %>
<%
if (request.getMethod().equals("POST")) {
	UserDao userDao = new UserDao();
	String userId = request.getParameter("userId");
	String nickname = request.getParameter("nickname");
	String introduction = request.getParameter("introduction");
	Part icon = request.getPart("icon");
	Part header = request.getPart("header");
	
	try {
		if (icon != null) {
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
		}
		if (header != null) {
			File file = new File(request.getServletContext().getRealPath("/data/user/header/" + userId + ".jpg"));
			if (file.exists()) {
				file.delete();
			}
			InputStream is = header.getInputStream();
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len=is.read(buffer))>0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			is.close();
		}
		userDao.updateFromMyPageEdit(userId, nickname, introduction);
		out.print("SUCCESS");
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS FAILED");
}
%>