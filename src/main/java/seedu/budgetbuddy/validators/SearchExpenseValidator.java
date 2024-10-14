package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.SearchExpenseCommand;

public class SearchExpenseValidator {
    /**
     * Validates command input by user to see if string input has a keyword provided as a descriptor
     * @param command
     * @return new SearchExpenseCommand object.
     */
    public static Command processCommand(String command){
        if (command.equals("search")){
            return new SearchExpenseCommand();
        }
        String trimmedCommand = command.substring("search ".length());
        return new SearchExpenseCommand(trimmedCommand);
    }
}
