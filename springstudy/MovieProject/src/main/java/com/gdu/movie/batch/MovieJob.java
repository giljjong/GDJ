package com.gdu.movie.batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.movie.domain.MovieDTO;
import com.gdu.movie.mapper.MovieMapper;

@EnableScheduling
@Component
public class MovieJob {

	@Autowired
	private MovieMapper movieMapper;
	
	@Scheduled(cron = "0 0/1 * * * *")
	public void execute() throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		String searchText = "코미디";
		
		map.put("column", "GENRE");
		map.put("searchText", searchText);
		
		List<MovieDTO> list = movieMapper.selectMoviesByQuery(map);
		
		String fileName;
		BufferedWriter bw = null;
		File file = null;
		
		if(list.size() == 0) {
			fileName = "error.txt";
			
			file = new File(fileName);
			
			if(file.exists()) {
				file.delete();
			}
			
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(searchText + " 검색 결과가 없습니다.");
		} else {
			fileName = searchText + ".txt";
			
			file = new File(fileName);
			
			if(file.exists()) {
				file.delete();
			}
				
			StringBuilder sb = new StringBuilder();
			for(MovieDTO movie : list) {
				sb.append("제목 : " + movie.getTitle() + "\n");
				sb.append("장르 : " + movie.getGenre() + "\n");
				sb.append("개요 : " + movie.getDescription() + "\n");
				sb.append("평점 : " + movie.getStar() + "\n");
			}
			
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(sb.toString());
		}
		
		bw.flush();
		bw.close();
		
	}

}
