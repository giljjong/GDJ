package com.gdu.app05.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app05.sevice.BoardService;
import com.gdu.app05.sevice.BoardServiceImpl;
import com.gdu.app05.sevice.ContactService;
import com.gdu.app05.sevice.ContactServiceImpl;
import com.gdu.app05.sevice.GalleryService;
import com.gdu.app05.sevice.GalleryServiceImpl;
import com.gdu.app05.sevice.MovieService;
import com.gdu.app05.sevice.MovieServiceImpl;

@Configuration
public class SpringBeanConfig {

	@Bean
	public BoardService boardService() {
		return new BoardServiceImpl();
	}
	
	@Bean
	public MovieService movieService() {
		return new MovieServiceImpl();
	}
	
	@Bean
	public ContactService contactService() {
		return new ContactServiceImpl();
	}
	
	@Bean
	public GalleryService galleryService() {
		return new GalleryServiceImpl();
	}
	
}
