package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app04.domain.Board;

@RequestMapping("board")
@Controller
public class MyController2 {
	
	@GetMapping("detail1")
	public String detail1(HttpServletRequest request) {
		
		String title = request.getParameter("title");
		String hit = request.getParameter("hit");
		request.setAttribute("title", title);
		request.setAttribute("hit", hit);
		
		return "redirect:/board/detail2?title=" + title + "&hit=" + hit;
	}
	
	@GetMapping("detail2")
	public String detail2(String title, int hit, Model model){

		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		
		return "board/detail";
	}
	
	@GetMapping("detail3")
	public String detail3(Board board, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("board", board);	
		
		return "redirect:/board/detail4";	// 새로운 요청에 파라미터를 추가하지 않았음을 주의할 것
	}
	
	@GetMapping("detail4")
	public String detail4() {
		return "board/detail";
	}
}
