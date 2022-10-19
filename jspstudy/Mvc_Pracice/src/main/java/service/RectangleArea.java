package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward2;

public class RectangleArea implements MyArea {

	@Override
	public ActionForward2 execute(HttpServletRequest request, HttpServletResponse repsonse) throws IOException {
		
		double width = Double.parseDouble(request.getParameter("width"));
		double height = Double.parseDouble(request.getParameter("height"));
		
		request.setAttribute("width", width);
		request.setAttribute("height", height);
		request.setAttribute("area", width * height);
		request.setAttribute("shape", "rectangle");
		
		ActionForward2 actionForward = new ActionForward2();
		actionForward.setView("views/output.jsp");
		actionForward.setRedirect(false);
		
		return actionForward;
	}

}
