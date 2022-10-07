package ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FromServlet")
public class FromServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청
		request.setCharacterEncoding("UTF-8");
		
		// 변수(파라미터)
		String id = request.getParameter("id");
		if(id == null || id.isEmpty()) {
			id="빈아이디";
		}
		String pwd = request.getParameter("pwd");
		if(pwd == null || pwd.isEmpty()) {
			pwd="빈 비밀번호";
		}
		String gender = request.getParameter("gender");
		String city = request.getParameter("city");
		
		// 배열(파라미터)
		String[] phone = request.getParameterValues("phone");
		if(phone[0].isEmpty()) {
			phone[0] = "빈 전화1";
		};
		if(phone[1].isEmpty()) {
			phone[1] = "빈 전화2";
		};
		if(phone[2].isEmpty()) {
			phone[2] = "빈 전화3";
		};
		String strPhone = phone[0] + "-" + phone[1] + "-" + phone[2];
		String[] agree = request.getParameterValues("agree");
		if(agree == null) {
			agree = new String[1];
			agree[0] = "빈동의";
		};
		List<String> list = Arrays.asList(agree);
		
		// 연습 이메일
		String emailId =request.getParameter("email_id");
		String domain = request.getParameter("email");
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>안녕하세요 " + id + "님 환영합니다.</h3>");
		out.println("<h3>비밀번호는 " + pwd + "입니다.</h3>");
		out.println("<h3>성별은 " + gender + "를 선택하신게 맞습니까?</h3>");
		out.println("<h3>거주지는 " + city +"</h3>");
		out.println("<h3>연락처 : " + strPhone + "</h3>");
		out.println("<h3>약관 동의 목록 : " + Arrays.toString(agree) + "</h3>");
		out.println("<h3>이메일 : " + emailId + "@" + domain + "</h3>");
		if(list.contains("marketing")) {
			out.println("<h3>마케팅 동의한 회원</h3>");
		}
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
