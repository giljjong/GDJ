package com.gdu.app01.java04;

public class Gun {
	private String model;
	private int bullet;
	
	public Gun() {
		// TODO Auto-generated constructor stub
	}

	public Gun(String model, int bullet) {
		super();
		this.model = model;
		this.bullet = bullet;
	}
	
	public void info() {
		System.out.println("모델 명 : " + model);
		System.out.println("총알 수 : " + bullet);
	}
}
