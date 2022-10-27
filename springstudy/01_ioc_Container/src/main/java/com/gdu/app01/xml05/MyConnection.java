package com.gdu.app01.xml05;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Connection getConnection() throws Exception {
			Connection con = null;
			con = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
			System.out.println("con 생성완료");
			if(con != null) {
				con.close();
			}
		return con;
	}
	
}
