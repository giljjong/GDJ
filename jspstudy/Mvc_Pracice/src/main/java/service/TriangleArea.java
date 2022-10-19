package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward2;

public class TriangleArea implements MyArea {

	@Override
	public ActionForward2 execute(HttpServletRequest request, HttpServletResponse repsonse) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		int width = Integer.parseInt(request.getParameter("width"));
		int height = Integer.parseInt(request.getParameter("height"));
		int area = width * height;
		
		request.setAttribute("width", width);
		request.setAttribute("height", height);
		request.setAttribute("area", area);
		request.setAttribute("shape", "triangle");
		
		ActionForward2 actionForward = new ActionForward2();
		actionForward.setView("views/output.jsp");
		actionForward.setRedirect(false);
		
		
		return actionForward;
	}

}
