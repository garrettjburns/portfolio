// Name: Garrett Burns
// File name: LocationComparerTest.java
// Description: This is a Junit test to see if the sameLocation() method in the LocationComparer class works as intended.
// in the example below, two matching locations (location1 and location3) are fed into the function test and return a passing result.
// when the parameters of the testing function are changed to location 1 and location 2, the test does not pass.


package junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pets.LocationComparer;

public class LocationComparerTest {

	@Test
	public void shouldCompareLocations() {
		
		// Given
		LocationComparer testCompare = new LocationComparer();
		String location1 = "Oak Square";
		String location2 = "Davis Square";
		String location3 = "Oak Square";
		
		// When
		boolean result = testCompare.SameLocation(location1,location3);
		
		// Then
		assertEquals(true, result);
		
	}

}
