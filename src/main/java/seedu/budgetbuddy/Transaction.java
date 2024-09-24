package seedu.budgetbuddy;

import java.time.LocalDate;

/**
 * The {@code Transaction} class represents a financial transaction.
 * It includes details about the transaction description, amount, and date.
 */
public class Transaction {
    protected String description;
    protected double amount;
    protected LocalDate date;

    public Transaction(String description, double amount, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    /**
     * Returns a string representation of the transaction, including its description, amount, and date.
     *
     * @return A formatted string with the transaction details.
     */
    public String toString() {
        String output = "";
        output += "Description: " + description;
        output += "  Amount: " + amount;
        output += "  Date: " + date;
        return output;
    }
}
