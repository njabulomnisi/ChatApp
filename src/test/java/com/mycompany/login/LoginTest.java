package com.mycompany.login;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class LoginTest{
    @Test
    public void testUserNameCorrect() {
        Login user = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkUserName());
    }

    @Test
    public void testUserNameIncorrect() {
        Login user = new Login("Kyle", "Smith", "kyle!!!!!!!",
                "Ch&&sec@ke99!", "+27838968976");

        assertFalse(user.checkUserName());
    }

    @Test
    public void testPasswordCorrect() {
        Login user = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testPasswordIncorrect() {
        Login user = new Login("Kyle", "Smith", "kyl_1",
                "password", "+27838968976");

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testRegisterUserCellPhoneIncorrect() {
    Login user = new Login("Kyle", "Smith", "kyl_1",
            "Ch&&sec@ke99!", "08966553");

    assertEquals("Cell phone number incorrectly formatted or does not contain international code; please correct the number and try again.",
            user.registerUser());
    }

    @Test
    public void testCellPhoneCorrect() {
        Login user = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrect() {
        Login user = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "08966553");

        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testRegisterUserUsernameIncorrect() {
        Login user = new Login("Kyle", "Smith", "kyle!!!!!!!",
                "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                user.registerUser());
    }

    @Test
    public void testRegisterUserPasswordIncorrect() {
        Login user = new Login("Kyle", "Smith", "kyl_1",
                "password", "+27838968976");

        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                user.registerUser());
    }

    @Test
    public void testLoginSuccessful() {
        Login user = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login user = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertFalse(user.loginUser("kyl_1", "wrongPassword"));
    }

    @Test
    public void testReturnLoginStatusCorrect() {
        Login user = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertEquals("Welcome Kyle,Smith it is great to see you again.",
                user.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testReturnLoginStatusIncorrect() {
        Login user = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertEquals("Username or password incorrect, please try again.",
                user.returnLoginStatus("kyl_1", "wrongPassword"));
    }
}
