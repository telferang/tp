package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.AddIncomeCommand;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Validates the command for adding an income.
 */
public class AddIncomeValidator {

    /**
     * Processes the given command to create a new income entry.
     *
     * @param command The command string to be processed.
     * @return A Command object representing the add income command
     *         or an IncorrectCommand if validation fails.
     */
    public static Command processCommand(String command) {
        if (command.equals("add income")) {
            return new IncorrectCommand("No description provided.");
        }

        String trimmedCommand = command.substring("add income ".length());
        String[] parts = trimmedCommand.split(" ");

        // Initialize default values
        String description = "";
        double amount = 0; // invalid amount initially
        LocalDate date = LocalDate.now();

        // Process parts to extract details
        for (String part : parts) {
            if (part.startsWith("a/")) {
                amount = parseAmount(part);
                if (amount == -1) {
                    return new IncorrectCommand("Invalid amount format. Amount should be a positive number.");
                }
            } else if (part.startsWith("d/")) {
                date = parseDate(part);
                if (date == null) {
                    return new IncorrectCommand("Invalid date format. Use d/dd/MM/yyyy.");
                }
            } else {
                description += part + " ";
            }
        }

        description = description.trim();

        // Validate description
        if (description.isEmpty()) {
            return new IncorrectCommand("Description cannot be empty.");
        }

        // Validate amount
        if (amount == 0) {
            return new IncorrectCommand("Amount not entered.");
        } else if (amount < 0) {
            return new IncorrectCommand("Amount must be a positive value.");
        }

        // All validations passed, return the command
        return new AddIncomeCommand(description, amount, date);
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
     * @return The parsed date or null if invalid.
     */
    private static LocalDate parseDate(String part) {
        try {
            return LocalDate.parse(part.substring(2), DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            return null;  // Indicates invalid date
        }
    }
}