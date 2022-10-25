package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDao;

public class StudentRemoveService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
	
		Optional<String> opt = Optional.ofNullable(request.getParameter("stuNo"));
		int stuNo = Integer.parseInt(opt.orElse("0"));
		
		int result = StudentDao.getInstance().deleteStudent(stuNo);
		
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('삭제가 완료되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/student/list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('삭제가 실패했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		out.close();
		
		return null;
	}
}