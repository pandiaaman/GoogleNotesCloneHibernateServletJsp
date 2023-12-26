package com.service;

import java.util.logging.*;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import com.entities.User;
import com.helper.FactoryProviderSingleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserLoginVerificationService {

	private static Logger logger = LoggerFactory.getLogger(UserLoginVerificationService.class);
	
	public boolean verifyUserLogin(User user) {
		String uname = user.getUname();
		String upass = user.getUpass();
		
		logger.info("values coming in are {} and {}",uname + " " + upass);
		logger.trace("We've just greeted the user!");
		logger.debug("We've just greeted the user!");
		logger.info("We've just greeted the user!");
		logger.warn("We've just greeted the user!");
		logger.error("We've just greeted the user!");
		
		
		//go to hibernate and check if the enteries are correct
		SessionFactory factory = FactoryProviderSingleton.getFactory();
		Session sess = factory.openSession();
		
		Query q = sess.createQuery("from User where uname='" + uname + "'");
		List<User> users = q.list();
	
		if(users.size() == 1) {
			
			String passwordInDB = users.get(0).getUpass();
			if(!checkPass(upass,passwordInDB)) {
				return false;
			}
			
			System.out.println("correct login");
			//fetching complete details of the user on login from db
			user.setUid(users.get(0).getUid());
			user.setUname(users.get(0).getUname());
			user.setNotes(users.get(0).getNotes());
			user.setUpass(users.get(0).getUpass());
			
			sess.close();
			
			return true;
		}
		

		
		sess.close();
		
		return false;
		
	}
	
	private boolean checkPass(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword))
			return true;
		else
			return false;
	}
	

}
