<%@ page import="java.io.*" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="dao.ContentDao" %>
<%@ page import="dto.ContentDto" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" %>
<%
if (request.getMethod().equals("POST")) {		
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
	Calendar calendar = Calendar.getInstance();
	String date = format.format(calendar.getTime());
	String category = request.getParameter("category");
	String type = "";
	if (category.equals("photo")) {
		type = "PH";
	} else if (category.equals("drawing")) {
		type = "DR";
	} else if (category.equals("music")) {
		type = "MU";
	} else if (category.equals("video")) {
		type = "VI";
	} else if (category.equals("cartoon")) {
		type = "CA";
	} else if (category.equals("novel")) {
		type = "NO";
	}
	int number = 1;
	String contentId = type + date + number;
	String extension = request.getParameter("extension");
	String title = request.getParameter("title");
	String description = request.getParameter("description");
	String userId = request.getParameter("userId");
		
	System.out.println("/data/content/" + category + "/" + contentId + "." + extension);
		
	File file = new File(request.getServletContext().getRealPath("/data/content/" + category + "/" + contentId + "." + extension));
	while (file.exists()) {
		number++;
		contentId = category + "/" + type + date + number;
		file = new File(request.getServletContext().getRealPath("/data/content/" + category + "/" + contentId + "." + extension));
	}
		
	try {         
		Part part = request.getPart("file");
			
		InputStream is = part.getInputStream();
		FileOutputStream fos = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len=is.read(buffer))>0) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		is.close();
        System.out.println("Upload Done");
		out.print("SUCCESS");
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
		
	ContentDao contentDao = new ContentDao();
	ContentDto contentDto = new ContentDto();
		
	contentDto.setContentId(contentId);
	contentDto.setExtension(extension);
	contentDto.setTitle(title);
	contentDto.setDescription(description);
	contentDto.setViews(0);
	contentDto.setUserId(userId);
	contentDao.insert(contentDto);
} else {
	out.print("ACCESS FAILED");
}
%>