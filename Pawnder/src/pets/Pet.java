// Name: Garrett Burns
// File Name: pets.java
// This file holds the constructor for the abstract pet class. The pet class holds the basic attributes for pets, which are
// inherited by the subclasses Cat and Dog. To keep this attributes private, the pet class has public getter and setter methods
// that allow the attributes to be accessed/manipulated from outside the class. The Pet class contains a toString() method that overrides the default tostring()
// to present these attributes in a more user-friendly way. The Pet class also has an abstract method named isPossible() that
// is inherited and personalized by the individual Cat and Dog subclasses.



package pets;

public abstract class Pet {
	
	private String breed = "";
	private String neighborhood = "";
	private int age = 0;
	private String name = "";
	
	public Pet(String aBreed, String aNeighborhood, int anAge, String aName) {
		this.breed = aBreed;
		this.neighborhood = aNeighborhood;
		this.age = anAge;
		this.name = aName;
	}
	
	// getters for pet attributes
	public String getBreed() {
		return this.breed;
	}
	
	public String getNeighborhood() {
		return this.neighborhood;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	// setters for pet attributes
	public void setBreed(String aBreed) {
		this.breed = aBreed;
	}
	
	public void setNeighborhood(String aNeighborhood) {
		this.neighborhood = aNeighborhood;
	}
	
	public void setAge(int anAge) {
		this.age = anAge;
	}
	
	public void setName(String aName) {
		this.name = aName;
	}
	
	
	
	public String toString() {
		return "Pet's breed: " + this.getBreed() + "\nPet's neighborhood: " + this.getNeighborhood() + "\nPet's age: " + this.getAge() + "\nPet's name: " + this.getName();
	}
	
	public static void inLibrary() {
		System.out.println("This pet is available in the library");
	}
	
	
	public abstract void isPossible();
}
