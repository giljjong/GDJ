package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward2;

public interface MyArea {
	public ActionForward2 execute(HttpServletRequest request, HttpServletResponse repsonse) throws IOException;
}
