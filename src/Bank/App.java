package Bank;

import java.util.Scanner;

public class App {

    // Assuming you have a method to generate a unique customer ID
    private static int generateId() {
        // Implement your logic to generate a unique customer ID
        // This can involve querying a database or using some unique indentifier logic
        // For simplicity, let's return a random number
        return (int) (Math.random() * 100000);
    }

    /**
     * This is the main-method and it represents the conversation between the bank
     * and the customer.
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the VS Bank!");
        String exit = "Exit";
        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase(exit)) {
            try {
                System.out.println("How can I help you?\n" +
                        "0 = Register as a new Customer\n" +
                        "1 = Transfer money\n" +
                        "2 = Perform transaction");
                String response = scanner.nextLine();
                if ("0".equals(response)) {
                    // User wants to register as a new customer
                    System.out.println("Please enter your surname:");
                    String surname = scanner.nextLine();
                    System.out.println("Please enter your firstname:");
                    String firstname = scanner.nextLine();
                    System.out.println("Please enter your date of birth:");
                    String dateOfBirth = scanner.nextLine();
                    System.out.println("Please enter the amount you want to put in your account:");
                    String balanceInput = scanner.nextLine();
                    // Convert the user input to an Integer
                    int balance;
                    while (true) {
                        try {
                            balance = Integer.parseInt(balanceInput);
                            break; // Exit the loop if parsing is successful
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid input. Please enter an Integer for the balance.");
                            balanceInput = scanner.nextLine(); // Ask for input again
                        }
                    }
                    Customer newCustomer = new Customer(generateId(), surname, firstname, dateOfBirth,
                            generateId(), balance);
                    System.out.println("Registration successful! Your details: ");
                    System.out.println(newCustomer.toString());
                } else if ("1".equals(response)) {
                    System.out.println("Please enter the AccountID of the sender.");
                    String senderIdInput = scanner.nextLine();
                    System.out.println("Please enter the AccountID of the receiver.");
                    String receiverIdInput = scanner.nextLine();
                    System.out.println("Please enter the amount of the money which is to be transfered.");
                    String transferAmountInput = scanner.nextLine();
                    // Convert the user input to an Integer
                    int senderId, receiverId, transferAmount;
                    while (true) {
                        try {
                            senderId = Integer.parseInt(senderIdInput);
                            receiverId = Integer.parseInt(receiverIdInput);
                            transferAmount = Integer.parseInt(transferAmountInput);
                            break; // Exit the loop if parsing is successful
                        } catch (NumberFormatException e) {
                            System.err.println(
                                    "Invalid input. Please enter an Integer for the sender, receiver and amount.");
                            senderIdInput = scanner.nextLine();
                            receiverIdInput = scanner.nextLine();
                            transferAmountInput = scanner.nextLine();
                        }
                    }
                    Customer sender = new Customer(senderId, "sender", "sender", "dateOfBirth", generateId(), 1000);
                    Customer receiver = new Customer(receiverId, "receiver", "receiver", "dateOfBirth", generateId(),
                            0);

                    // Perform transfer
                    sender.performTransfer(sender, transferAmount, receiver);
                } else {
                    /* further functions */
                }
            } catch (IllegalStateException e) {
                System.err.println("Something went wrong. Please try again.");
            }
        }
        System.out.println("Thank you for considering VS Bank. Have a nice day!");
        scanner.close();
    }
}
