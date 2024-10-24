package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

/**
 * Represents a command that lists all expenses in the expense manager.
 * This command retrieves and displays the current expenses for the user.
 */
public class ListExpenseCommand extends Command {

    /**
     * Checks if the provided command matches the command to list expenses.
     *
     * @param command The command to be checked.
     * @return True if the command matches "list expenses", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.equals("list expenses");
    }

    /**
     * Executes the command to list all expenses by invoking the ExpenseManager's method.
     */
    @Override
    public void execute() {
        ExpenseManager.listExpenses();
    }
}
