package Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    // Initialize an Arraylist to store and handle the customer information
    private static List<Customer> customers = new ArrayList<>();

    // Assuming you have a method to generate a unique customer ID
    private static int generateId() {
        // Implement your logic to generate a unique customer ID
        // This can involve querying a database or using some unique indentifier logic
        // For simplicity, let's return a random number
        return (int) (Math.random() * 100000);
    }

    /**
     * Finds a customer in the list based on the provided customer ID.
     * 
     * @param customerId The ID of the customer to find.
     * @return The customer with the specified ID, or null if not found.
     */
    private static Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            System.out.println("Checking customer ID: " + customer.getId());
            if (customer.getId() == customerId) {
                System.out.println("Customer found!");
                return customer;
            }
        }
        System.out.println("Customer not found!");
        return null; // Customer not found
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
                        "1 = Show Customer details\n" +
                        "2 = Transfer money\n" +
                        "3 = Perform transaction");
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
                    double balance;
                    while (true) {
                        try {
                            balance = Double.parseDouble(balanceInput);
                            break; // Exit the loop if parsing is successful
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid input. Please enter an Integer for the balance.");
                            balanceInput = scanner.nextLine(); // Ask for input again
                        }
                    }
                    Customer newCustomer = new Customer(generateId(), surname, firstname, dateOfBirth, generateId(),
                            balance);
                    customers.add(newCustomer); // Add the new customer to the list
                    System.out.println("Registration successful! Your details: ");
                    System.out.println(newCustomer.toString());
                } else if ("1".equals(response)) {
                    try {
                        if (!customers.isEmpty()) {
                            System.out.println("Custimer details:\n");
                            for (Customer c : customers) {
                                System.out.println(c.toString());
                            }
                        } else {
                            System.out.println("No data available yet.");
                        }
                    } catch (Exception e) {
                        System.err.println("Something went wrong. Try again");
                        response = scanner.nextLine();
                    }
                } else if ("2".equals(response)) {
                    System.out.println("Please enter the AccountID of the sender.");
                    int senderId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Please enter the AccountID of the receiver.");
                    int receiverId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Please enter the amount of the money which is to be transfered.");
                    double transferAmountInput = Double.parseDouble(scanner.nextLine());

                    // Find sender and receiver in the customer list
                    Customer sender = findCustomerById(senderId);
                    Customer receiver = findCustomerById(receiverId);

                    if (sender != null && receiver != null) {
                        // Perform transfer
                        boolean transferSuccessful = sender.performTransfer(transferAmountInput, receiver);
                        if (transferSuccessful) {
                            System.out.println("Transfer successful!");
                        } else {
                            System.err.println("Transfer failed. Insufficient balance!");
                        }
                    } else {
                        System.err.println("Invalid sender or receiver!");
                    }
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
