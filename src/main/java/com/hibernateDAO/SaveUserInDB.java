package com.hibernateDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entities.User;
import com.helper.FactoryProviderSingleton;

public class SaveUserInDB {

	public boolean saveUserInDBOnRegistration(User user) {
		SessionFactory factory = FactoryProviderSingleton.getFactory();
		Session sess = factory.openSession();
		
		Transaction tx = sess.beginTransaction();
		
		sess.save(user);
		
		tx.commit();
	
		return true;
	}
}
