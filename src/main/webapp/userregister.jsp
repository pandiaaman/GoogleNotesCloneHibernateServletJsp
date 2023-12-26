<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<link rel="stylesheet" href="stylecss/style.css"/>
<title>register</title>

<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<link rel="stylesheet" href="stylecss/style.css"/>
</head>
<body>
	<div class="container">
	<%@include file="navbar.jsp" %>
	
	<%
		if(session.getAttribute("errormsg")!=null){
			out.println(session.getAttribute("errormsg"));
			session.setAttribute("errormsg", null);
		}
	
		if(session.getAttribute("user")!=null){
			session.setAttribute("errormsg", "logout to do a new registration");
			response.sendRedirect("index.jsp");
			
		}else{
	%>
	</div>
	
	
	<div class="container">	
		<form action="./userRegisterServlet" method="post">
		  <div class="form-group">
		    <label for="username">Create Username</label>
		    <input required type="text" class="form-control" id="username" name="username" placeholder="Enter username">
		  </div>
		  <div class="form-group">
		    <label for="userpassword">Create Password</label>
		    <input required type="password" class="form-control" id="userpassword" name="userpassword" placeholder="Enter Password">
		  </div>
		  <button type="submit" class="btn btn-primary">Register</button>
		</form>
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