package multipart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

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
@WebServlet("/app_requests/sign_up_icon")
public class IconUploadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,  HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	
		String userId = request.getParameter("userId");
		Part icon = request.getPart("icon");
		PrintWriter out = response.getWriter();

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
			out.print("SERVER ERROR");
			e.printStackTrace();
		}	
	}
}