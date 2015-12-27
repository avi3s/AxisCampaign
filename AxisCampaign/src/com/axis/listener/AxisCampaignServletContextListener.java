package com.axis.listener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.axis.scheduler.ScheduledTask;

public class AxisCampaignServletContextListener  implements ServletContextListener{
	
/*      date.setTime();*/
    @Autowired
    private SessionFactory sessionFactory;
    
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
		sessionFactory.close();
	}

    //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
	    WebApplicationContextUtils
        .getRequiredWebApplicationContext(arg0.getServletContext())
        .getAutowireCapableBeanFactory()
        .autowireBean(this);
		
		// today    
		Calendar date = new GregorianCalendar();
		// reset hour, minutes, seconds and millis
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		date.add(Calendar.DAY_OF_MONTH, 1);//first time is next day
		System.out.println("ServletContextListener started");	
		Timer time = new Timer(); // Instantiate Timer Object
		ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
/*		System.out.println(date.getTime() + "next day 12 am after server start");*/
/*		time.schedule(st, date.getTime(), 1000);*/
		//time.schedule(st, 0, 60000);
		time.scheduleAtFixedRate(st, date.getTime(), 24*60*60*1000);//run thread

	}

}

