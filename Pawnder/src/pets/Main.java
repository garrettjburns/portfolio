// name: Garrett Burns
// file name: main.java
// Description: Running this file simulates the Pawnder program. The main method is where the user will be prompted to input the details for their pet, 
// and receive a potential match.The main method instantiates cat and dog objects by pulling from classes and methods defined outside of main()

package pets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		// prompt user to input basic pet details
		System.out.println("Please designate if your pet is a cat or dog.");
		Scanner sc = new Scanner(System.in);
		String inputType = sc.nextLine();
		System.out.println("Please enter the breed of your pet.");
		String inputBreed = sc.nextLine();
		System.out.println("Please enter your neighborhood.");
		String inputNeighborhood = sc.nextLine();
		System.out.println("Please enter your pet's age, in years, as an integer.");
		int inputAge = 0;
		if (sc.hasNextInt()) {
			inputAge = sc.nextInt();
		} else {
			System.out.println(
					"Please make sure your pet's age is input as an integer and not a spelled word. For example, type \n"
							+ " '7' and press enter for a pet that is seven years old.");
			sc.nextLine();
			inputAge = sc.nextInt();
		}
		sc.nextLine();
		System.out.println("Please enter the name of your pet.");
		String inputName = sc.nextLine();
		System.out.println("Please enter your name");
		String inputUserName = sc.nextLine();
		System.out.println("Please enter your email");
		String inputEmail = sc.nextLine();

		// record user information by creating User instance
		User inputUser = new User(inputUserName, inputEmail);
		Creature<User> inputUser1 = new Creature<User>(inputUser); // create generic class Creature for input user
		
		
		
		// Code implementing OBJECT WRITE using instance of User class titled theOutputUser
		User theOutputUser = new User(inputUserName, inputEmail);
		
		String fileName = "data.bin";
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
			os.writeObject(theOutputUser);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		
		//implementing OBJECT READ using User class titled theOutputUser
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
			User theInputUser = (User) is.readObject();
			System.out.println("User name: " + theInputUser.getName() + "\nUser email: " + theInputUser.getEmail());
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// upon identifying if input pet is cat, ask for a cat-specific detail and
		// create cat
		if (inputType.equals("cat") == true) {
			System.out.println("Input a special quality of your cat");
			String inputCatQuality = sc.nextLine();
			Pet inputPet = new Cat(inputBreed, inputNeighborhood, inputAge, inputName, inputCatQuality);
			inputPet.isPossible(); // demonstrates polymorphism of cat subclass
			System.out.println("\n");
			Creature<Pet> inputPet1 = new Creature<Pet>(inputPet); // create generic class Creature for input pet
			Map<Creature, Creature> UserPetMap = new HashMap<>(); // creates hashmap for creatures
			UserPetMap.put(inputUser1, inputPet1); // logs the input user and cat together in hashmap through Creature
													// class
			System.out.println(inputUser1.get() + "\n"); // prints user details
			System.out.println("Your cat's details: \n" + UserPetMap.get(inputUser1).get() + "\n"); // prints
																									// corresponding pet
																									// details
		}

		// upon identifying if input pet is dog, ask for a dog-specific detail and
		// create dog
		if (inputType.equals("dog") == true) {
			System.out.println("Input a special quality of your dog");
			String inputDogQuality = sc.nextLine();
			Pet inputPet = new Dog(inputBreed, inputNeighborhood, inputAge, inputName, inputDogQuality);
			inputPet.isPossible(); // demonstrates polymorphism of dog subclass
			System.out.println("\n");
			Creature<Pet> inputPet1 = new Creature<Pet>(inputPet); // create generic class Creature for input pet
			Map<Creature, Creature> UserPetMap = new HashMap<>(); // creates hashmap for creatures
			UserPetMap.put(inputUser1, inputPet1); // logs input user and dog together in hashmap through Creature class
			System.out.println(inputUser1.get() + "\n"); // prints user details
			System.out.println("Your dog's details: \n" + UserPetMap.get(inputUser1).get() + "\n"); // prints
																									// corresponding pet
																									// details
		}

		// create a hard-coded library of pets as potential matches for simulation
		Pet pet1 = new Cat("Persian", "Davis Square", 3, "Maggie", "Likes yarn"); // test to create cat object
		Pet pet2 = new Cat("Ragdoll", "Ball Square", 5, "September", "Likes catnip"); // test to create cat object
		Pet pet3 = new Dog("Dalmation", "Oak Square", 4, "Spot", "Likes walks"); // test to create dog object
		Pet pet4 = new Dog("German Shepard", "Union Square", 5, "Rosco", "Likes belly rubs"); // test to create dog
																								// object

		// put all pets from library in a list
		Pet[] pets = new Pet[4];
		pets[0] = pet1;
		pets[1] = pet2;
		pets[2] = pet3;
		pets[3] = pet4;


		// Stream from list implemented to notify user if compatible breed exists in
		// system
		System.out.println("If a compatible breed exists in the system, it will be listed below: ");
		List<String> LibraryBreeds = Arrays.asList(pet1.getBreed(), pet2.getBreed(), pet3.getBreed(), pet4.getBreed());
		LibraryBreeds
			.stream()
			.filter(x -> x.equals(inputBreed))
			.forEach(System.out::println);
		System.out.println("\n");

		
		//initialize location comparer
		LocationComparer isMatch = new LocationComparer();
		
		
		// display all the pet details and state that they are available in the library
		System.out.println("The following pets are in the library:");
		for (Pet obj : pets) {
			System.out.println(obj);
			((Pet)obj).inLibrary();  // example of downcasting of cat and dog to pet
			if (isMatch.SameLocation(obj.getNeighborhood(), inputNeighborhood) == true){  	          // display if input pet is in the
				System.out.println("This pet is in the same neighborhood! Would you like to match? Y/N"); // the same neighborhood as a pet in the library
				String matchResponse = sc.nextLine();
				if (matchResponse.equals("Y")) {
					inputUser.sendEmail();
				}
			}
			System.out.println("\n");
		}
		
		
		// Example of lambda functionality, which creates a lambda instance that
		// tests to see if two breeds of pets in the library are the same.
		Comparator<String> stringComparatorLambda = (String o1, String o2) -> o1.compareTo(o2);
		boolean sameBreed = false;
		int breedComparison = stringComparatorLambda.compare(pet1.getBreed(), pet2.getBreed());
		if (breedComparison == 0) {
			sameBreed = true;
		} else {
			sameBreed = false;
		}
		
		
		
		
		
		
