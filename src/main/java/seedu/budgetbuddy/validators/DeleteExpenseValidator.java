package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.DeleteExpenseCommand;
import seedu.budgetbuddy.commands.IncorrectCommand;

public class DeleteExpenseValidator{
    public static Command processCommand(String command) {

        String trimmedCommand = command.substring("delete expense ".length());
        int index = ExpenseIndexValidator.validateExpenseIndex(trimmedCommand);
        if (index == -1) {
            return new IncorrectCommand("Invalid Index");
        }

        return new DeleteExpenseCommand(index - 1);
    }
}
