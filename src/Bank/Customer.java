package Bank;

import java.text.DecimalFormat;

/**
 * The Customer class represents a bank customer with basic information and
 * account details.
 * It provides methods for performing transactions and transfers.
 */
public class Customer {

    // Fields representing customer information
    protected String firstname;
    protected String surname;
    protected String dateOfBirth;
    protected int iD;
    protected int accountId;
    protected double balance;
    protected double transactionAmount;
    protected double transferAmount;
    protected int id;

    /**
     * Constructs a new Customer with the specified details.
     *
     * @param iD          the customer ID
     * @param surname     the customer's surname
     * @param firstname   the customer's first name
     * @param dateOfBirth the customer's date of birth
     * @param accountId   the customer's account ID
     * @param balance     the initial account balance
     */
    public Customer(int iD, String surname, String firstname, String dateOfBirth, int accountId, double balance) {
        this.iD = iD;
        this.surname = surname;
        this.firstname = firstname;
        this.dateOfBirth = dateOfBirth;
        this.accountId = accountId;
        this.balance = balance;
    }

    public Customer() {
    }

    /**
     * Returns a string representation of the customer's details.
     *
     * @return a string with formatted customer information
     */
    @Override
    public String toString() {
        // Format the balance with two decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        String formattedBalance = decimalFormat.format(balance);

        return "\nCustomer ID: " + iD + "\n" +
                "Surname: " + surname + "\n" +
                "Firstname: " + firstname + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Account ID: " + accountId + "\n" +
                "Balance: " + formattedBalance + "\n";
    }

    /**
     * Performs a transaction by updating the customer's account.
     * The transaction amount can be positive for deposits or
     * negative for withdrawals.
     *
     * @param transactionAmount The amount to be transacted. Positive for deposit,
     *                          negative for withdrawal.
     * @return The updated account balance after the transaction.
     */
    public double performTransaction(double transactionAmount) {
        // Perform transaction
        balance += transactionAmount;
        // Return the new balance after the transaction
        return balance;
    }

    /**
     * Performs a fund transfer between two customers' accounts.
     *
     * @param sender   the customer initiating the transfer
     * @param amount   the amount to be transferred
     * @param receiver the customer receiving the transfer
     * @return true if the transfer is successful, false otherwise
     */
    public boolean performTransfer(double transferAmount, Customer receiver) {
        // Check for null sender and receiver
        if (this == null || receiver == null) {
            System.err.println("Invalid sender or receiver");
            return false;
        }
        // Check if iD1 has sufficient balance for the transfer
        if (this.balance >= transferAmount) {
            // Perform the transfer
            this.balance -= transferAmount;
            receiver.balance += transferAmount;
            // Transfer successful
            return true;
        } else {
            // Insufficient balance for the transfer
            // Transfer failed
            return false;
        }
    }

    /**
     * Gets the ID of the customer.
     * 
     * @return The ID of the customer.
     */
    public int getId() {
        return this.accountId;
    }
}