package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.AddBudgetCommand;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;

import java.time.YearMonth;

import static seedu.budgetbuddy.validators.AmountValidator.validateAmount;
import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

/**
 * Validates commands for adding budgets.
 */
public class AddBudgetValidator {

    public static Command processCommand(String command) {
        if (command.equals("add budget")) {
            return new IncorrectCommand("No description provided.");
        }

        String trimmedCommand = command.substring("add budget ".length());
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

        // All validations passed, return the command
        return new AddBudgetCommand(amount, date);
    }

}
