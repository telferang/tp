package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.DeleteIncomeCommand;
import seedu.budgetbuddy.commands.IncorrectCommand;

public class DeleteIncomeValidator {
    public static Command processCommand(String command) {

        String trimmedCommand = command.substring("delete income ".length());
        int index = IncomeIndexValidator.validateIncomeIndex(trimmedCommand);
        if (index == -1) {
            return new IncorrectCommand("Invalid Index");
        }

        return new DeleteIncomeCommand(index - 1);
    }
}
