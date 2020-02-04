// Name: Garrett Burns
// File Name: OutputPet.java
// Description: The OutputPet class is designed to contain methods that allow the Pawnder application to create an output
// text file containing the credentials of the user's pet. The OutputPet class accomplishes this with three methods:
// 1) openFile(), which creates a new file for the text to go, 2) addRecords(), which takes in the credentials as 
// parameters, and 3) closeFile(), which closes out of the text file. 


package pets;

import java.io.*;
import java.lang.*;
import java.util.*;

public class OutputPet {

	private Formatter x;
	
	public void openFile() {
		try {
			x = new Formatter("PetOutputFile.txt");
		}
	catch(Exception e) { 
		System.out.println("You have an error accessing output pet file");
	}
}

public void addRecords(String inputBreed, String inputLocation, int inputAge, String inputName) {
	x.format("%s %s %s %s", inputBreed, inputLocation, inputAge, inputName);
}

public void closeFile() {
	x.close();
}

}