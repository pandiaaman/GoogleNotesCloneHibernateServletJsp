<%@page import="com.entities.Note"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.helper.FactoryProviderSingleton"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<link rel="stylesheet" href="stylecss/style.css"/>
    <title>edit a note</title>
</head>
<body>

	<%
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	%>
	<div class="container">
    	<%@include file="navbar.jsp" %>
    </div>

	<%
	
	if(session.getAttribute("user") == null){
		session.setAttribute("errormsg", "please login to edit/view your notes");
		response.sendRedirect("index.jsp");
	}
	
	//session timeout in seconds after inactivity on same session
	session.setMaxInactiveInterval(5*60); //5 mins of inactivity on this page will lead to session timeout user has to login again

	
		out.println("node edited : " + request.getParameter("noteid"));
		int noteid = Integer.parseInt(request.getParameter("noteid"));
	%>
	
	<%
	SessionFactory factory = FactoryProviderSingleton.getFactory();	
	Session sess = factory.openSession();
	
	Note note = (Note) sess.get(Note.class, noteid);
	
	out.println(note.isNotePinned());
	%>
	
	<div class="container">
		<h2 class="text-light">add your notes here!</h2>
		
		<form action="./saveEditedNoteServlet" method="post">
		
    	  <input type="hidden" id="noteid" name="noteid" value="<%=note.getId() %>">
	
		  <div class="form-group">
		    <label for="noteTitle">Title</label>
		    <input required type="text" class="form-control" name="noteTitle" id="noteTitle" value="<%out.println(note.getTitle()); %>">
		  </div>
		  		  <div class="form-group">
		    <label for="noteContent">Content</label>
		    <textarea class="form-control" name="noteContent" id="noteContent" 
		    style="height:10rem"
		    ><%=note.getContent() %></textarea>
		  </div>
		  <div class="form-check">
		    <input type="checkbox" class="form-check-input" name="pinNote" id="pinNote" value="pinned"
		    <%
		    	if(note.isNotePinned()){
		    %>
		    checked
		    <%
		    	}
		    %>
		    >
		    <label class="form-check-label text-light" for="pinNote">Pin Note</label>
		  </div>
		  <div class="text-center container">
		  	<button class="btn btn btn-light my-2 my-sm-0" type="submit">Confirm Edit</button>
		  </div>
		</form>
	</div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS --> 
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 
 
 
</body>
</html>