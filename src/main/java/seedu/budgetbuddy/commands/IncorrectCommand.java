package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.Ui;

public class IncorrectCommand extends Command {
    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public void execute () {
        Ui.displayToUser(feedbackToUser);
    }

}