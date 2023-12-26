package com.hibernateDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.FactoryProviderSingleton;

public class FetchNoteHibernate {

	public List<Note> fetchNotesFromDB(){
		List<Note> notes = new ArrayList<Note>();
		
		SessionFactory factory = FactoryProviderSingleton.getFactory();
		Session sess = factory.openSession();
		
		String hql = "from notes_table";
		Query query = sess.createQuery(hql);
		notes = query.list(); 
		
		
		return notes;
		
		
	}
}
