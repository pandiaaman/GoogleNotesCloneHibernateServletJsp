package com.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProviderSingleton {

	/*
	 * this is a singleton class that provides the factory object 
	 */
	
	public static SessionFactory factory;
	
	public static SessionFactory getFactory() {
		
		if(factory == null) {
			synchronized (FactoryProviderSingleton.class) { //using synchronized in case multiple threads try to access this for creation
				Configuration conf = new Configuration().configure("hibernate.cfg.xml");
				factory = conf.buildSessionFactory();
			}
			
		}
		
		return factory;
		
	}
	
	
	
	public static void closeFactory() {
		if(factory.isOpen()) {
			factory.close();
		}
	}
	
}
