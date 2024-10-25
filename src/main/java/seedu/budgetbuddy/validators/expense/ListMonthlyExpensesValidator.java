package seedu.budgetbuddy.validators.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.expense.ListMonthlyExpensesCommand;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.budgetbuddy.validators.CategoryValidator.validateCategory;
import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

/**
 * A validator class for processing and validating the "list monthly expenses" command.
 * It extracts year and month information from the command, validates the input,
 * and optionally includes a category.
 */
public class ListMonthlyExpensesValidator {
    private static final Logger LOGGER = LoggerSetup.getLogger();

    /**
     * Processes the "list monthly expenses" command.
     * Validates the yearMonth and category (if provided) arguments.
     *
     * @param command The raw command string entered by the user.
     * @return A {@link Command} object, either a {@link ListMonthlyExpensesCommand}
     *     or an {@link IncorrectCommand} if validation fails.
     */
    public static Command processCommand(String command) {
        YearMonth yearMonth = null;
        Category category = null;

        LOGGER.log(Level.INFO, "Processing command: ", command);

        // Extracts the portion of the command after "list monthly expenses"
        String trimmedCommand = command.substring("list monthly expenses".length()).trim();
        if (trimmedCommand.isEmpty()) {
            LOGGER.log(Level.WARNING, "Trimmed command is empty");
            return new IncorrectCommand("Please provide a year.");
        }

        // Splits the command by spaces to get individual components
        String[] commandArgs = trimmedCommand.split(" ");

        // Parses each argument to check for date or category flags
        for (String arg : commandArgs) {
            if (arg.startsWith("m/")) {
                yearMonth = validateYearMonth(arg);
                if (yearMonth == null) {
                    LOGGER.log(Level.WARNING, "Invalid Date format for year and month");
                    return new IncorrectCommand("Invalid date format. Please use 'm/MM/YYYY'.");
                }
            } else if (arg.startsWith("c/")) {
                category = validateCategory(arg);
                if (category == null) {
                    LOGGER.log(Level.WARNING, "Invalid category format");
                    return new IncorrectCommand("Invalid category format. Please check the category.");
                }
            } else {
                LOGGER.log(Level.WARNING, "Invalid argument in command: " + arg);
                return new IncorrectCommand("Invalid command format.");
            }
        }

        // Checks if yearMonth is provided, as it's required for the command
        if (yearMonth == null) {
            return new IncorrectCommand("Invalid Date");
        } else {
            // Returns command with or without category based on input
            if (category == null) {
                return new ListMonthlyExpensesCommand(yearMonth);
            } else {
                return new ListMonthlyExpensesCommand(yearMonth, category);
            }
        }
    }
}
