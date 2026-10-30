package chatapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    Login login = new Login();

    @Test
    public void testCheckUserNameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    // Password tests
    @Test
    public void testCheckPasswordComplexityCorrectlyFormatted() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexityIncorrectlyFormatted() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // Cell number tests
    @Test
    public void testCheckCellPhoneNumberCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumberIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // Login tests
    @Test
    public void testLoginUserSuccessful() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUserFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertFalse(login.loginUser("kyl_1", "wrongPass"));
    }

    @Test
    public void testReturnLoginStatus() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String status = login.returnLoginStatus(true);
        assertTrue(status.contains("Welcome"));
    }
}
