// Name: Garrett Burns
// File name: UserTest.java
// Description: This file is a JUnit test to verify that the constructor for the User class is functioning properly.
// This is performed by testing the getters for the parameters "test" and "email."



package junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pets.User;

public class UserTest {

	@Test
	public void shouldCreateUser() {
		
		// Given
		User testuser = new User("testName", "testEmail");
		
		
		//When
		String result1 = testuser.getName();
		String result2 = testuser.getEmail();
		
		
		//then
		assertEquals("testName", result1);
		assertEquals("testEmail", result2);
	}
}
