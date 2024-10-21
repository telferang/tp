package seedu.budgetbuddy.validators.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.expense.DisplayExpenseGraphCommand;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.logging.Logger;

public class DisplayExpenseGraphValidator {
    private static final Logger LOGGER = LoggerSetup.getLogger();

    public static Command processCommand(String command) {
        int year;
        YearMonth month = null;

        // Extract the portion of the command after "display monthly expenses"
        String trimmedCommand = command.substring("display monthly expenses".length()).trim();
        if (trimmedCommand.isEmpty()) {
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
                    return new IncorrectCommand("Month is missing after 'm/'. Please provide a valid month.");
                }
            } else{
                return new IncorrectCommand("Unknown command '" + trimmedCommand + "'.");
            }
        }

        // Validate and parse the year
        if (yearArg == null || !isValidYear(yearArg)) {
            return new IncorrectCommand("Invalid or missing year format.");
        }
        year = Integer.parseInt(yearArg);

        // If a month is provided, validate and parse it
        if (monthArg != null) {
            if (!isValidMonth(monthArg)) {
                return new IncorrectCommand("Invalid month format.");
            }
            month = YearMonth.of(year, Integer.parseInt(monthArg));
            return new DisplayExpenseGraphCommand(year, month);
        }

        // If only the year is provided, proceed with that
        return new DisplayExpenseGraphCommand(year);
    }

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

