package com.gdu.app01.xml05;

import java.sql.Connection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml05/appCtx.xml");

		MyConnection myCon = ctx.getBean("connection", MyConnection.class);
		try {
			Connection con = myCon.getConnection();
			if(con != null) {
				con.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		ctx.close();
	}

}
