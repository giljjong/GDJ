package com.gdu.app12.contoller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.app12.service.BbsService;

@Controller
public class BbsContoller {

	@Autowired
	private BbsService bbsService;
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/bbs/list")
	public String list(HttpServletRequest request, Model model) {
		bbsService.findAllBbsLists(request, model);
		return "bbs/list";
	}
	
	@GetMapping("/bbs/write")
	public String write() {
		return "bbs/write";
	}
	
	@PostMapping("/bbs/add")
	public String add(HttpServletRequest request, Model model) {
		bbsService.addBbs(request);
		return "redirect:/bbs/list";
	}
	
	@PostMapping("/bbs/remove")
	public String remove(int bbsNo) {
		bbsService.removeBbs(bbsNo);
		return "redirect:/bbs/list";
	}
	
	@PostMapping("/bbs/reply/add")
	public String addReply(HttpServletRequest request) {
		bbsService.addReply(request);
		return "redirect:/bbs/list";
	}
	
	
}
