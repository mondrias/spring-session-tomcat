package com.spring.config;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class WebAppInitializer implements WebApplicationInitializer {
	

	

	
	
/*	 Enable this for tomcat */
public void onStartup(ServletContext servletContext) throws ServletException {
	  WebApplicationContext context = getContext(servletContext);
	  Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
	  servlet.addMapping("/");
	  servlet.setLoadOnStartup(1);
	 }

	 private WebApplicationContext getContext(ServletContext servletContext) {
	  AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
	  context.setServletContext(servletContext);
	  context.getEnvironment().setActiveProfiles("prod");
	  context.register(RootConfig.class,SecurityConfig.class,MvcConfig.class);
	  servletContext.addListener(new ContextLoaderListener(context));
	  
	  return context;
	 }
	
	

}