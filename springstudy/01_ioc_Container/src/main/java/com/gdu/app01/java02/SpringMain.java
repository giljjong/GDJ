package com.gdu.app01.java02;

import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
		Student stud = ctx.getBean("stud", Student.class);
		
		for(Integer score : stud.getScores()) {
			System.out.println(score);
		}
		
		for(String award : stud.getAwards()) {
			System.out.println(award);
		}
		
		for(Map.Entry<String, String> entry : stud.getContact().entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		System.out.println(stud.getScores());
		System.out.println(stud.getAwards());
		System.out.println(stud.getContact());
	}

}
