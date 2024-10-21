package seedu.budgetbuddy.validators.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.expense.DisplayExpenseGraphCommand;
import seedu.budgetbuddy.util.LoggerSetup;

import java.util.logging.Logger;

public class DisplayExpenseGraphValidator {
    private static final Logger LOGGER = LoggerSetup.getLogger();

    public static Command processCommand (String command) {
        String trimmedCommand = command.substring("display monthly expenses".length()).trim();
        if(trimmedCommand.isEmpty()) {
            return new IncorrectCommand("Please Provide a Year.");
        }
        if (isValidYear(trimmedCommand)) {
            int year = Integer.parseInt(trimmedCommand);
            return new DisplayExpenseGraphCommand(year);
        }
        return new IncorrectCommand("Your Year Format is Invalid.");
    }

    private static boolean isValidYear (String year) {
        if (year.length() != 4){
            return false;
        }
        try {
            int yearInt = Integer.parseInt(year);
            return yearInt >= 1900 && yearInt <= 2100;
        }catch (NumberFormatException e) {
            return false;
        }
    }
}
