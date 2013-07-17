package com.bets.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.HttpConstraint;

public class ContextListener  implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		EntityUtil.getEntityManagerFactory();
		System.out.println("context initialized");
		System.out.println("entity manager factory initialized");
		
	}

}
