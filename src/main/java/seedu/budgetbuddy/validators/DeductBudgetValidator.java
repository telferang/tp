package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.DeductBudgetCommand;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.transaction.budget.BudgetManager;

import java.time.YearMonth;

import static seedu.budgetbuddy.validators.AmountValidator.validateAmount;
import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

/**
 * Validates commands for deducting budgets.
 */
public class DeductBudgetValidator {
    public static Command processCommand(String command) {
        if (command.equals("deduct budget")) {
            return new IncorrectCommand("No description provided.");
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
                    return new IncorrectCommand("Invalid amount format. Amount should be a positive number.");
                }
            } else if (part.startsWith("m/")) {
                date = validateYearMonth(part);
            }
        }

        // Validate amount
        if (amount == 0) {
            return new IncorrectCommand("Amount not entered.");
        } else if (amount < 0) {
            return new IncorrectCommand("Invalid amount format. Amount must be a positive value.");
        }

        // Validate date
        if (date == null) {
            return new IncorrectCommand("Invalid date format. Use m/MM/yyyy.");
        }

        // Check if budget exists for the date
        if (BudgetManager.getBudget(date) == null) {
            return new IncorrectCommand("Budget does not exist for the specified date.");
        }

        // All validations passed, return the command
        return new DeductBudgetCommand(amount, date);
    }
}
