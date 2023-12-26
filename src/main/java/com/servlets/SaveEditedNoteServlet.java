package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.FactoryProviderSingleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveEditedNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(SaveEditedNoteServlet.class);
   
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("info : <<<entering the doPost method within SaveEditiedNoteServlet>>>");
		try {
			PrintWriter out = res.getWriter();
			
			int noteid = Integer.parseInt(req.getParameter("noteid"));
			String noteTitle = req.getParameter("noteTitle");
			String noteContent = req.getParameter("noteContent");
			boolean isNotePinned = false;
			
			if(req.getParameterValues("pinNote")== null) {
				isNotePinned=false;
			}else {
				String[] pinnedNote = req.getParameterValues("pinNote");
				isNotePinned = pinnedNote[0].equals("pinned")?true:false;
			}
			logger.trace("trace : <<< values for note : id : " + noteid + " note title : " + noteTitle + ">>>");
			logger.info("info : <<<opening the session factory and hibernate session>>>");
			
			SessionFactory factory = FactoryProviderSingleton.getFactory();
			Session sess = factory.openSession();
			
			Transaction tx = sess.beginTransaction();
			
			Note note = (Note) sess.get(Note.class, noteid);
			
			note.setTitle(noteTitle);
			note.setContent(noteContent);
			note.setNotePinned(isNotePinned);
			
			//the above note will automatically gets updated once we commit
			tx.commit();
			sess.close();
			
			logger.info("note saved and committed, redirecting to show_all_notes.jsp");
			res.sendRedirect("show_all_notes.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
