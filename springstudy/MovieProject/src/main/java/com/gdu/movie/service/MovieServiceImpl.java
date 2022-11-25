package com.gdu.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.movie.domain.MovieDTO;
import com.gdu.movie.mapper.MovieMapper;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieMapper movieMapper;
	
	@Override
	public Map<String, Object> findAllMovies(HttpServletRequest request) {
		
		int movieCnt = movieMapper.selectAllMoviesCount();
		
		List<MovieDTO> list = movieMapper.selectAllMovies();
		
		Map<String, Object> map = new HashMap<>();

			if(list.size() == 0) {
				map.put("message", "조회된 검색 결과가 없습니다.");
				map.put("list", null);
				map.put("status", 500);
			} else {
				map.put("message", "전체" + movieCnt + "개의 목록을 가져왔습니다.");
				map.put("list", list);
				map.put("status", 200);
			}
		
		return map;
	}
	
	@Override
	public Map<String, Object> findMovies(Map<String, Object> map) {
		

		List<MovieDTO> list = movieMapper.selectMoviesByQuery(map);
		
		Map<String, Object> result = new HashMap<>();
		
		if(list.size() == 0) {
			result.put("message", map.get("searchText") + " 검색 결과가 없습니다.");
			result.put("list", null);
			result.put("status", 500);
		} else {
			result.put("message", list.size() + "개의 검색 결과가 있습니다.");
			result.put("list", list);
			result.put("status", 200);
		}
		
		return result;
	}

}
