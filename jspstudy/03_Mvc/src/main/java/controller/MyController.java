package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.AdderService;
import service.MyService;
import service.NowService;
import service.TodayService;

// @WebServlet({"/today.do", "now.do", "adder.do"})
@WebServlet("*.do")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청 확인
		String requestURI = request.getRequestURI();						// requestURI  : /03_Mvc/today.do 또는 /03_Mvc/now.do
		String contextPath = request.getContextPath();						// contextPath : /03_Mvc
		String command = requestURI.substring(contextPath.length() + 1);	// command 	   : today.do 또는 now.do
		
		MyService myService = null;
		
		// ActionForward 선언
		// 1. 모든 Model의 execute 반환타입은 ActionForward이다
		// 2. Model이 필요 없는 경우 ActionForward를 직접 만든다
		ActionForward actionForward = null;
		
		// 요청에 따른 Model의 선택
		switch(command) {
		// 비즈니스 로직이 필요한 경우
		case "today.do" :
			myService = new TodayService();
			break;
		
		case "now.do" :
			myService = new NowService();
			break;
			
		case "adder.do" :
			myService = new AdderService();
			break;
		
		// 비즈니스 로직이 필요 없는 단순 이동의 경우
		case "input.do":
			actionForward = new ActionForward();
			actionForward.setView("views/input.jsp");
			break;
		}
		
		if(myService != null) {
			actionForward = myService.execute(request, response);
		};
		
		// 이동(리다이렉트, 포워드)
		// 1. actionForward != null : 리다이렉트 또는 포워드
		// 2. actionForward == null : response로 응답한 경우 또는 ajax 처리
		
		if(actionForward != null) {
			if(actionForward.isRedirect()) {
				response.sendRedirect(actionForward.getView());
			} else {
				request.getRequestDispatcher(actionForward.getView()).forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
