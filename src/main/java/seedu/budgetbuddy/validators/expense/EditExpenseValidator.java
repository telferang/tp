package seedu.budgetbuddy.validators.expense;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.expense.DisplayExpenseCommand;
import seedu.budgetbuddy.commands.expense.EditExpenseCommand;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.logging.Logger;

import static seedu.budgetbuddy.validators.AmountValidator.validateAmount;
import static seedu.budgetbuddy.validators.CategoryValidator.validateCategory;
import static seedu.budgetbuddy.validators.DateValidator.validateDate;

public class EditExpenseValidator {

    private static final Logger LOGGER = LoggerSetup.getLogger();

    /**
     * Processes the command string to determine if it is valid for editing.
     * If valid, it updates the fields to be changed and returns a true for validity of command.
     * otherwise, it will display error message and return a false validity of command.
     * For any invalid category input, it will be set to others by default
     *
     * @param command
     * @return boolean object that checks for validity of command
     */
    public static boolean processCommand(String command){

        String[] parts = command.split(" ");

        //Process Initial Value
        LocalDate date = null;
        Category category = Category.EMPTY;
        double amount = -1.0;

        //Process parts to extract details
        for (String part : parts) {
            if (part.startsWith("d/")) {
                date = validateDate(part);
                if (date == null) {
                    LOGGER.warning("Invalid date format. Date found: " + part);
                    Ui.displayToUser("Invalid month format. Use d/dd/mm/yyyy.");
                    return false;
                }
            } else if (part.startsWith("c/")) {
                category = validateCategory(part);
            } else if (part.startsWith("a/")) {
                amount = validateAmount(part);
                if (amount < 0.0) {
                    Ui.displayToUser("Invalid amount format. Amount should be a positive number.");
                    return false;
                }
            }
        }

        //set Fields to be changed
        EditExpenseCommand.setDate(date);
        EditExpenseCommand.setCategory(category);
        EditExpenseCommand.setAmount(amount);

        return true;
    }
}
