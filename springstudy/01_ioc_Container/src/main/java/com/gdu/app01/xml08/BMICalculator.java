package com.gdu.app01.xml08;

public class BMICalculator {
	
	private Calculator calc;
	private double height;
	private double weight;
	private double bmi;
	private String health;
	
	public BMICalculator(Calculator calc, double height, double weight) {
		super();
		this.calc = calc;
		this.height = height;
		this.weight = weight;
		this.bmi = calc.div(weight, calc.div(calc.mul(height, height), 10000));
		
		String health = "";
		
		if(bmi > 0 && bmi < 20) {
			health = "저체중";
		} else if(bmi < 25) {
			health = "정상";
		} else if(bmi < 30) {
			health = "과체중";
		} else {
			health = "비만";
		}
		
		this.health = health;
	}
	
	public double getHeight() {
		return height;
	}

	public double getWeight() {
		return weight;
	}

	public void info() {
		System.out.println("bmi : " + bmi);
		System.out.println("healt : " + health);
	}
	
}
