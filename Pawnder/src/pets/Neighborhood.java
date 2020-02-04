// Name: Garrett Burns
// File: Neighborhood.java
// Description: ** Class not yet used by the Pawnder application and is not included in main(). 
// The neighborhood class is a subclass of Town, which inherits the name of the town that the neighborhood exists in.
// The Neighborhood class also contains its own subclass attribute, NeighborhoodName, that is used to hold
// the name of a specific neighborhood. This class exists so that a pet's location can be specified beyond a town for
// more more compatible matchmaking. 

package pets;

public class Neighborhood extends Town {
	private String neighborhoodName = "";
	
public Neighborhood(String townName, String aNeighborhoodName) {
	super(townName);
	this.neighborhoodName = aNeighborhoodName;
	}
	
public String getNeighborhoodName() {
	return this.neighborhoodName;
	}
	
	
public String toString() {
	return "The neighborhood is " + this.getNeighborhoodName() + " in " + this.getTownName();
	}
	
}
