/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login;

/**
 *
 * @author Student
 */
import java.util.Scanner;
public class ChatAPP {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("===== CHAT APP REGISTRATION =====");
            
            System.out.print("Enter your first name: ");
            String firstName = input.nextLine();
            
            System.out.print("Enter your last name: ");
            String lastName = input.nextLine();
            
            System.out.print("Enter your username: ");
            String username = input.nextLine();
            
            System.out.print("Enter your password: ");
            String password = input.nextLine();
            
            System.out.print("Enter your cellphone number: ");
            String cellPhoneNumber = input.nextLine();
            
            Login user = new Login(firstName, lastName, username,
                    password, cellPhoneNumber);
            
            System.out.println();
            System.out.println("===== REGISTRATION =====");
            
            String registrationMessage = user.registerUser();
            
            System.out.println(registrationMessage);
            
            if (user.checkUserName()
                    && user.checkPasswordComplexity()
                    && user.checkCellPhoneNumber()) {
                
                System.out.println();
                System.out.println("===== LOGIN =====");
                
                System.out.print("Enter your username: ");
                String loginUsername = input.nextLine();
                
                System.out.print("Enter your password: ");
                String loginPassword = input.nextLine();
                
                String loginMessage = user.returnLoginStatus(
                        loginUsername, loginPassword);
                
                System.out.println(loginMessage);
            }
        }
    }
}
