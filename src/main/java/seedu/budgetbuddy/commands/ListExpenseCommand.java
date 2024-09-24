package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.expense.ExpenseManager;

public class ListExpenseCommand extends Command {
    public static boolean isCommand(String command) {
        return command.equals("list expenses");
    }

    public void execute() {
        ExpenseManager.listExpenses();
    }
}
