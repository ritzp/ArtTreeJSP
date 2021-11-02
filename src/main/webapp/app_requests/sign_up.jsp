<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dto.UserDto" %>
<%@ page import="util.PasswordEncryptor" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<% 
if (request.getMethod().equals("POST")) {
	UserDao userDao = new UserDao();
	UserDto userDto = new UserDto();
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	String date = format.format(Calendar.getInstance().getTime());
	
	try {
		PasswordEncryptor encryptor = new PasswordEncryptor();
		String encPass = encryptor.encrypt(date, request.getParameter("password"));
		
		int method = Integer.parseInt(request.getParameter("method"));
		userDto.setUserId(request.getParameter("userId"));
		userDto.setEmail(request.getParameter("emailPhone"));
		userDto.setPassword(encPass);
		userDto.setNickname(request.getParameter("nickname"));
		userDto.setIntroduction(request.getParameter("introduction"));
		userDto.setCreationDate(date);
		System.out.println("Sign Up - " + userDto.getUserId());
	} catch (Exception e) {
		out.print("SERVER ERROR");
		e.printStackTrace();
	}
	
	userDao.insert(userDto);
	out.print("SUCCESS");
} else {
	out.print("ACCESS DENIED");
}
%>