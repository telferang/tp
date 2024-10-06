package seedu.budgetbuddy.transaction.budget;

import seedu.budgetbuddy.Ui;

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
}
