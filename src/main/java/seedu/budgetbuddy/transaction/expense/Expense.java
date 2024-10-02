package seedu.budgetbuddy.transaction.expense;

import seedu.budgetbuddy.transaction.Transaction;

import java.time.LocalDate;

/**
 * Represents an expense transaction in the budget management system.
 * An expense has a description, amount, date, and category.
 */
public class Expense extends Transaction {
    /** The category of the expense. */
    Category category;

    /**
     * Constructs an Expense object with the specified details.
     *
     * @param description the description of the expense
     * @param amount      the amount of the expense
     * @param date        the date of the expense
     * @param category    the category of the expense
     */
    public Expense(String description, double amount, LocalDate date, Category category) {
        super(description, amount, date);
        this.category = category;
    }

    /**
     * Returns a string representation of the expense, including its details.
     *
     * @return a string describing the expense
     */
    public String toString() {
        String output = "";
        output += "Description: " + description;
        output += "  Amount: " + amount;
        output += "  Date: " + date;
        output += "  Category: " + category;
        return output;
    }
}
