package com.gdu.app01.xml02;

public class Engine {

	private String fuel;		// 연료 종류
	private double efficency;	// 연비
	private int cc;				// 배기량
	
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public double getEfficency() {
		return efficency;
	}
	public void setEfficency(double efficency) {
		this.efficency = efficency;
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
	
}
