
package chatapp;

import java.util.Scanner;

public class ChatApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login appLogin = new Login();

        // --- PART 1 LOGIN (keep your existing logic) ---
        System.out.print("Enter username: ");
        String inputUser = scanner.nextLine();
        System.out.print("Enter password: ");
        String inputPass = scanner.nextLine();

        boolean isSuccess = appLogin.loginUser(inputUser, inputPass);
        String loginStatusMsg = appLogin.returnLoginStatus(isSuccess);

        System.out.println("\n--- LOGIN STATUS ---");
        System.out.println(loginStatusMsg);

        if (isSuccess) {
            System.out.println("\n_________________________________");
            System.out.println(" Welcome to QuickChat. ");
            System.out.println("_________________________________");

            System.out.print("Enter the number of messages you wish to send: ");
            int messageQuota;
            try {
                messageQuota = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                messageQuota = 1;
            }

            boolean running = true;
            int messagesProcessed = 0;

            while (running) {
                System.out.println("\n--- QUICKCHAT MENU ---");
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Quit");
                System.out.print("Choose an option: ");

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number (1-3).");
                    continue;
                }

                switch (choice) {
                    case 1:
                        if (messagesProcessed >= messageQuota) {
                            System.out.println("You have reached your message quota for this session (" + messageQuota + ").");
                            break;
                        }

                        Message tempMsg = new Message();

                        for (int i = messagesProcessed; i < messageQuota; i++) {
                            System.out.println("\n--- Drafting Message " + (i + 1) + " of " + messageQuota + " ---");
                            
                            System.out.print("Enter Recipient Cell Number (e.g., +27838968976): ");
                            String recipientCell = scanner.nextLine();
                            System.out.println(tempMsg.checkRecipientCell(recipientCell));

                            System.out.print("Enter Message Text (max 250 characters): ");
                            String messageText = scanner.nextLine();
                            System.out.println(tempMsg.checkMessageLength(messageText));

                            Message msg = new Message(i, recipientCell, messageText);

                            System.out.println("\n1. Send Message\n2. Disregard\n3. Store to send later");
                            System.out.print("Choose: ");
                            int actionChoice = Integer.parseInt(scanner.nextLine());
                            String result = msg.SentMessage(actionChoice);
                            System.out.println(result);

                            if (actionChoice == 1) {
                                System.out.println(msg.printMessages());
                            }

                            messagesProcessed++;

                            if (messagesProcessed < messageQuota) {
                                System.out.print("\nDo you want to send another message now? (yes/no): ");
                                String answer = scanner.nextLine();
                                if (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("y")) {
                                    break;
                                }
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\nComing Soon.");
                        break;

                    case 3:
                        running = false;
                        System.out.println("\nExiting QuickChat...");
                        break;

                    default:
                        System.out.println("Invalid option. Please choose 1, 2, or 3.");
                        break;
                }
            }

            System.out.println("\n_________________________________");
            System.out.println("Total Messages Sent This Session: " + Message.returnTotalMessages());
            System.out.println("_________________________________");
        } else {
            System.out.println("\nRegistration failed. Please restart the application and enter valid details.");
        }
        scanner.close();
    }
}
