package seedu.budgetbuddy;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.ExitCommand;

/**
 * The main class for the BudgetBuddy application.
 *
 * This class initiates the application, displaying a welcome message and continuously
 * processing user commands until the exit command is executed.
 */
public class BudgetBuddy {

    public static void main(String[] args) {
        Ui.displayWelcomeMessage();
        Command command;
        do {
            String userCommandText = Ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            command.execute();
        } while (!(command instanceof ExitCommand));
        System.exit(0);
    }
}
