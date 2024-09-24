package seedu.budgetbuddy;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.ExitCommand;

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
