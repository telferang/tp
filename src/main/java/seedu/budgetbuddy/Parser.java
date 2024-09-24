package seedu.budgetbuddy;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.AddExpenseCommand;
import seedu.budgetbuddy.commands.DeleteExpenseCommand;
import seedu.budgetbuddy.commands.ListExpenseCommand;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.AddIncomeCommand;
import seedu.budgetbuddy.commands.DeleteIncomeCommand;
import seedu.budgetbuddy.commands.ListIncomeCommand;
import seedu.budgetbuddy.commands.ExitCommand;
import seedu.budgetbuddy.validators.AddExpenseValidator;
import seedu.budgetbuddy.validators.AddIncomeValidator;
import seedu.budgetbuddy.validators.DeleteExpenseValidator;
import seedu.budgetbuddy.validators.DeleteIncomeValidator;


public class Parser {
    public Command parseCommand(String userCommandText) {
        if (AddExpenseCommand.isCommand(userCommandText)) {
            return AddExpenseValidator.processCommand(userCommandText);
        }
        if (DeleteExpenseCommand.isCommand(userCommandText)) {
            return DeleteExpenseValidator.processCommand(userCommandText);
        }
        if (ListExpenseCommand.isCommand(userCommandText)) {
            return new ListExpenseCommand();
        }
        if (AddIncomeCommand.isCommand(userCommandText)) {
            return AddIncomeValidator.processCommand(userCommandText);
        }
        if (DeleteIncomeCommand.isCommand(userCommandText)) {
            return DeleteIncomeValidator.processCommand(userCommandText);
        }
        if (ListIncomeCommand.isCommand(userCommandText)) {
            return new ListIncomeCommand();
        }
        if (ExitCommand.isCommand(userCommandText)) {
            return new ExitCommand();
        }
        return new IncorrectCommand("Invalid input");
    }

}
