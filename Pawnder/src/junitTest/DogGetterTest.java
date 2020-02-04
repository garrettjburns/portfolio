// Name: Garrett Burns
// File Name: CatGetterTest.java
// Description: JUnit test to determine if the getters for the dog object are functioning properly


package junitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pets.Dog;

public class DogGetterTest {

	@Test
	public void shouldCreateDog() {
		
		// Given
		Dog testDog = new Dog("testBreed", "testNeighborhood", 2, "testName", "testDogQuality");
		
		// When
		String result1 = testDog.getBreed();
		String result2 = testDog.getNeighborhood();
		int result3 = testDog.getAge();
		String result4 = testDog.getName();
		String result5 = testDog.getDogQuality();

		// Then
		assertEquals("testBreed", result1);
		assertEquals("testNeighborhood", result2);
		assertEquals(2, result3);
		assertEquals("testName", result4);
		assertEquals("testDogQuality", result5);
	}

}
