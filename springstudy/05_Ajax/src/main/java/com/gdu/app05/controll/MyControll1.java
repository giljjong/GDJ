package com.gdu.app05.controll;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.domain.Member;
import com.gdu.app05.sevice.MemberService;
import com.gdu.app05.sevice.MemberServiceImpl;

@Controller
public class MyControll1 {

	@GetMapping("/")
	public String welcom() {
		return "index";
	}
	
	@GetMapping("member")
	public String member() {
		return "member";
	}
	
	// field
	@Autowired
	private MemberService memberService = new MemberServiceImpl();
	
	/*
	 * 	@ResponsBody
	 * 	안녕. 난 ajax 처리하는 메소드야
	 * 	내가 반환하는 값은 뷰(JSP)이름이 아니고 어떤 데이터(text, json, xml)야
	 */
	
	@ResponseBody
	@GetMapping(value="member/detail1"
			  , produces="text/plain; charset=UTF-8")
	public String detail1(HttpServletRequest request, HttpServletResponse response) {
		String str = memberService.execute1(request, response);
		return str;
	}
	
	@ResponseBody
	@GetMapping(value="member/detail2"
			  , produces="application/json; charset=UTF-8")
	public Member detail2(@RequestParam(value="id") String id,
						  @RequestParam(value="pw") String pw) {
		Member member = memberService.execute2(id, pw);
		return member;
	}
	
	@ResponseBody
	@GetMapping(value="member/detail3"
			  , produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> detail3(Member member){
		return memberService.execute3(member);
	}
	
	
}
