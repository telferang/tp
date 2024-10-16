package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.DeductBudgetCommand;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.transaction.budget.BudgetManager;

import java.time.YearMonth;
import java.util.logging.Logger;

import static seedu.budgetbuddy.validators.AmountValidator.validateAmount;
import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

/**
 * Validates commands for deducting budgets.
 */
public class DeductBudgetValidator {
    private static Logger logger = Logger.getLogger(DeductBudgetValidator.class.getName());

    public static Command processCommand(String command) throws BudgetBuddyException {
        assert command != null : "Command cannot be null";

        if (command.equals("deduct budget")) {
            logger.warning("Attempted to deduct budget without description.");
            throw new BudgetBuddyException("No description provided.");
        }

        String trimmedCommand = command.substring("deduct budget ".length());
        String[] parts = trimmedCommand.split(" ");

        // Initialize default values
        double amount = 0; // invalid amount initially
        YearMonth date = null; // invalid date initially

        // Process parts to extract details
        for (String part : parts) {
            if (part.startsWith("a/")) {
                amount = validateAmount(part);
                if (amount == -1) {
                    throw new BudgetBuddyException("Invalid amount format. Amount should be a positive number.");
                }
            } else if (part.startsWith("m/")) {
                date = validateYearMonth(part);
            }
        }

        // Validate amount
        if (amount <= 0) {
            throw new BudgetBuddyException("Invalid amount: " + amount + ". Must be a positive value.");
        }

        // Validate date
        if (date == null) {
            throw new BudgetBuddyException("Invalid date format. Use m/MM/yyyy.");
        }

        // Check if budget exists for the date
        if (BudgetManager.getBudget(date) == null) {
            throw new BudgetBuddyException("Budget does not exist for the specified date: " + date);
        }

        // All validations passed, return the command
        return new DeductBudgetCommand(amount, date);
    }
}
