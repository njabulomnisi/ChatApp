package chatapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String status;
    
    private static int totalMessagesSent = 0;

    // Part 3 Arrays
    public static List<String> sentMessages = new ArrayList<>();
    public static List<String> disregardedMessages = new ArrayList<>();
    public static List<Message> storedMessages = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();
    public static List<Message> allMessages = new ArrayList<>();

    // Empty constructor for line: new Message()
    public Message() {
    }

    // Constructor for ChatApp line 75: new Message(i, recipientCell, messageText)
    public Message(int messageNumber, String recipient, String text) {
        this.recipient = recipient;
        this.messageText = text;
        this.status = "Pending";
        this.messageID = createMessageID();
        this.messageHash = createMessageHash(this.messageID, messageNumber, text);
        // For Part 3 lists
        this.allMessages.add(this);
        this.messageIDs.add(this.messageID);
        this.messageHashes.add(this.messageHash);
    }

    // Constructor for Part 3 tests
    public Message(String id, String recipient, String text, String hash, String status) {
        this.messageID = id;
        this.recipient = recipient;
        this.messageText = text;
        this.messageHash = hash;
        this.status = status;
    }

    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return messageText; }
    public String getMessageHash() { return messageHash; }

    // Part 2 methods
    public String checkRecipientCell(String cell) {
        if (cell != null && cell.length() <= 13 && cell.startsWith("+27")) {
            return "Cell phone number successfully captured";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again";
        }
    }

    public String checkMessageLength(String text) {
        if (text.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Message exceeds 250 characters by " + (text.length() - 250) + ", please reduce size.";
        }
    }

    public String createMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        this.messageID = String.valueOf(id);
        return this.messageID;
    }

    public String createMessageHash(String msgId, int msgNumber, String message) {
        String[] words = message.trim().split("\\s+");
        String first = words[0];
        String last = words[words.length - 1];
        if (first.length() > 3) first = first.substring(0,3);
        if (last.length() > 3) last = last.substring(0,3);
        this.messageHash = msgId.substring(0,2) + ":" + msgNumber + ":" + first.toUpperCase() + last.toUpperCase();
        return this.messageHash;
    }

    // FIX for error: method SentMessage(int) - ChatApp line 80
    public String SentMessage(int choice) {
        if (choice == 1) {
            this.status = "Sent";
            sentMessages.add(this.messageText);
            totalMessagesSent++;
            return "Message successfully sent.";
        } else if (choice == 2) {
            this.status = "Disregard";
            disregardedMessages.add(this.messageText);
            return "Press 0 to delete message.";
        } else if (choice == 3) {
            this.status = "Stored";
            storedMessages.add(this);
            return "Message successfully stored.";
        } else {
            return "Invalid choice.";
        }
    }

    // FIX for error: method printMessages() - ChatApp line 83
    public String printMessages() {
        return "MessageID: " + this.messageID + "\n" +
               "MessageHash: " + this.messageHash + "\n" +
               "Recipient: " + this.recipient + "\n" +
               "Message: " + this.messageText;
    }

    // FIX for error: method returnTotalMessages() - ChatApp line 114
    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    // Part 3 methods for unit tests
    public static void populateTestData() {
        sentMessages.clear(); disregardedMessages.clear(); storedMessages.clear();
        messageHashes.clear(); messageIDs.clear(); allMessages.clear();
        totalMessagesSent = 0;
        addToArrays("01", "+27834557896", "Did you get the cake?", "0:0:DIDCAK", "Sent");
        addToArrays("02", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "1:1:WHEYOU", "Stored");
        addToArrays("03", "+27834484567", "Yohoooo, I am at your gate.", "2:2:YOHGAT", "Disregard");
        addToArrays("0838884567", "+27838884567", "It is dinner time!", "3:3:ITDIN", "Sent");
        addToArrays("05", "+27838884567", "Ok, I am leaving without you.", "4:4:OKYOU", "Stored");
    }

    private static void addToArrays(String id, String recipient, String text, String hash, String status) {
        Message m = new Message(id, recipient, text, hash, status);
        allMessages.add(m);
        messageIDs.add(id);
        messageHashes.add(hash);
        if (status.equals("Sent")) { sentMessages.add(text); totalMessagesSent++; }
        if (status.equals("Disregard")) disregardedMessages.add(text);
        if (status.equals("Stored")) storedMessages.add(m);
    }

    public static String displayLongestMessage() {
        String longest = "";
        for (Message m : allMessages) {
            if (m.getMessage().length() > longest.length()) longest = m.getMessage();
        }
        return longest;
    }

    public static String searchByMessageID(String id) {
        for (Message m : allMessages) {
            if (m.getMessageID().equals(id)) return m.getMessage();
        }
        return "Not found";
    }

    public static String searchByRecipient(String recipient) {
        String result = "";
        for (Message m : allMessages) {
            if (m.getRecipient().equals(recipient)) {
                if (!result.isEmpty()) result += "\n";
                result += m.getMessage();
            }
        }
        return result;
    }

    public static String deleteByHash(String hash) {
        for (int i = 0; i < allMessages.size(); i++) {
            if (allMessages.get(i).getMessageHash().equals(hash)) {
                String text = allMessages.get(i).getMessage();
                storedMessages.removeIf(mm -> mm.getMessageHash().equals(hash));
                allMessages.remove(i);
                messageHashes.remove(hash);
                return "Message: \"" + text + "\" successfully deleted.";
            }
        }
        return "Hash not found";
    }

    public static String displayReport() {
        String report = "";
        for (Message m : allMessages) {
            report += "Message Hash: " + m.getMessageHash() + "\n";
            report += "Recipient: " + m.getRecipient() + "\n";
            report += "Message: " + m.getMessage() + "\n\n";
        }
        return report;
    }
}
