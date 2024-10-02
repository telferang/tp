package seedu.budgetbuddy.transaction.expense;

import seedu.budgetbuddy.Ui;

import java.util.ArrayList;

/**
 * Manages a list of expenses, providing functionalities to add, delete,
 * and list expenses, as well as tracking the total number of expenses.
 */
public class ExpenseManager {
    private static int numberOfExpenses = 0;
    private static ArrayList<Expense> expenses = new ArrayList<>();

    /**
     * Adds a new expense to the manager.
     *
     * @param expense The expense to be added.
     */
    public static void addExpense(Expense expense) {
        expenses.add(expense);
        numberOfExpenses++;
        Ui.displayAcknowledgmentMessage(expense.toString(), "added", "expense", numberOfExpenses);
    }

    /**
     * Deletes an expense from the manager at the specified index.
     *
     * @param index The index of the expense to be deleted.
     */
    public static void deleteExpense(int index) {
        numberOfExpenses--;
        Ui.displayAcknowledgmentMessage(expenses.get(index).toString(), "deleted", "expense", numberOfExpenses);
        expenses.remove(index);
    }

    /**
     * Returns the current number of expenses.
     *
     * @return The total number of expenses.
     */
    public static int getNumberOfExpenses() {
        return numberOfExpenses;
    }

    /**
     * Lists all the expenses managed by the manager.
     * Displays each expense with its corresponding number.
     */
    public static void listExpenses() {
        String result = "";
        int counter = 1;
        for (Expense expense : expenses) {
            result += counter + ". " + expense.toString() + "\n";
            counter++;
        }
        Ui.displayToUser(result);
    }
}
