package com.gdu.app01.xml07;

public class User {
	
	private String id;
	private Contact contact;
	
	public User(String id, Contact contact) {
		super();
		this.id = id;
		this.contact = contact;
	}

	public void info() {
		System.out.println("아이디 : " +  id);
		contact.info();
	}
}
