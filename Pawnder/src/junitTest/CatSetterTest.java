// Name: Garrett Burns
// File Name: CatGetterTest.java
// Description: JUnit test to determine if the setters for the cat object are functioning properly


package junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pets.Cat;

public class CatSetterTest {

	@Test
	public void test() {
		// Given
		Cat testCat = new Cat("testBreed", "testNeighborhood", 2, "testName", "testCatQuality");
		
		// When
		testCat.setBreed("changedBreed");
		String result1 = testCat.getBreed();
		testCat.setNeighborhood("changedNeighborhood");
		String result2 = testCat.getNeighborhood();
		testCat.setAge(3);
		int result3 = testCat.getAge();
		testCat.setName("changedName");
		String result4 = testCat.getName(); 
		testCat.setCatQuality("changedCatQuality");
		String result5 = testCat.getCatQuality();
		
		// Then
		assertEquals("changedBreed", result1);
		assertEquals("changedNeighborhood", result2);
		assertEquals(3, result3);
		assertEquals("changedName", result4);
		assertEquals("changedCatQuality", result5);
	}

}
