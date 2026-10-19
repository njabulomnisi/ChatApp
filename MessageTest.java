package chatapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    Message msg = new Message();

    @Test
    public void testCheckRecipientCellValid() {
        String result = msg.checkRecipientCell("+27838968976");
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testCheckRecipientCellInvalid() {
        String result = msg.checkRecipientCell("08575975889");
        assertTrue(result.contains("incorrectly formatted"));
    }

    @Test
    public void testCheckMessageLengthValid() {
        String result = msg.checkMessageLength("Hi Mike, can you please come and bring a boat.");
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testCheckMessageLengthInvalid() {
        // Create a message > 250 chars
        String longMsg = "a".repeat(260);
        String result = msg.checkMessageLength(longMsg);
        assertTrue(result.contains("exceeds 250 characters"));
    }

    @Test
    public void testCreateMessageHash() {
        Message testMsg = new Message(0, "+27838968976", "Hi Mike, can you please come and bring a boat.");
        // Hash should be like 00:0:HIBOAT or similar
        String hash = testMsg.getMessageHash();
        assertNotNull(hash);
        assertFalse(hash.isEmpty());
        assertTrue(hash.contains(":"));
    }

    @Test
    public void testSentMessageSend() {
        Message testMsg = new Message(1, "+27838968976", "Test");
        String result = testMsg.SentMessage(1);
        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testSentMessageStore() {
        Message testMsg = new Message(1, "+27838968976", "Test");
        String result = testMsg.SentMessage(3);
        assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testTotalMessages() {
        int before = Message.returnTotalMessages();
        Message testMsg = new Message(1, "+27838968976", "Test");
        testMsg.SentMessage(1);
        int after = Message.returnTotalMessages();
        assertEquals(before + 1, after);
    }
}