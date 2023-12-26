package com.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.User;
import com.helper.FactoryProviderSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRegiesterVerificationService {

	private static Logger logger = LoggerFactory.getLogger(UserRegiesterVerificationService.class);
	
	public boolean verifyRegisteringUser(User user) {
		
		logger.info("verifying user");
		
		String uname = user.getUname();
		System.out.println("USER NAME IS : " + uname);
		//check if this username does not exist already in the db
		
		SessionFactory factory = FactoryProviderSingleton.getFactory();
		Session sess = factory.openSession();

		Query q = sess.createQuery("from User where uname='" + uname + "'");
		List<User> users = q.list();
	
		sess.close();
		
		for(User u : users) {
			System.out.println("fetched names while registering : " + u.getUname());
		}
		if(users.size() > 0) {
			System.out.println("already existing username");
			logger.error("user already exists with the same username");
			
			sess.close();
			
			return false;
		}
		
		System.out.println("correct username unique");
		logger.debug("unique username for the user");
		
		
		
		return true;
		
	}

	
}
