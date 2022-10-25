package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDao;

public class MemberDetailService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("memberNo"));
		int memberNo = Integer.parseInt(opt.orElse("0"));
		
		response.setContentType("application/json charset=UTF-8");
		
		Member member = MemberDao.getInstance().selectMemberByNo(memberNo);
		boolean exists = member != null;
		
		JSONObject obj = new JSONObject();
		obj.put("exists", exists);
		if(exists) {
			obj.put("member", new JSONObject(member));
		}
		
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
		out.close();
		
	}

}
