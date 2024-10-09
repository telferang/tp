package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.DisplayIncomeCommand;
import seedu.budgetbuddy.commands.IncorrectCommand;

import java.time.YearMonth;

import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

public class DisplayIncomeValidator {

    /**
     * Processes the command string to determine if it is valid for displaying income.
     * If valid, it returns a DisplayIncomeCommand with the parsed date.
     *
     * @param command
     * @return
     */
    public static Command processCommand(String command) {
        if (command.equals("display income")){
            return new DisplayIncomeCommand();
        }

        String trimmedCommand = command.substring("display income ".length());
        String[] parts = trimmedCommand.split(" ");

        //Process Initial Value
        YearMonth month = null;

        //Process parts to extract details
        for (String part : parts) {
            if (part.startsWith("m/")) {
                month = validateYearMonth(part);
                if (month == null) {
                    return new IncorrectCommand("Invalid month format. Use m/MM/yyyy.");
                }
            }
        }

        return new DisplayIncomeCommand(month);
    }

}
