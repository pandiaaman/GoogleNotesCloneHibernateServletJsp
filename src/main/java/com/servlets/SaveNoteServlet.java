package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entities.Note;
import com.entities.User;
import com.helper.FactoryProviderSingleton;
import com.hibernateDAO.SaveNoteHibernate;


public class SaveNoteServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		HttpSession session = req.getSession();
		
		PrintWriter out = res.getWriter();		
		out.println("here");
		
		if(session.getAttribute("user") == null) {
			session.setAttribute("errormsg", "please login to add your note");
			res.sendRedirect("index.jsp");
		}
		
		try {
			
			String noteTitle = req.getParameter("noteTitle");
			String noteContent = req.getParameter("noteContent");
			
			boolean isNotePinned = false;
			
			if(req.getParameterValues("pinNote")== null) {
				isNotePinned=false;
			}else {
				String[] pinnedNote = req.getParameterValues("pinNote");
				isNotePinned = pinnedNote[0].equals("pinned")?true:false;
			}

			out.println(isNotePinned);
			
			
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			out.println(userid);
			
			
			SessionFactory factory = FactoryProviderSingleton.getFactory();
			
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction();
			
			/*
			 * this is important : here since both the user and notes are dependednt on each other
			 * we fetch the current user from db using userid we stored in session when the user logged in
			 * we fetch the notes of this user and add the current note to the list
			 * then we also add the user in the notes constructor
			 * we save both
			 */
			User currUser = (User) sess.get(User.class, userid);
			Note newNote = new Note(noteTitle,noteContent,new Date(),isNotePinned, currUser, false);
			currUser.getNotes().add(newNote);
			sess.save(currUser);
			sess.save(newNote);
			
			tx.commit();
			
			sess.close();
			

			
			res.sendRedirect("show_all_notes.jsp");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
