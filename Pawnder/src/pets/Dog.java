// Name: Garrett Burns
// File Name: Dog.java
// Description: The Dog class is a subclass of the abstract class Pet. The Dog class contains a constructor
// that inherits some qualities from its superclass, as well as an additional DogQuality that is specific 
// to its existence within the Dog subclass. The class furthers the method isPossible() which is originally 
// introduced by the Pet class but defined by the Dog subclass.

package pets;

public class Dog extends Pet {
	private String dogQuality = ""; // example of quality for dog subclass

	public Dog(String aBreed, String aNeighborhood, int anAge, String aName, String aDogQuality) {
		super(aBreed, aNeighborhood, anAge, aName);
		dogQuality = aDogQuality;
	}

	public String getDogQuality() {
		return this.dogQuality;
	}

	public void setDogQuality(String aDogQuality) {
		this.dogQuality = aDogQuality;
	}

	public void isPossible() {
		System.out.println("This dog has a possible match");
	}
}
