// Name: Garrett Burns
// File Name: CatGetterTest.java
// Description: JUnit test to determine if the getters for the cat object are functioning properly


package junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pets.Cat;

public class CatGetterTest {

	@Test
	public void shouldCreateCat() {
		
		// Given
		Cat testCat = new Cat("testBreed", "testNeighborhood", 2, "testName", "testCatQuality");
		
		// When
		String result1 = testCat.getBreed();
		String result2 = testCat.getNeighborhood();
		int result3 = testCat.getAge();
		String result4 = testCat.getName();
		String result5 = testCat.getCatQuality();
		
		// Then
		assertEquals("testBreed", result1);
		assertEquals("testNeighborhood", result2);
		assertEquals(2, result3);
		assertEquals("testName", result4);
		assertEquals("testCatQuality", result5);
	}

}
