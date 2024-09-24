package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.expense.ExpenseManager;

public class ExitCommand extends Command {

    public ExitCommand() {

    }

    public static boolean isCommand(String command) {
        return command.equals("bye");
    }

    public void execute() {
        Ui.displayExitMessage();
    }
}
