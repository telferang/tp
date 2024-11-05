package seedu.budgetbuddy.transaction.budget;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Manages the remaining budgets after expenses have been deducted from the budgets.
 * Initializes the remaining budgets based on the current expenses and provides functionality
 * to retrieve and list remaining budgets.
 */
public class RemainingBudgetManager {

    /** Logger to record events and operations for RemainingBudgetManager. */
    private static final Logger LOGGER = LoggerSetup.getLogger();

    /** List of budgets after expenses have been deducted. */
    private ArrayList<Budget> remainingBudgets;

    /**
     * Constructs a new RemainingBudgetManager. Initializes the remaining budgets by copying
     * the existing budgets and deducting the appropriate expenses.
     */
    public RemainingBudgetManager() {
        remainingBudgets = new ArrayList<>();
        copyBudgetManager();
        ArrayList<Expense> expenses = ExpenseManager.getExpenses();
        for (Expense expense : expenses) {
            YearMonth expenseDate = YearMonth.from(expense.getDate());
            Category expenseCategory = expense.getCategory();
            double expenseAmount = expense.getAmount();

            // Try to find a budget for the expense's YearMonth
            Budget matchingBudget = null;

            matchingBudget = searchForGivenBudget(expenseDate, matchingBudget);

            // If no budget is found, create a new one with zero initial amount
            matchingBudget = createNewBudget(matchingBudget, expenseDate);

            // Deduct the expense from the matching budget (allowing it to go negative)
            matchingBudget.deductExpense(expenseCategory, expenseAmount);
            LOGGER.info("Deducted " + expenseAmount + " from budget for " + expenseDate
                    + " in category " + expenseCategory);
        }
        LOGGER.info("Remaining budgets initialized and updated after deductions.");
    }

    /**
     * Creates a new budget for a given date if no matching budget is found.
     *
     * @param matchingBudget The existing matching budget, or null if no budget exists.
     * @param expenseDate The date (YearMonth) of the expense.
     * @return The new or existing budget for the specified date.
     */
    private Budget createNewBudget(Budget matchingBudget, YearMonth expenseDate) {
        assert expenseDate != null : "Expense date cannot be null";
        if (matchingBudget == null) {
            matchingBudget = new Budget(expenseDate);
            remainingBudgets.add(matchingBudget);  // Add the new budget to the list
            LOGGER.info("Created new budget for " + expenseDate + " with initial amount 0.0");
        }
        return matchingBudget;
    }

    /**
     * Searches for an existing budget that matches the specified date.
     *
     * @param expenseDate The date (YearMonth) to search for.
     * @param matchingBudget The budget to match, initially null.
     * @return The matching budget, or null if none is found.
     */
    private Budget searchForGivenBudget(YearMonth expenseDate, Budget matchingBudget) {
        for (Budget budget : remainingBudgets) {
            if (budget.getDate().equals(expenseDate)) {
                matchingBudget = budget;
                LOGGER.info("Found existing budget for date: " + expenseDate);
                break;
            }
        }
        return matchingBudget;
    }

    /**
     * Copies the existing budgets from the BudgetManager to initialize the remainingBudgets.
     */
    private void copyBudgetManager() {
        ArrayList<Budget> budgets = BudgetManager.getBudgets();
        assert budgets != null : "BudgetManager's budgets cannot be null";
        for (Budget budget : budgets) {
            remainingBudgets.add(new Budget(budget));
            LOGGER.info("Copied budget: " + budget);
        }
    }

    /**
     * Lists all remaining budgets after expenses have been deducted.
     * Displays the result to the user through the UI.
     */
    public void listRemainingBudgets() {
        if (remainingBudgets.size() == 0) {
            Ui.displayToUser("No budgets found");
            LOGGER.info("No budgets found");
            return;
        }
        String result = "All budgets after deductions:";
        for (Budget budget : remainingBudgets) {
            result += "\n" + budget;
        }
        Ui.displayToUser(result);
        LOGGER.info("Displayed all remaining budgets to user.");
    }

    /**
     * Retrieves the remaining budget for a given date and category.
     *
     * @param date The date (LocalDate) of the expense.
     * @param category The category of the expense.
     * @return A message indicating the remaining budget, or that no budget was found.
     */
    public String getRemainingBudgets(LocalDate date, Category category) {
        YearMonth expenseMonth = YearMonth.from(date);
        assert date != null : "Date cannot be null";
        assert category != null : "Category cannot be null";

        for (Budget budget : remainingBudgets) {
            if (budget.getDate().equals(expenseMonth)) {
                Double remainingAmount = budget.getCategoryBudgets().get(category);
                if (remainingAmount == null) {
                    remainingAmount = 0.0; // If the category does not exist, assume remaining amount is 0
                }
                LOGGER.info("Retrieved remaining budget for " + expenseMonth + " in category " + category
                        + ": " + remainingAmount);
                String result = "The remaining budget for " + expenseMonth + " in the " + category
                        + " category is: " + remainingAmount;
                if (remainingAmount < 0) {
                    result += "\nCaution! You have exceeded your budget!";
                }
                return result;
            }
        }

        // If no budget is found for the specified date
        LOGGER.warning("No budget found for " + expenseMonth + ".");
        return "No budget found for " + expenseMonth + ".";
    }
}
