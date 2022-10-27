package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.NaverCaptchaService;
import service.NaverCaptchaServiceImpl;

@WebServlet("*.do")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		NaverCaptchaService service = new NaverCaptchaServiceImpl();
		
		ActionForward af = null;
		
		switch(urlMapping) {
		case "/member/loginPage.do" :
			// capchakey 발급요청
			String key = service.getCaptchaKey();
			Map<String, String> map = service.getCaptchaImage(request, key);
			
			request.setAttribute("dirname", map.get("dirname"));
			request.setAttribute("fileName", map.get("fileName"));
			request.setAttribute("key", key);
			
			af = new ActionForward("/member/login.jsp", false);
			break;
		case "/member/refreshCaptcha.do" :
			service.refreshCaptcha(request, response);
			break;
		case "/member/validateCaptcha.do" :
			boolean result = service.validateUserInput(request);
			if(result) {
				af = new ActionForward("/member/Success.jsp", false);
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('자동입력 방지문자를 확인하세요.')");
				out.println("location.href='" + request.getContextPath() + "/member/refreshCaptcha.do';");
				out.println("</script>");
				out.close();
			}
			break;
		}

		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
