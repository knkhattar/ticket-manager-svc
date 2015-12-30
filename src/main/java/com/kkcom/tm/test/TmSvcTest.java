package com.kkcom.tm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kkcom.tm.login.svc.AuthService;

public class TmSvcTest {
	

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-context.xml");

		AuthService authService = (AuthService) context.getBean("authService");
		
		System.out.println("auth status ::"+authService.authenticate("test11", "pass1").isAuthenticated());
	}
}
