package multipart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import dao.ContentDao;
import dao.UserDao;
import dto.ContentDto;
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
@WebServlet("/app_requests/upload_multiple")
public class UploadMultipleServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,  HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
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
		String[] extension = request.getParameter("extensions").split("/");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String tag = request.getParameter("tag");
		String userId = request.getParameter("userId");
			
		try {
			ContentDao contentDao = new ContentDao();
			ContentDto contentDto = contentDao.select(contentId);
			while (contentDto.getContentId() != null) {
				number++;
				contentId = type + date + number;
				contentDto = contentDao.select(contentId);
			}
			
			Collection<Part> parts = request.getParts();
			for (int i=0; i<parts.size(); i++){
				File file = new File(request.getServletContext().getRealPath("/data/content/" + category + "/" + contentId + "-" + i + "." + extension[i]));
				
				Part part = request.getPart("file"+i);
				InputStream is = part.getInputStream();
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len=is.read(buffer))>0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				is.close();
				System.out.println("/data/content/" + category + "/" + contentId + "." + extension[i]);
		        System.out.println("Upload Done");
			}
			out.print("SUCCESS");
		} catch (Exception e) {
			out.print("SERVER ERROR");
			e.printStackTrace();
		}
			
		ContentDao contentDao = new ContentDao();
		ContentDto contentDto = new ContentDto();
			
		contentDto.setContentId(contentId);
		contentDto.setExtension(request.getParameter("extensions"));
		contentDto.setTitle(title);
		contentDto.setDescription(description);
		contentDto.setTag(tag);
		contentDto.setViews(0);
		contentDto.setUserId(userId);
		contentDao.insert(contentDto);
	}
}