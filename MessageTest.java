package chatapp;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class MessageTest {

    @Before
    public void setUp() {
        Message.populateTestData();
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        // Expected: "Did you get the cake?", "It is dinner time!"
        assertEquals(2, Message.sentMessages.size());
        assertTrue(Message.sentMessages.contains("Did you get the cake?"));
        assertTrue(Message.sentMessages.contains("It is dinner time!"));
    }

    @Test
    public void testDisplayLongestMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, Message.displayLongestMessage());
    }

    @Test
    public void testSearchForMessageID() {
        // Message 4 ID is 0838884567
        String result = Message.searchByMessageID("0838884567");
        assertEquals("It is dinner time!", result);
    }

    @Test
    public void testSearchByRecipient() {
        // Test Data: +27838884567
        String result = Message.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageUsingHash() {
        // Test Data: Message 2 hash
        String result = Message.deleteByHash("1:1:WHEYOU");
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", result);
    }

    @Test
    public void testDisplayReport() {
        String report = Message.displayReport();
        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));
    }
}
