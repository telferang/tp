package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.transaction.budget.Budget;
import seedu.budgetbuddy.transaction.budget.BudgetManager;

import java.time.YearMonth;
import java.util.logging.Logger;

/**
 * Represents a command to add a budget for a specific month and year.
 */
public class AddBudgetCommand extends Command {
    private double amount;
    private YearMonth date;
    private static final Logger logger = Logger.getLogger(AddBudgetCommand.class.getName());

    /**
     * Constructs an AddBudgetCommand with the specified amount and date.
     *
     * @param amount The amount of the budget to be added.
     * @param date The YearMonth representing the month and year for the budget.
     */
    public AddBudgetCommand(double amount, YearMonth date) {
        assert amount >= 0 : "Amount must be non-negative";
        assert date != null : "Date cannot be null";
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
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be non-negative.");
        }

        // Check if a budget already exists for the specified date
        Budget existingBudget = BudgetManager.getBudget(date);

        if (existingBudget != null) {
            existingBudget.addAmount(amount);
            logger.info("Updated existing budget for date: " + date + " with amount: " + amount);
        } else {
            BudgetManager.addBudget(new Budget(amount, date));
            logger.info("Added new budget for date: " + date + " with amount: " + amount);
        }
    }
}
