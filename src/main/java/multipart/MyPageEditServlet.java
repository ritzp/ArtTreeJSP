package multipart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(
      location = "C:\\upload"
)
@WebServlet("/app_requests/my_page_edit")
public class MyPageEditServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,  HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
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
	}
}