package com.gdu.contact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.contact.domain.ContactDTO;
import com.gdu.contact.repository.ContactDAO;
import com.gdu.contact.service.ContactService;
import com.gdu.contact.service.ContactServiceImpl;

@Configuration
public class SpringBeanConfig {
	@Bean
	public ContactDAO contactDao() {
		return new ContactDAO();
	}
	
	@Bean
	public ContactDTO contactDTO() {
		return new ContactDTO();
	}
	
	@Bean
	public ContactService contactService() {
		return new ContactServiceImpl();
	}
}
