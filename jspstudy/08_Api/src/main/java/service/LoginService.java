package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
	public void execute(HttpServletRequest request, HttpServletResponse response);
}
