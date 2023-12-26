package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.FactoryProviderSingleton;

public class ArchiveNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			PrintWriter out = response.getWriter();
//			out.println("hello :" + request.getParameter("noteid"));
			int noteid = Integer.parseInt(request.getParameter("noteid"));
			int value = Integer.parseInt(request.getParameter("value"));
			
			SessionFactory factory = FactoryProviderSingleton.getFactory();
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction();
			
			Note note = sess.get(Note.class, noteid);
			
			if(value == 1) {
				note.setArchived(true);
			}else {
				note.setArchived(false);
			}
			
			tx.commit();
			sess.close();
			
			
			HttpSession session = request.getSession();
			
			if(value == 1)
				session.setAttribute("note_archived", noteid);
			
			
			response.sendRedirect("show_all_notes.jsp");	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
