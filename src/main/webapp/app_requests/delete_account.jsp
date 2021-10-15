<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dao.ContentDao" %>
<%@ page import="dto.ContentDto" %>
<%@ page import="dao.CommentDao" %>
<%@ page import="dao.LikeDao" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FilenameFilter" %>
<%@ page import="java.util.ArrayList" %>
<%
if (request.getMethod().equals("POST")) {
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	UserDao userDao = new UserDao();
	ContentDao contentDao = new ContentDao();
	CommentDao commentDao = new CommentDao();
	LikeDao likeDao = new LikeDao();
	ArrayList<ContentDto> contentList = new ArrayList<>();
	
	try {
		int count = userDao.selectForDeleteAccount(userId, password);
		if (count > 0) {
			contentList = contentDao.selectForList(userId);
			for (int i=0; i<contentList.size(); i++) {
				final String contentId = contentList.get(i).getContentId();
				String dirString = null;
				if (contentId.startsWith("PH")) {
					dirString = request.getServletContext().getRealPath("/data/content/photo");
				} else if (contentId.startsWith("DR")) {
					dirString = request.getServletContext().getRealPath("/data/content/drawing");
				} else if (contentId.startsWith("MU")) {
					dirString = request.getServletContext().getRealPath("/data/content/music");
				} else if (contentId.startsWith("VI")) {
					dirString = request.getServletContext().getRealPath("/data/content/video");
				} else if (contentId.startsWith("CA")) {
					dirString = request.getServletContext().getRealPath("/data/content/cartoon");
				} else if (contentId.startsWith("NO")) {
					dirString = request.getServletContext().getRealPath("/data/content/novel");
				}
				File fileDir = new File(dirString);
				File[] fileList = fileDir.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						return name.startsWith(contentId);
					}
				});
				for (int _i=0; _i<fileList.length; _i++) {
					fileList[_i].delete();
				}
			}
			File iconFile = new File(request.getServletContext().getRealPath("/data/user/icon/" + userId + ".png"));
			File headerFile = new File(request.getServletContext().getRealPath("/data/user/header/" + userId + ".jpg"));
			iconFile.delete();
			headerFile.delete();
			commentDao.deleteForAccountDelete(userId);
			likeDao.deleteForAccountDelete(userId);
			contentDao.deleteForAccountDelete(userId);
			userDao.delete(userId);
			out.print("SUCCESS");
		} else {
			out.print("FAILED");
		}		
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
} else {
	out.print("ACCESS DENIED");
}
%>