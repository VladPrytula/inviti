package com.inviti.rest.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class RestInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext restContext = new AnnotationConfigWebApplicationContext();
		restContext.register(WebConfig.class);

		servletContext.addListener(new ContextLoaderListener(restContext));
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"DispatcherServlet", new DispatcherServlet(restContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
	}

}
