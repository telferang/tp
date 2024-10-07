package seedu.budgetbuddy.transaction.budget;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.transaction.expense.Expense;

import java.time.YearMonth;
import java.util.ArrayList;

/**
 * Manages the budgets for different months and years.
 * Provides methods to add, retrieve, and manage multiple budgets.
 */
public class BudgetManager {
    private static int numberOfBudgets = 0;
    private static ArrayList<Budget> budgets = new ArrayList<>();

    /**
     * Adds a new budget to the list and increments the total number of budgets.
     * Displays a message indicating that a new budget has been added.
     *
     * @param budget The Budget object to be added.
     */
    public static void addBudget(Budget budget) {
        budgets.add(budget);
        numberOfBudgets++;
        Ui.displayBudgetTransactionMessage(budget.toString(), numberOfBudgets);
    }

    /**
     * Deletes budget from the list and decrements the total number of budgets.
     * Displays a message indicating that a budget is deleted.
     *
     * @param budget The Budget object to be added.
     */
    public static void deleteBudget(Budget budget) {
        budgets.remove(budget);
        numberOfBudgets--;
        Ui.displayBudgetDeletedMessage(budget.toString(), numberOfBudgets);
    }

    /**
     * Returns the current number of budgets.
     *
     * @return The total number of budgets.
     */
    public static int getNumberOfBudgets() {
        return numberOfBudgets;
    }

    /**
     * Retrieves a budget for the specified YearMonth date.
     *
     * @param date The YearMonth representing the month and year for the budget.
     * @return The existing Budget for the specified date, or null if no budget exists.
     */
    public static Budget getBudget(YearMonth date) {
        for (Budget budget : budgets) {
            // Check if the budget's date matches the specified date
            if (budget.getDate().equals(date)) {
                return budget; // Return the existing budget
            }
        }
        return null; // No budget found for the specified date
    }

    /**
     * Lists all the budgets managed by the manager.
     * Displays each budget with its corresponding number.
     */
    public static void listBudgets(YearMonth date) {
        String result = "";
        if (date == null) {
            result += "Here are the budgets for the 12 most recent entries:\n";

            int entriesToDisplay = Math.min(budgets.size(), 12);
            for (int counter = 1; counter <= entriesToDisplay; counter++) {
                Budget budget = budgets.get(budgets.size() - counter);
                result += counter + ". " + budget.toString() + "\n";
            }
        } else {
            // Assume validator guarantees date is valid
            Budget budget = getBudget(date);
            if (budget != null) {
                result += "Here is the budget for the specified month:\n";
                result += budget.toString();
            }
        }
        Ui.displayToUser(result);
    }
}
