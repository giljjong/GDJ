package com.gdu.app01.xml03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml03/appCtx.xml");
		
		Person person = ctx.getBean("person", Person.class);
		System.out.println(person.getName());
		System.out.println(person.getAge());
		Address addr = person.getAddr();
		System.out.println(addr.getJibun());
		System.out.println(addr.getRoad());
		System.out.println(addr.getZipCode());
		ctx.close();
	}

}
