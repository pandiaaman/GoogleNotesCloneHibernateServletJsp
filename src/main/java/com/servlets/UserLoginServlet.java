package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.User;
import com.service.UserLoginVerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private static Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		logger.info("entering into UserLoginServlet");
		
		String uname = req.getParameter("username");
		String upass = req.getParameter("userpassword");
		
		logger.info("values associated to the user : " + uname + " and " + upass);
		
		User newuser = new User();
		newuser.setUname(uname);
		newuser.setUpass(upass);
		
		UserLoginVerificationService verifyLogin = new UserLoginVerificationService();
		if(verifyLogin.verifyUserLogin(newuser)) {
			session.setAttribute("uname", newuser.getUname());
			session.setAttribute("userid", newuser.getUid());
			session.setAttribute("user", newuser);
			
			System.out.println("setting userid in session : " + session.getAttribute("userid"));
			System.out.println("attribute uname : " + session.getAttribute(uname));
			res.sendRedirect("index.jsp");

		}
		else {
			session.setAttribute("errormsg", "please reenter your details");
			System.out.println("not setting");
			System.out.println("attribute uname : " + session.getAttribute(uname));
			res.sendRedirect("userlogin.jsp");

		}
		
				
	}

}
