package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app04.domain.Member;

@RequestMapping("member")	// URL Mapping이 member로 시작하는 모든 요청을 처리하는 Controller이다
@Controller
public class MyController1 {
	
	@GetMapping("detail1")
	public String detail1(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		/*
		 * @RequestParam
		 * 	1. value   		: 파라미터 이름
		 * 	2. required 	: 파라미터의 필수 여부 (디폴트는 true)
		 *  3. defaultValue : 파라미터가 없을 때 사용할 값
		 */
		Member member = new Member(id, pw);
		
		// forward 할 데이터를 request에 담아 두는 방법
		request.setAttribute("member", member);
		
		return "member/detail";		// view 폴더 아래 member 폴더 안의 detail.jsp로 forward해라
	}
	
	// location.herf="${contextPath}/member/detail2?id=admin&pw=1234";
	
	@GetMapping("detail2")
	public String detail2(@RequestParam(value="id", required=false, defaultValue="master") String id
						  , @RequestParam(value="pw", required=false, defaultValue="1111") String pw
						  , Model model) {
		Member member = new Member(id, pw);
		
		// forward할 데이터를 model에 담아 두는 방법(스프링 방식)
		// request를 이용하는 방식에 비해 보안이 향상되었다
		model.addAttribute("member", member);	// model은 request를 저장소로 활용한다
		
		return "member/detail";
	}
	
	// location.herf="${contextPath}/member/detail3?id=admin&pw=1234";
	@GetMapping("detail3")
	public String detail3(String id, String pw, Model model) {
		
		Member member = new Member(id, pw);
		model.addAttribute("member", member);
		return "member/detail";
	}
	
	@GetMapping("detail4")
	public String getDetail4(Member member
							, Model model) {

		model.addAttribute("member", member);
		return "member/detail";
	}
	
	@PostMapping("detail4")
	public String postDetail4(@ModelAttribute(value="member") Member member) { // 파라미터 id, pw를 이용해 Member member를 만들고, Model에 member라는 이름의 속성으로 저장하시오
		
	return "member/detail";
	}
	
}
