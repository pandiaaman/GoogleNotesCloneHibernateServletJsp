<%@page import="com.entities.Note"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="com.helper.FactoryProviderSingleton"%>
<%@page import="org.hibernate.Session"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<link rel="stylesheet" href="stylecss/style.css"/>
<title>all notes</title>
</head>
<body>

		<%
			response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		%>

	<div class="container">
		<%@include file="navbar.jsp" %>
		
		<br>
		
		<%
		
			if(session.getAttribute("user")==null){
				session.setAttribute("errormsg","please login to view your notes");
				response.sendRedirect("index.jsp");
			}
			else{
				
				//session timeout in seconds after inactivity on same session
				session.setMaxInactiveInterval(5*60); //5 mins of inactivity on this page will lead to session timeout user has to login again
		
				if(session.getAttribute("note_archived") != null){
					int archivednoteid = Integer.parseInt(session.getAttribute("note_archived").toString());
					//out.println(archivednoteid);
					%>
					<div class="alert alert-primary" role="alert">
  						<a class="pr-3" href="ArchiveNoteServlet?noteid=<%=archivednoteid%>&value=0">UNDO archived note</a>
					</div>
					
					<%
					session.setAttribute("note_archived", null);
				}
		
				SessionFactory factory = FactoryProviderSingleton.getFactory();	
				Session sess = factory.openSession();
				
				Query q = sess.createQuery("from Note where user_id='" + session.getAttribute("userid") + "'");
				
				//implementing pagination
	//			q.setFirstResult(0);
	//			q.setMaxResults(5);
				
				List<Note> notes = q.list();
			
				for(Note note : notes){
					if(note.isNotePinned() == true && note.isArchived() == false){
					%>
					
					<div class="card mt-3">
					  <div class="card-body">
					  <a href="edit_note.jsp?noteid=<%=note.getId()%>">
					  <span style="position:absolute; width:100%;height:100%;top:0;left: 0;z-index: 1;">
					  	<div class="container text-right">
					  		<a style="position : relative; z-index: 2;" class="pr-3" href="ArchiveNoteServlet?noteid=<%=note.getId()%>&value=1"><img src="archive.png" title="archive note" alt="archive icon" width="20" height="20" style="position : relative; z-index: 2;"/></a>
					  	
					  		<img src="pin.png" title="pinned note" alt="pin icon" width="10" height="10"/>
					  		</div>
					    <h5 class="card-title"><%=note.getTitle() %></h5>
					    <p class="card-text"><%=note.getContent() %></p>
					    
					    <div class="container text-right">
					   		<p class="card-text"><%=note.getAddedDate() %></p>
						    <a href="edit_note.jsp?noteid=<%=note.getId()%>" class="btn btn-light">Edit Note</a>
						    <a style="position : relative; z-index: 2;" href="DeleteServlet?noteid=<%=note.getId()%>" class="btn btn-dark">Delete Note</a>
					    </div>
					    </span>
					    </a>
					  </div>
					</div> 
					<%
					}
				}
				
				out.println("<br>");
				
				for(Note note : notes){
					if(note.isNotePinned() != true && note.isArchived() == false){
					%> 
					<div class="card mt-3">
					  <div class="card-body">
					  <a href="edit_note.jsp?noteid=<%=note.getId()%>">
					  <span style="position:absolute; width:100%;height:100%;top:0;left: 0;z-index: 1;">
					  <div class="container text-right">
					  		<a class="pr-3" href="ArchiveNoteServlet?noteid=<%=note.getId()%>&value=1"><img src="archive.png" title="archive note" alt="archive icon" width="20" height="20" style="position : relative; z-index: 2;"/></a>
					  		</div>
					    <h5 class="card-title"><%=note.getTitle() %></h5>
					    <p class="card-text"><%=note.getContent() %></p>
					    
					    <div class="container text-right">
					   		<p class="card-text"><%=note.getAddedDate() %></p>
						    <a href="edit_note.jsp?noteid=<%=note.getId()%>" class="btn btn-light">Edit Note</a>
						    <a style="position : relative; z-index: 2;" href="DeleteServlet?noteid=<%=note.getId()%>" class="btn btn-dark">Delete Note</a>
					    </div>
					    </span>
					    </a>
					  </div>
					</div> 
					<%
					}
				}
			%>
			
				<button style="position:fixed;bottom:2em;right:2em" class="btn btn-dark my-2 my-sm-0" href="add_notes.jsp" type="submit"><a class="nav-link" href="add_notes.jsp">add Note</a></button>
			
		</div>
		
		<div class="mb-5">
		
		</div>
			<%
				sess.close();
			
			
			}
			%>
	
<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 
</body>
</html>