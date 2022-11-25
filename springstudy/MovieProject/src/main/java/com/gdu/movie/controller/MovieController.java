package com.gdu.movie.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.movie.service.MovieService;
import com.gdu.movie.util.SecurityUtil;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private SecurityUtil securityUtil;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@ResponseBody
	@GetMapping(value="/searchAllMovies", produces="application/json; charset=UTF-8")
	public Map<String, Object> searchAll(HttpServletRequest request) {
		return movieService.findAllMovies(request);
	}

	@ResponseBody
	@GetMapping(value="/searchMovie", produces="application/json; charset=UTF-8")
	public Map<String, Object> searchmovie(HttpServletRequest request) {
		String column = request.getParameter("column");
		String searchText = securityUtil.preventXSS(request.getParameter("searchText"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("column", column);
		map.put("searchText", searchText);

		return movieService.findMovies(map);
	}

}
