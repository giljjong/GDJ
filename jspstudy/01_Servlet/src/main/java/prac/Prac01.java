package prac;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://localhost:9090/01_Servlet/now 주소를 입력하면
// 현재 날짜와 시간을 보여주기

@WebServlet("/now")
public class Prac01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Prac01() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html lang=\"ko\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>");
		out.println("현재 시간");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div>");
		out.println("<h1> 오늘 날짜는" + year + "년" + month + "월" + day + "일 </h1>");
		out.println("<h1> 현재 시간-" + hour + ":" + minute + ":" + second + "</h1>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
