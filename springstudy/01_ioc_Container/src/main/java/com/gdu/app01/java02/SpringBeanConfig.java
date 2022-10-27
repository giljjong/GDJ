package com.gdu.app01.java02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {
	
	@Bean
	public Student stud() {
		List<Integer> scores = new ArrayList<Integer>();
		for(int cnt = 0; cnt < 5; cnt++) {
			int random = (int)(Math.random() * 100) + 1;
			scores.add(random);
		}
		
		Set<String> awards = new HashSet<String>();
		awards.add("숨쉬기상");
		awards.add("그냥상");
		awards.add("부상");
		
		Map<String, String> contact = new HashMap<String, String>();
		contact.put("address", "강서로 47길");
		contact.put("tel", "010-14653-023");
		
		Student stud = new Student();
		stud.setScores(scores);
		stud.setAwards(awards);
		stud.setContact(contact);
		
		return stud;
	}
	
}
