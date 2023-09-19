package com.sundar.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;	

public class Test {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		// Successfully Loaded.
		System.out.println("Loaded...");
		
		context.close();
	}

}
