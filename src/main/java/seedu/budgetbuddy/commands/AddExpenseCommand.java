package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.expense.Category;
import seedu.budgetbuddy.expense.Expense;
import seedu.budgetbuddy.expense.ExpenseManager;

import java.time.LocalDate;

public class AddExpenseCommand extends Command {
    String description;
    double amount;
    LocalDate date;
    Category category;

    public AddExpenseCommand(String description, double amount, LocalDate date, Category category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public static boolean isCommand(String command) {
        return command.startsWith("add expense");
    }

    public void execute(){
        ExpenseManager.addExpense(new Expense(description, amount, date, category));

    }
}
