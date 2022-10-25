package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDao;

public class MemberModifyService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String grade = request.getParameter("grade");
		String address = request.getParameter("address");

		Member member = Member.builder()
				.id(id)
				.name(name)
				.gender(gender)
				.grade(grade)
				.address(address)
				.build();
		
		int result = 0;
		try {
			result = MemberDao.getInstance().updateMember(member);
			response.setContentType("application/json; charset=UTF-8");
			JSONObject obj = new JSONObject();
			obj.put("isSuccess", result > 0);
			
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.close();
			
		} catch(Exception e) {
			
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out= response.getWriter();
			out.println("회원 정보가 수정되지 않았습니다. \n입력 정보를 확인하세요");
			out.close();
			
		}
	}

}
