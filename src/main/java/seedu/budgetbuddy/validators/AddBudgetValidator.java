package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.AddBudgetCommand;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
                amount = parseAmount(part);
                if (amount == -1) {
                    return new IncorrectCommand("Invalid amount format. Amount should be a positive number.");
                }
            } else if (part.startsWith("m/")) {
                date = parseDate(part);
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

    /**
     * Parses the amount from the command part.
     *
     * @param part The command part containing the amount.
     * @return The parsed amount or -1 if invalid.
     */
    private static double parseAmount(String part) {
        try {
            return Double.parseDouble(part.substring(2));
        } catch (NumberFormatException e) {
            return -1;  // Indicates invalid amount
        }
    }

    /**
     * Parses the date from the command part.
     *
     * @param part The command part containing the date.
     * @return The parsed YearMonth or null if invalid.
     */
    private static YearMonth parseDate(String part) {
        try {
            return YearMonth.parse(part.substring(2), DateTimeFormatter.ofPattern("MM/yyyy"));
        } catch (DateTimeParseException e) {
            return null;  // Indicates invalid date
        }
    }
}
