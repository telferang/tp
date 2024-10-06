package seedu.budgetbuddy.transaction.income;

import seedu.budgetbuddy.transaction.Transaction;

import java.time.LocalDate;

/**
 * Represents an income transaction with a description, amount, and date.
 * This class extends the Transaction class to manage income-specific details.
 */
public class Income extends Transaction {
    /**
     * Creates an Income object with the specified description, amount, and date.
     *
     * @param description The description of the income.
     * @param amount The amount of income.
     * @param date The date of the income transaction.
     */
    public Income(String description, double amount, LocalDate date) {
        super(description, amount, date);
    }
}
