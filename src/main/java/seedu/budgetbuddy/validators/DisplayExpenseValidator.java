package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.DisplayExpenseCommand;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.transaction.expense.Category;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

import javax.xml.validation.Validator;
import java.time.LocalDate;
import java.time.YearMonth;

import static seedu.budgetbuddy.validators.AmountValidator.validateAmount;
import static seedu.budgetbuddy.validators.DateValidator.validateDate;
import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

public class DisplayExpenseValidator{

    public static Command processCommand(String command) {
        if (command.equals("display expense")){
            return new DisplayExpenseCommand();
        }

        String trimmedCommand = command.substring("display expense ".length());
        String[] parts = trimmedCommand.split(" ");

        //Process Initial Value
        YearMonth month = null;
        Category category = null;

        //Process parts to extract details
        for (String part : parts) {
            if (part.startsWith("m/")) {
                month = validateYearMonth(part);
                if (month == null) {
                    return new IncorrectCommand("Invalid month format. Use m/MM/yyyy.");
                }
            } else if (part.startsWith("c/")) {
                category = parseCategory(part);
                if (category == null) {
                    return new IncorrectCommand("Unknown category. Use a valid category");
                }
            }
        }

        //Check of Display Type
        return checkDisplayType(category, month);
    }

    public static Command checkDisplayType(Category category, YearMonth date) {
        if (category != null && date == null) {
            return new DisplayExpenseCommand(category);
        } else if (category == null && date != null) {
            return new DisplayExpenseCommand(date);
        } else{
            return new DisplayExpenseCommand(category, date);
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
            return null;  // if invalid category search applied
        }
    }

}
