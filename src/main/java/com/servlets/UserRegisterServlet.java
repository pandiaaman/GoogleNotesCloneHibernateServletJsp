package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.entities.User;
import com.hibernateDAO.SaveUserInDB;
import com.service.UserRegiesterVerificationService;

public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		
		PrintWriter out = res.getWriter();
		out.println("asdf");
		
		User newuser = new User();
		String uname = req.getParameter("username");
		String upass = req.getParameter("userpassword");
		//using BCYPT encryption for hashing password : https://www.devglan.com/spring-mvc/storing-hashed-password-database-java
		upass = hashPassword(upass);
		
		newuser.setUname(uname);
		newuser.setUpass(upass);
		
		System.out.println("USERRRRNAME IS : " + uname);
		
		
		UserRegiesterVerificationService registerUserCheck = new UserRegiesterVerificationService();
		
		//checking if username already exists
		if(registerUserCheck.verifyRegisteringUser(newuser)) {
			SaveUserInDB saveRegisteredUser = new SaveUserInDB();
			saveRegisteredUser.saveUserInDBOnRegistration(newuser);
			res.sendRedirect("userlogin.jsp");
		}else {
			System.out.println("username exists already");
			sess.setAttribute("errormsg", "username exists already");
			res.sendRedirect("userregister.jsp");
		}
		
		
		
	}
	
	private String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}


}
