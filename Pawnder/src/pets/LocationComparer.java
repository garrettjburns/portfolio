// Name: Garrett Burns
// File name: LocationComparer
// The LocationComparer class is the home of the SameLocation() method, which is used to compare two pet locations
// and determine if they are the same. This is implemented to determine matches by the Pawnder application.
// 

package pets;

public class LocationComparer {

	public boolean SameLocation(String pet1Location, String pet2Location) {
		if (pet1Location.equals(pet2Location)) {
			return true;
		}
		else {
			return false;
		}
	}
}