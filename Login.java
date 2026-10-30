package chatapp;

public class Login {
    private String registeredUsername;
    private String registeredPassword;
    private String firstName;
    private String lastName;

    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;
        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
        return hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cell) {
        if (cell == null) return false;
        return cell.matches("^\\+27[0-9]{9}$");
    }

    public String registerUser(String username, String password, String cell, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        this.registeredUsername = username;
        this.registeredPassword = password;
        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        if (registeredUsername == null || registeredPassword == null) {
            return username != null && password != null;
        }
        return registeredUsername.equals(username) && registeredPassword.equals(password);
    }

    public String returnLoginStatus(boolean isSuccess) {
        if (isSuccess) {
            return "Welcome " + (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "") + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
