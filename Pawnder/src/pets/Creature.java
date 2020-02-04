// Name: Garrett Burns
// File name: Creature.java
// Description: Generic class created to manipulate input-based instances of User and Pet classes


package pets;

public class Creature<T> {
	
	// instance variable
	private T t;
	
	// parameterized constructor
	public Creature(T t) {
		this.t = t;
	}
	
	// returns casted value by the method's signature
	public T get() {
		return t;
	}
}
