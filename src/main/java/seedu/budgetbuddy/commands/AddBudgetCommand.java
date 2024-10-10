package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.transaction.budget.Budget;
import seedu.budgetbuddy.transaction.budget.BudgetManager;

import java.time.YearMonth;

/**
 * Represents a command to add a budget for a specific month and year.
 */
public class AddBudgetCommand extends Command {
    private double amount;
    private YearMonth date;

    /**
     * Constructs an AddBudgetCommand with the specified amount and date.
     *
     * @param amount The amount of the budget to be added.
     * @param date The YearMonth representing the month and year for the budget.
     */
    public AddBudgetCommand(double amount, YearMonth date) {
        this.amount = amount;
        this.date = date;
    }

    /**
     * Checks if the given command string starts with "add budget".
     *
     * @param command The command string entered by the user.
     * @return true if the command starts with "add budget", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("add budget");
    }

    /**
     * Executes the AddBudgetCommand by checking if a budget already exists for the specified month and year (date).
     * If a budget exists, the specified amount is added to the existing budget.
     * If no budget exists, a new budget is created and added to the BudgetManager.
     */
    @Override
    public void execute() {
        // Check if a budget already exists for the specified date
        Budget existingBudget = BudgetManager.getBudget(date);

        if (existingBudget != null) {
            // Update the existing budget's amount
            existingBudget.addAmount(amount);
        } else {
            // Add a new budget since it does not exist
            BudgetManager.addBudget(new Budget(amount, date));
        }
    }
}
