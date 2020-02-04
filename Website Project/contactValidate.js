function contactValidate() {
	var fname = document.getElementbyId('fname');
	var lname = document.getElementbyId('lname');
	var email = document.getElementbyId('email');
	var location = document.getElementbyId('location');

	// validates that the first name is longer than one character
	if (fname.value.length <= 1) {
		alert("Sorry! First name must contain (1) or more characters.");
		firstName.focus();
		return false;
	} else {
		return true;
	}

	// validates that the last name is longer than two characters
	if (lname.value.length <= 1) {
		alert("Sorry! Last name must contain (1) or more characters.");
		lastName.focus();
		return false;
	} else {
		return true;
	}

	//validates that the first name has only alpha characters
	if (!/^[a-zA-Z]*$/g.test(fname)) {
		alert("Sorry, your first name must only contain letters (a-z)");
		firstName.focus();
		return false;
	} else {
		return true;
	}

	//validates that the last name has only alpha characters
	if (!/^[a-zA-Z]*$/g.test(lastName)) {
		alert("Sorry, your first name must only contain letters (a-z)");
		firstName.focus();
		return false;
	} else {
		return true;
	}

	// validates that the last name follows the email format (alphanumeric characters, an @ sign)
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(email.value.match(mailformat)) {
	return true;
	} else {
	alert("You have entered an invalid email address!");
	return false;
	}
}