package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward2;
import service.CircleArea;
import service.MyArea;
import service.RectangleArea;
import service.TriangleArea;

@WebServlet("*.do")
public class MyController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		MyArea myArea = null;
		
		ActionForward2 actionForward = null;
		
		switch(command) {
		case "input.do" :
			actionForward = new ActionForward2();
			actionForward.setView("views/input.jsp");
			break;
		case "triangle.do" :
			myArea = new TriangleArea();
			break;
		case "rectangle.do" :
			myArea = new RectangleArea();
			break;
		case "circle.do" :
			myArea = new CircleArea();
			break;
		}
		
		if(myArea != null) {
			actionForward = myArea.execute(request, response);
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
