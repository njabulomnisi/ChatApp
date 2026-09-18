package registration;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void testUserNameCorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertTrue(login.checkUserName());
    }

    @Test
    public void testUserNameIncorrect() {
        Login login = new Login("Kyle", "Smith", "kyle!!!!!!!",
                "Ch&&sec@ke99!", "+27838968976");

        assertFalse(login.checkUserName());
    }

    @Test
    public void testPasswordCorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordIncorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1",
                "password", "+27838968976");

        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testRegisterUserCellPhoneIncorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "08966553");

        assertEquals("Cell phone number incorrectly formatted or does not "
                + "contain international code; please correct the "
                + "number and try again.",
                login.registerUser());
    }

    @Test
    public void testCellPhoneCorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "08966553");

        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    public void testRegisterUserUsernameIncorrect() {
        Login login = new Login("Kyle", "Smith", "kyle!!!!!!!",
                "Ch&&sec@ke99!", "+27838968976");

        assertEquals("Username is not correctly formatted; please ensure that "
                + "your username contains an underscore and is no more "
                + "than five characters in length.",
                login.registerUser());
    }

    @Test
    public void testRegisterUserPasswordIncorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1",
                "password", "+27838968976");

        assertEquals("Password is not correctly formatted; please ensure that "
                + "the password contains at least eight characters, a "
                + "capital letter, a number, and a special character.",
                login.registerUser());
    }

    @Test
    public void testLoginSuccessful() {
        Login login = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertFalse(login.loginUser("kyl_1", "wrongPassword"));
    }

    @Test
    public void testReturnLoginStatusCorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertEquals("Welcome Kyle,Smith it is great to see you again.",
                login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testReturnLoginStatusIncorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");

        assertEquals("Username or password incorrect, please try again.",
                login.returnLoginStatus("kyl_1", "wrongPassword"));
    }
}