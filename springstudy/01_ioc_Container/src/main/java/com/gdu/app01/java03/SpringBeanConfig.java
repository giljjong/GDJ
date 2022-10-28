package com.gdu.app01.java03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {
	
	@Bean
	public Publisher publisher1() {
		Publisher publisher = new Publisher();
		publisher.setName("한국 출판사");
		publisher.setTel("032-13-543");
		return publisher;
	}
	
	@Bean
	public Book book1() {
		Book book = new Book();
		book.setTitle("어린왕자");
		book.setAuthor("생텍쥐베리");
		book.setPublisher(publisher1());
		return book;
	}
	
}
