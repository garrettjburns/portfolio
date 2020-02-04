// Name: Garrett Burns
// File Name: Cat.java
// Description: The Cat class is a subclass of the abstract class Pet. The Cat class contains a constructor
// that inherits some qualities from its superclass, as well as an additional CatQuality that is specific 
// to its existence within the Cat subclass. The class furthers the method isPossible() which is originally 
// introduced by the Pet class but defined by the Cat subclass.


package pets;


public class Cat extends Pet{
	private String catQuality = "";
	
	public Cat(String aBreed, String aNeighborhood, int anAge, String aName, String aCatQuality) { 
		super(aBreed, aNeighborhood, anAge, aName);
		catQuality = aCatQuality;
	}
	
	public String getCatQuality() {
		return this.catQuality;
	}
	
	public void setCatQuality(String aCatQuality) {
		this.catQuality = aCatQuality;
	}

	public void isPossible() {
		System.out.println("This cat has a possible match");
	}
	
}
