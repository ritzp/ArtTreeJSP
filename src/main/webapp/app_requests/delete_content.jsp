<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.ContentDao" %>
<%@ page import="dto.ContentDto" %>
<%@ page import="dao.CommentDao" %>
<%@ page import="dao.LikeDao" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FilenameFilter" %>
<%
if (request.getMethod().equals("POST")) {
	ContentDao contentDao = new ContentDao();
	CommentDao commentDao = new CommentDao();
	LikeDao likeDao = new LikeDao();
	final ContentDto contentDto = contentDao.select(request.getParameter("contentId"));
	String type = contentDto.getContentId().substring(0, 2);
	String category = null;
	if (type.equals("PH")) {
		category = "photo";
	} else if (type.equals("DR")) {
		category = "drawing";
	} else if (type.equals("MU")) {
		category = "music";
	} else if (type.equals("VI")) {
		category = "video";
	} else if (type.equals("CA")) {
		category = "cartoon";
	} else if (type.equals("NO")) {
		category = "novel";
	}
	
	try {
		File dir = new File(request.getServletContext().getRealPath("/data/content/" + category));
		File[] fileList = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith(contentDto.getContentId());
			}
		});
		for (int i=0; i<fileList.length; i++) {
			fileList[i].delete();
		}
		contentDao.delete(contentDto.getContentId());
		commentDao.deleteForContentDelete(contentDto.getContentId());
		likeDao.deleteForContentDelete(contentDto.getContentId());
		
		System.out.println("Delete Content - " + contentDto.getContentId());
		out.print("SUCCESS");
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS DENIED");
}
%>