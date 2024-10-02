package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.AddExpenseCommand;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.expense.Category;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddExpenseValidator {

    /**
     * Processes the given command to create a new expense entry.
     *
     * @param command The command string to be processed.
     * @return A Command object representing the add expense command
     *         or an IncorrectCommand if validation fails.
     */
    public static Command processCommand(String command) {
        if (command.equals("add expense")) {
            return new IncorrectCommand("No description provided.");
        }

        String trimmedCommand = command.substring("add expense ".length());
        String[] parts = trimmedCommand.split(" ");

        // Initialize default values
        String description = "";
        double amount = 0; // invalid amount initially
        LocalDate date = LocalDate.now();
        Category category = Category.OTHERS;

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
            } else if (part.startsWith("c/")) {
                category = parseCategory(part);
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
        return new AddExpenseCommand(description, amount, date, category);
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

    /**
     * Parses the category from the command part.
     *
     * @param part The command part containing the category.
     * @return The parsed category or OTHERS if invalid.
     */
    private static Category parseCategory(String part) {
        String categoryStr = part.substring(2).toUpperCase();
        try {
            return Category.valueOf(categoryStr);
        } catch (IllegalArgumentException e) {
            return Category.OTHERS;  // Default category if invalid
        }
    }
}
