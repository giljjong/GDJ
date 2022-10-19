package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward2;

public class CircleArea implements MyArea {

	@Override
	public ActionForward2 execute(HttpServletRequest request, HttpServletResponse repsonse) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		int radius = Integer.parseInt(request.getParameter("radius"));
		double area = (double) Math.pow(radius, 2) * Math.PI;
		
		request.setAttribute("radius", radius);
		request.setAttribute("area", area);
		request.setAttribute("shape", "circle");
		
		ActionForward2 actionForward = new ActionForward2();
		actionForward.setView("views/output.jsp");
		actionForward.setRedirect(false);
		
		
		return actionForward;
	}

}
