// Name: Garrett Burns
// File Name: ReadLibrary.java
// Description: The ReadLibary class contains three methods that are required to access a library of pet's that
// the Pawnder simulation uses for matchmaking. The three methods that ReadLibrary() uses to access this library, stored on a text file 
// are: 1) openFile(), which opens the text file where the library data is stored. 
// 2) readFile(), which parses through the text file and outputs its data into the application, and
// 3), closeFile(), which closes out of the text file. 

package pets;

import java.io.File;
import java.util.Scanner;

public class ReadLibrary {

	private Scanner x;

	public void openFile(String thefile) {
		try {
			x = new Scanner(new File(thefile));
		} catch (Exception e) {
			System.out.println("Cannot find file.");
		}
	}

	public void readFile() {
		while (x.hasNext()) {
			String a = x.next();
			String b = x.next();
			String c = x.next();
			String d = x.next();

			System.out.printf("Breed: %s Location: %s Age: %s Name: %s\n", a, b, c, d);
		}
	}

	public void closeFile() {
		x.close();
	}
}
