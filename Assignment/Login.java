package registration;

import java.util.regex.Pattern;

public class Login {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;

    public Login(String firstName, String lastName, String username,
                 String password, String cellPhoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public boolean checkUserName() {

        if (username == null) {
            return false;
        }

        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPasswordComplexity() {

        if (password == null) {
            return false;
        }

        String pattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}$";

        return Pattern.matches(pattern, password);
    }

    public boolean checkCellPhoneNumber() {

        if (cellPhoneNumber == null) {
            return false;
        }

        String pattern = "^\\+27[0-9]{9}$";

        return Pattern.matches(pattern, cellPhoneNumber);
    }

    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
                  
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
                    
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code; please correct the number and try again.";
                    
        }

        return "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.\n"
                + "You have successfully registered!";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {

        if (enteredUsername == null || enteredPassword == null) {
            return false;
        }

        if (enteredUsername.equals(username)
                && enteredPassword.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public String returnLoginStatus(String enteredUsername,
                                    String enteredPassword) {

        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + firstName + "," + lastName
                    + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}