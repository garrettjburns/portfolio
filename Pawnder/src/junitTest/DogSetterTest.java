// Name: Garrett Burns
// File Name: CatGetterTest.java
// Description: JUnit test to determine if the setters for the dog object are functioning properly

package junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pets.Dog;

public class DogSetterTest {

	@Test
	public void test() {
		// Given
		Dog testDog = new Dog("testBreed", "testNeighborhood", 2, "testName", "testDogQuality");
		
		// When
		testDog.setBreed("changedBreed");
		String result1 = testDog.getBreed();
		testDog.setNeighborhood("changedNeighborhood");
		String result2 = testDog.getNeighborhood();
		testDog.setAge(3);
		int result3 = testDog.getAge();
		testDog.setName("changedName");
		String result4 = testDog.getName(); 
		testDog.setDogQuality("changedDogQuality");
		String result5 = testDog.getDogQuality();
		
		// Then
		assertEquals("changedBreed", result1);
		assertEquals("changedNeighborhood", result2);
		assertEquals(3, result3);
		assertEquals("changedName", result4);
		assertEquals("changedDogQuality", result5);
	}

}
