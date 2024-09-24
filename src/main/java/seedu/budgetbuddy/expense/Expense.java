package seedu.budgetbuddy.expense;

import seedu.budgetbuddy.Transaction;

import java.time.LocalDate;

public class Expense extends Transaction {
    Category category;

    public Expense(String description, double amount, LocalDate date, Category category) {
        super(description, amount, date);
        this.category = category;
    }

    public String toString() {
        String output = "";
        output += "Description: " + description;
        output += "  Amount: " + amount;
        output += "  Date: " + date;
        output += "  Category: " + category;
        return output;
    }
}
