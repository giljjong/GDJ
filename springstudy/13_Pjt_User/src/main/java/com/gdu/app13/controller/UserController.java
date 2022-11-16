package com.gdu.app13.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app13.service.UserService;

import oracle.jdbc.proxy.annotation.Post;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/user/agree")
	public String agree() {
		return "user/agree";
	}
	
	@GetMapping("/user/join/write")
	public String joinWrite(@RequestParam(required=false) String location,
							@RequestParam(required=false) String promotion, Model model) {
		model.addAttribute("location", location);
		model.addAttribute("promotion", promotion);
		return "user/join";
	}
	
	@ResponseBody
	@GetMapping(value="/user/checkSameId", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> checkReduceId(String id) {
		return userService.isReduce(id);
	}
	
	@ResponseBody
	@GetMapping(value="/user/checkReduceEmail", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> checkReduceEmail(String email) {
		return userService.isReduceEmail(email);
	}
	
	@ResponseBody
	@GetMapping(value="/user/sendAuthCode", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getAuthCode(String email) {
		return userService.sendAuthCode(email);
	}
	
	@PostMapping("/user/join")
	public void join(HttpServletRequest request, HttpServletResponse response) {
		userService.join(request, response);
	}
	
	@GetMapping("/user/retire")
	public void retire(HttpServletRequest request, HttpServletResponse response) {

		userService.retire(request, response);
	}
	@GetMapping("/user/login/form")
	public String lofinForm(HttpServletRequest request, Model model) {
		model.addAttribute("url", request.getHeader("referer"));
		return "user/login";
	}
	
	@PostMapping("/user/login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		userService.login(request, response);
	}
	
	@GetMapping("/user/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "redirect:/";
	}
}
