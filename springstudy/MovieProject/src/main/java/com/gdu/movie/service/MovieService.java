package com.gdu.movie.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MovieService {
	
	public Map<String, Object> findAllMovies(HttpServletRequest request);
	public Map<String, Object> findMovies(Map<String, Object> map);
}
