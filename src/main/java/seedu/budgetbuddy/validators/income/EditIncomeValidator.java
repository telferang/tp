package seedu.budgetbuddy.validators.income;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.income.EditIncomeCommand;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.LocalDate;
import java.util.logging.Logger;

import static seedu.budgetbuddy.validators.AmountValidator.validateAmount;
import static seedu.budgetbuddy.validators.DateValidator.validateDate;

public class EditIncomeValidator {

    private static final Logger LOGGER = LoggerSetup.getLogger();

    /**
     * Processes the command string to determine if it is valid for editing.
     * If valid, it updates the fields to be changed and returns a true for validity of command.
     * otherwise, it will display error message and return a false validity of command.
     *
     * @param command Input given by user
     * @return Validity of command {@code Boolean}
     */
    public static boolean processCommand(String command) {

        String[] parts = command.split(" ");

        //Process Initial Value
        LocalDate date = null;
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
            } else if (part.startsWith("a/")) {
                amount = validateAmount(part);
                if (amount < 0.0) {
                    Ui.displayToUser("Invalid amount format. Amount should be a positive number.");
                    return false;
                }
            }
        }

        if (date == null && amount == -1.0) {
            Ui.displayToUser("No valid fields detected, Exiting change menu.");
            return false;
        }

        //set Fields to be changed
        EditIncomeCommand.setDate(date);
        EditIncomeCommand.setAmount(amount);

        return true;
    }
}
