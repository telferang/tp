package seedu.budgetbuddy.validators.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.expense.DisplayTotalExpensesCommand;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A validator class for processing and validating the "display monthly expenses" command.
 * It extracts year and month information from the command and validates the input.
 */
public class DisplayTotalExpensesValidator {
    private static final Logger LOGGER = LoggerSetup.getLogger();
    /**
     * Processes the command input, validates the year and month (if provided), and returns a corresponding command.
     *
     * @param command The raw command string entered by the user.
     * @return A {@link Command} object, either a {@link DisplayTotalExpensesCommand} or an {@link IncorrectCommand} if input is invalid.
     */
    public static Command processCommand(String command) {
        int year;
        YearMonth month = null;

        LOGGER.log(Level.INFO, "Processing command: " + command);

        // Extract the portion of the command after "display monthly expenses"
        String trimmedCommand = command.substring("display monthly expenses".length()).trim();
        if (trimmedCommand.isEmpty()) {
            LOGGER.log(Level.WARNING, "Trimmed command is empty");
            return new IncorrectCommand("Please provide a year.");
        }

        // Split the command by spaces to get individual components
        String[] commandArgs = trimmedCommand.split(" ");

        String yearArg = null;
        String monthArg = null;

        // Check if the first argument starts with "y/" and the second one with "m/"
        for (String arg : commandArgs) {
            if (arg.startsWith("y/")) {
                yearArg = arg.substring(2); // Remove the "y/" prefix
            } else if (arg.startsWith("m/")) {
                if (arg.length() > 2) {
                    monthArg = arg.substring(2); // Remove the "m/" prefix
                } else {
                    LOGGER.log(Level.WARNING, "Invalid month argument: " + arg);
                    return new IncorrectCommand("Month is missing after 'm/'. Please provide a valid month.");
                }
            } else {
                LOGGER.log(Level.WARNING, "Invalid Command: " + arg);
                return new IncorrectCommand("Unknown command '" + trimmedCommand + "'.");
            }
        }

        // Validate and parse the year
        if (yearArg == null || !isValidYear(yearArg)) {
            LOGGER.log(Level.WARNING, "Invalid year: " + yearArg);
            return new IncorrectCommand("Invalid or missing year format.");
        }
        year = Integer.parseInt(yearArg);

        // If a month is provided, validate and parse it
        if (monthArg != null) {
            if (!isValidMonth(monthArg)) {
                LOGGER.log(Level.WARNING, "Invalid month: " + monthArg);
                return new IncorrectCommand("Invalid month format.");
            }
            month = YearMonth.of(year, Integer.parseInt(monthArg));
            return new DisplayTotalExpensesCommand(year, month);
        }

        // If only the year is provided, proceed with that
        return new DisplayTotalExpensesCommand(year);
    }

    /**
     * Validates the month input to ensure it's a valid two-digit month (01 to 12).
     *
     * @param month The month string to validate.
     * @return True if the month is valid, false otherwise.
     */
    private static boolean isValidMonth(String month) {
        // Return false if the month string is empty or has invalid length
        if (month.length() != 2) {
            return false;
        }
        try {
            int monthInt = Integer.parseInt(month);
            return monthInt >= 1 && monthInt <= 12;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates the year input to ensure it consists of four digits and falls within the valid range (1900 to 2100).
     *
     * @param year The year string to validate.
     * @return True if the year is valid, false otherwise.
     */
    private static boolean isValidYear(String year) {
        if (year.length() != 4) {
            return false;
        }
        try {
            int yearInt = Integer.parseInt(year);
            return yearInt >= 1900 && yearInt <= 2100;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

