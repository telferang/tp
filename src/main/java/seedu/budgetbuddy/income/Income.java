package seedu.budgetbuddy.income;

import seedu.budgetbuddy.Transaction;

import java.time.LocalDate;

public class Income extends Transaction {
    public Income(String description, double amount, LocalDate date) {
        super(description, amount, date);
    }
}
