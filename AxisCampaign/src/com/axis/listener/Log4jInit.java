package com.axis.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Application Lifecycle Listener implementation class Log4jInit
 * 
 */
@WebListener
public class Log4jInit implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public Log4jInit() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

		Logger logger = null;
		ServletContext servletContext = arg0.getServletContext();
		String log4jFile = servletContext.getInitParameter("log4jFileName");
		log4jFile = servletContext.getRealPath(log4jFile);
		DOMConfigurator.configure(log4jFile);
		logger = LogManager.getLogger(Log4jInit.class.getName());
		logger.debug("Loaded: " + log4jFile);
	}

}
