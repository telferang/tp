package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.transaction.budget.Budget;
import seedu.budgetbuddy.transaction.budget.BudgetManager;
import seedu.budgetbuddy.commands.IncorrectCommand;

import java.time.YearMonth;

/**
 * Represents a command to deduct a budget for a specific month and year.
 */
public class DeductBudgetCommand extends Command {
    private double amount;
    private YearMonth date;

    /**
     * Constructs an DeductBudgetCommand with the specified amount and date.
     *
     * @param amount The amount of the budget to be added.
     * @param date   The YearMonth representing the month and year for the budget.
     */
    public DeductBudgetCommand(double amount, YearMonth date) {
        this.amount = amount;
        this.date = date;
    }

    /**
     * Checks if the given command string starts with "deduct budget".
     *
     * @param command The command string entered by the user.
     * @return true if the command starts with "add budget", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("deduct budget");
    }

    /**
     * Executes the AddBudgetCommand by checking if a budget already exists for the specified month and year (date).
     * If a budget exists, the specified amount is added to the existing budget.
     * If no budget exists, a new budget is created and added to the BudgetManager.
     */
    @Override
    public void execute() {
        // Assume validation has guaranteed that existingBudget is not null
        Budget existingBudget = BudgetManager.getBudget(date);

        // Handle if existingBudget is null smoothly
        if (existingBudget == null) {
            return;
        }
        // Deduct the amount from the existing budget
        existingBudget.deductAmount(amount);
    }
}
