package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.AddBudgetCommand;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;

import java.time.YearMonth;
import java.util.logging.Logger;

import static seedu.budgetbuddy.validators.AmountValidator.validateAmount;
import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

/**
 * Validates commands for adding budgets.
 */
public class AddBudgetValidator {
    private static final Logger logger = Logger.getLogger(AddBudgetValidator.class.getName());

    public static Command processCommand(String command) {
        assert command != null : "Command cannot be null";

        if (command.equals("add budget")) {
            logger.warning("Attempted to add budget without description.");
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
                    return new IncorrectCommand("Invalid amount format. " +
                            "Amount should be a positive number.");
                }
            } else if (part.startsWith("m/")) {
                date = validateYearMonth(part);
            }
        }

        // Validate amount
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount: " + amount + ". Amount must be a positive value.");
        }

        // Validate date
        if (date == null) {
            throw new IllegalArgumentException("Invalid date format. Use m/MM/yyyy.");
        }

        // All validations passed, return the command
        return new AddBudgetCommand(amount, date);
    }

}