//		
//		// creates an output file with the basic pet details
//		OutputPet o = new OutputPet();
//		o.openFile();
//		o.addRecords(inputBreed, inputNeighborhood, inputAge, inputName);
//		o.closeFile();

		// demonstrates that pet library can be held and read from an imported txt file
//		System.out.println("\n \n The following demonstrates the output of ReadLibrary \n");
//		ReadLibrary r = new ReadLibrary();
//		r.openFile("petLibrary2.txt");
//		r.readFile();
//		r.closeFile();



//		// TEST CODE for generic class, Creature
//		User theuser1 = new User("Garrett", "garrettjburns@gmail.com");
//		Pet thepet1 = new Cat("Siamese", "Davis Square", 3, "Ronald", "Likes catnip");
//		Creature<User> test1 = new Creature<User>(theuser1);
//		Creature<Pet> test2 = new Creature<Pet>(thepet1);
//		
//		// TEST CODE: places username as key and connects it to pet in hash map
//		Map<Creature, Creature> testMap = new HashMap<>();
//		testMap.put(test1, test2);
//		System.out.println(test1.get()+ "\n");
//		System.out.println("Retrieved from map: \n" + testMap.get(test1).get());

//		 TEST CODE for town and neighborhood classes
//		Town town1 = new Town("Brighton");
//		System.out.println(town1.getTownName());
//		Neighborhood neighborhood1 = new Neighborhood("Brighton", "Oak Square");
//		System.out.println(neighborhood1.getTownName());
//		Town tempTown = neighborhood1;
//		tempTown.getTownName();

		// TEST CODE for array list
//		ArrayList<Pet> thePets = new ArrayList<Pet>();  // create an array list for all pet objects
	}
}