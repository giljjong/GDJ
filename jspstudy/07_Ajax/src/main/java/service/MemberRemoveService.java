package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import repository.MemberDao;

public class MemberRemoveService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("memberNo"));
		int memberNo = Integer.parseInt(opt.orElse("0"));
		
		response.setContentType("application/json");
		
		try {
			int result = MemberDao.getInstance().deleteMember(memberNo);
			JSONObject obj = new JSONObject();
			obj.put("isSuccess", result > 0);
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.close();
		} catch(Exception e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("존재하는 회원이 없습니다.");
			out.close();
		}
	}

}
