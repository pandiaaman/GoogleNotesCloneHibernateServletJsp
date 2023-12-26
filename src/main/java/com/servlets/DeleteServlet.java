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

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			
			
			int noteid = Integer.parseInt(request.getParameter("noteid").trim());
			PrintWriter out = response.getWriter();
			System.out.println("deleting note" + noteid);
			SessionFactory factory= FactoryProviderSingleton.getFactory();
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction(); 
			
			Note note = (Note) sess.get(Note.class, noteid);
			System.out.println(note.getTitle());
//			out.println(note.getTitle());
			sess.delete(note);
			
			tx.commit();
			sess.close();
			
			response.sendRedirect("show_all_notes.jsp");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}
