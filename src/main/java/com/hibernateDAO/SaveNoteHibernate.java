package com.hibernateDAO;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entities.Note;
import com.entities.User;
import com.helper.FactoryProviderSingleton;

public class SaveNoteHibernate {

	public boolean saveNoteInDB(Note note) {
		SessionFactory factory = FactoryProviderSingleton.getFactory();
		
		Session sess = factory.openSession();
		Transaction tx = sess.beginTransaction();
		
		
		sess.save(note.getUser());
		sess.save(note);
		
		tx.commit();
		
		sess.close();
		
		return true;
		
	}
}
