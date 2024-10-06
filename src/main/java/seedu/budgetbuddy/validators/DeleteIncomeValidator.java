package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.DeleteIncomeCommand;
import seedu.budgetbuddy.commands.IncorrectCommand;

/**
 * Validates the command for deleting an income.
 */
public class DeleteIncomeValidator {

    /**
     * Processes the delete income command and returns the appropriate command object.
     *
     * @param command The command string entered by the user.
     * @return A Command object representing the delete income action or an IncorrectCommand if the index is invalid.
     */
    public static Command processCommand(String command) {
        String trimmedCommand = command.substring("delete income ".length());
        int index = IncomeIndexValidator.validateIncomeIndex(trimmedCommand);
        if (index == -1) {
            return new IncorrectCommand("Invalid Index");
        }

        return new DeleteIncomeCommand(index - 1);
    }
}
