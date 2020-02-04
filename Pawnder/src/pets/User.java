package pets;

import java.io.Serializable;

public class User implements Serializable {

	private String name = "blankName";
	private String email = "blankEmail";

	public User(String aName, String anEmail) {
		this.name = aName;
		this.email = anEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "User's name: " + this.name + "\nUser's email: " + this.email;
	}

	public void sendEmail() {
		System.out.println("A confirmation email has been sent to " + this.email);
	}

}
