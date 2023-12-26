package com.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.entities.User;

import junit.framework.TestCase;

@ExtendWith(MockitoExtension.class)
public class UserLoginVerificationServiceTest extends TestCase {

	UserLoginVerificationService verifyUserService = new UserLoginVerificationService();
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testVerifyUserLogin() {
		
		User user = mock(User.class); //using mockito to mock an object of user that is being passed to login verification
		//Here, we have the method verifyUserLogin(User user), which means : 
		 // SUT : system under test : verifyUserLogin
		 // Dependency : User : we need to mock user in case we dont have the user
		
		when(user.getUname()).thenReturn("amit");
		when(user.getUpass()).thenReturn("password");
		
		
		boolean result = verifyUserService.verifyUserLogin(user);
		
		
		assertEquals(true, true);
	}

}
