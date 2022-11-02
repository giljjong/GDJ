package com.gdu.app05.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.sevice.MovieService;

@Controller
public class MovieController {
	
	@GetMapping("movie")
	public String movie() {
		return "movie";
	}
	
	@Autowired
	private MovieService movieService;
	
	@ResponseBody
	@GetMapping(value="movie/boxOfficeList"
			   ,produces=MediaType.APPLICATION_JSON_VALUE)
	public String getBoxoffice(String targetDt) {
		return movieService.getBoxoffice(targetDt);
	}
	
}
