package prac;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Prac03A")
public class Prac03A extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String content = request.getParameter("content");
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String title = sdf.format(date1) +"-" + URLEncoder.encode(to, "UTF-8") + ".txt";
		
		// 디렉터리 생성
				File dir = new File(request.getServletContext().getRealPath("storage"));
				if(dir.exists() == false) {
					dir.mkdirs();
				}
				
				// 파일 객체
				File file = new File(dir, title);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write("to." + to + "\n");
		bw.write(content + "\n");
		bw.write("from." + from);
		bw.close();
		
		
		response.setContentType("text/html; charset=UTF-8");
		response.sendRedirect("/01_Servlet/Prac03B?fileName=" + title);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
