package seedu.budgetbuddy.validators.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.expense.DisplayExpenseCommand;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.logging.Logger;

import static seedu.budgetbuddy.validators.CategoryValidator.validateCategory;
import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

public class DisplayExpenseValidator{

    private static final Logger LOGGER = LoggerSetup.getLogger();

    /**
     * Processes the command string to determine if it is valid for displaying expenses.
     * If valid, it returns a DisplayExpenseCommand with the parsed date.
     *
     * @param command
     * @return
     */
    public static Command processCommand(String command) {
        if (command.equals("display expenses")){
            return new DisplayExpenseCommand();
        }

        String trimmedCommand = command.substring("display expenses ".length());
        String[] parts = trimmedCommand.split(" ");

        //Process Initial Value
        YearMonth month = null;
        Category category = null;

        //Process parts to extract details
        for (String part : parts) {
            if (part.startsWith("m/")) {
                month = validateYearMonth(part);
                if (month == null) {
                    LOGGER.warning("Invalid month format. Month found: " + part);
                    return new IncorrectCommand("Invalid month format. Use m/MM/yyyy.");
                }
            } else if (part.startsWith("c/")) {
                category = validateCategory(part);
                if (category == null) {
                    LOGGER.warning("Invalid Category. Category found: " + part);
                    return new IncorrectCommand("Unknown category. Use a valid category");
                }
            }
        }

        //Check of Display Type
        return checkDisplayType(category, month);
    }

    /**
     * Checks the value of category and date and returns the corresponding
     * DisplayExpenseCommand type
     * @param category
     * @param date
     * @return
     */
    public static Command checkDisplayType(Category category, YearMonth date) {
        if (category != null && date == null) {
            return new DisplayExpenseCommand(category);
        } else if (category == null && date != null) {
            return new DisplayExpenseCommand(date);
        } else{
            return new DisplayExpenseCommand(category, date);
        }
    }
}
