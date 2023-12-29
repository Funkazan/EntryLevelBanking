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
                    System.out.println("Please enter the AccountID of the receiver.");
                    String accountIdInput = scanner.nextLine();
                    // Convert the user input to an Integer
                    int accountId;
                    while (true) {
                        try {
                            accountId = Integer.parseInt(accountIdInput);
                            break; // Exit the loop if parsing is successful
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid input. Please enter an Integer for the balance.");
                            accountIdInput = scanner.nextLine(); // Ask for input again
                        }
                    }
                    Customer sender = new Customer();
                    Customer receiver = new Customer();

                    if (sender != null && receiver != null) {
                        Customer customer = new Customer();

                        customer.performTransfer(sender, accountId, receiver);
                    } else {
                        System.err.println("Invalid sender or receiver.");
                    }
                } else {

                }
            } catch (IllegalStateException e) {
                System.err.println("Something went wrong. Please try again.");
            }
        }
        System.out.println("Thank you for considering VS Bank. Have a nice day!");
        scanner.close();
    }
}
