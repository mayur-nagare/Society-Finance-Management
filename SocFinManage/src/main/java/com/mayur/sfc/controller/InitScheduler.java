package com.mayur.sfc.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mayur.jdbc.db.configuration.Schedular;

@WebListener
public class InitScheduler implements ServletContextListener {

	   @Override
       public final void contextInitialized(final ServletContextEvent sce) {
		   Schedular schedular = new Schedular();
		   schedular.schduleFine();
		   schedular.schduleMail();
       }

       @Override
       public final void contextDestroyed(final ServletContextEvent sce) {
    	   System.out.println("Schedular End");
       }
	}
