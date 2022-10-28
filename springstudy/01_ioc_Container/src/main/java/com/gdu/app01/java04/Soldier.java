package com.gdu.app01.java04;

public class Soldier {
	
	private String name;
	private Gun gun;
	
	public Soldier() {
		// TODO Auto-generated constructor stub
	}
	
	public Soldier(String name, Gun gun) {
		super();
		this.name = name;
		this.gun = gun;
	}
	
	public void info() {
		System.out.println("이름 : " + name);
		gun.info();
	}
}
