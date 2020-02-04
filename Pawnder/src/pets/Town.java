// Name: Garrett Burns
// File Name: Town.java
// Description: **This class is not yet used by Pawnder, but will be introduced in a later iteration**. The town class
// has one attribute that is used to designate the name of a town. A getter method getTownName() exists so that the main()
// method can access the townName attribute while retaining its privacy. There is also a toString() method to override
// the default toString() and provide a more user-friendly way to view the town's attribute.


package pets;

public class Town {
	private String townName = "";


public Town(String aTownName) {
	this.townName = aTownName;
	}

public String toString() {
	return "The name of the town is " + this.townName;
	}	

public String getTownName() {
	return this.townName;
	}
}
