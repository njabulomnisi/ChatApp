/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
       

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    
    private static int totalMessageSent = 0;
    private static String sentMessages = "";
    
    public Message(int messageNumber, String recipient, String message){
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageID = messageID;
        this.messageHash = messageHash;   
    }
    
    public String generateMessageID(){
        Random random = new Random();
        
        String id = "";
        
        for(int i = 0; i <10; i++){
            int digit = random.nextInt(10);
            id += digit;
        }
        return id;
    }
    
    public boolean checkMessageID(){
        return messageID.length() <=10;
    }
    
    public String checkRecipient(){
        String cellRegex = "\\+27\\d{9}$";
        
        if(recipient.matches(cellRegex)){
            return "Cellphone number is successsfully captured.";
        }else{
            return "Cellphone number is incorrctly formatted or doesn't contain an international code: +27..";
        }
    }
    
    public boolean isRecipientValid(){
        return recipient.matches("\\+27\\d{9}$");
    }
    
    public String checkMessageLength(){
        if(message.length()<=250){
            return "Message is ready to send";
        }else{
            int extraCharacters = message.length() - 250;
            return "Message exceeds 250 characters by " + extraCharacters;
        }
    }
    
    public boolean isMessageValid(){
        return message.length()<=250;
    }
    
    public String createMessageHash(){
        String[] words = message.trim().split("\\s+");
        
        String firstWord = words[0];
        String lastWord = words[words.length-1];
        firstWord = firstWord.replaceAll("[^a-zA-Z0-9]", "");
        lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "");
        
        String hash = messageID.substring(0,2)
                +":"
                +messageNumber
                +":"
                +firstWord
                +lastWord;
        
        return hash.toUpperCase();
    }
    
    public String sentMessage(int option){
        if(option ==1){
            totalMessageSent++;
            
            sentMessages += printMessageDetails()
                    +System.lineSeparator()
                    +"======================"
                    +System.lineSeparator();
            return "Message is successfully sent.";
            
        }else if (option ==0){
            return"Press 0 to delete the message.";
            
        }else if (option ==2){
            return"Message successfully stored.";
        }else {
        return "Invalid option";
        }
        
        public String printMessages(){
            if(sentMessages.isEmpty()){
                return"No messages sent";
            }
            return sentMessages;
        }
        
        public int returnTotalMessages(){
            return totalMessageSent;
        }
        
        public String printMessageDetails(){
            return"Message ID" + messageID
                    +System.lineSeparator()
                    +"Message hash: " + messageHash
                    +System.lineSeparator()
                    +"Recipient: " + recipient
                    +"Message: " + message;
        }
        
        public String storeMessage(){
            
        }
}
