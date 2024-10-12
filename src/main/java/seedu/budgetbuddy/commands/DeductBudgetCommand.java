package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.transaction.budget.Budget;
import seedu.budgetbuddy.transaction.budget.BudgetManager;

import java.time.YearMonth;
import java.util.logging.Logger;

/**
 * Represents a command to deduct a budget for a specific month and year.
 */
public class DeductBudgetCommand extends Command {
    private double amount;
    private YearMonth date;
    private static final Logger logger = Logger.getLogger(DeductBudgetCommand.class.getName());

    /**
     * Constructs a DeductBudgetCommand with the specified amount and date.
     *
     * @param amount The amount of the budget to be deducted.
     * @param date The YearMonth representing the month and year for the budget.
     */
    public DeductBudgetCommand(double amount, YearMonth date) {
        assert amount >= 0 : "Amount must be non-negative";
        assert date != null : "Date cannot be null";
        this.amount = amount;
        this.date = date;
    }

    /**
     * Checks if the given command string starts with "deduct budget".
     *
     * @param command The command string entered by the user.
     * @return true if the command starts with "deduct budget", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("deduct budget");
    }

    /**
     * Executes the DeductBudgetCommand by checking if a budget already exists for the specified month and year.
     * If a budget exists, the specified amount is deducted from the existing budget.
     * If no budget exists, the method simply returns, as the exception is handled in the DeductBudgetValidator.
     */
    @Override
    public void execute() {
        // Assume validation has guaranteed that existingBudget is not null
        Budget existingBudget = BudgetManager.getBudget(date);
        existingBudget.deductAmount(amount);
        logger.info("Deducted " + amount + " from budget for date: " + date);
    }
}
