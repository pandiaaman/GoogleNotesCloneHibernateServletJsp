<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<link rel="stylesheet" href="stylecss/style.css"/>
    <title>Hello, world!</title>
  </head>
  <body>
  
  <%
  		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  
  %>
  
    <div class="container">
    	<%@include file="navbar.jsp" %>
    	
    </div>

	<%
		if(session.getAttribute("uname") == null){
	
	%>
		<div class="container">
	    	<h2>landing page!</h2>
	    </div>
	    
	<%
	
			if(session.getAttribute("errormsg")!=null){
				out.println(session.getAttribute("errormsg"));
				session.setAttribute("errormsg",null);
			}
		}
		else{
			//session timeout in seconds after inactivity on same session
			session.setMaxInactiveInterval(5*60); //5 mins of inactivity on this page will lead to session timeout user has to login again
	
	%>   
		<div class="container">
		<%
			out.println("Hello " + session.getAttribute("uname"));
		 %>
			 <div class="container text-center mt-5">
				<button class="btn btn-dark my-2 my-sm-0" href="add_notes.jsp" type="submit"><a class="nav-link" href="show_all_notes.jsp">see your Notes</a></button>
			</div>
		 	<div class="container text-center mt-5">
				<button class="btn btn-dark my-2 my-sm-0" href="add_notes.jsp" type="submit"><a class="nav-link" href="add_notes.jsp">add Note</a></button>
			</div>
		</div>
	<%
		}
	%> 
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>