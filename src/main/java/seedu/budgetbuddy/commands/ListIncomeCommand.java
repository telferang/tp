package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.transaction.income.IncomeManager;

/**
 * Represents a command that lists all incomes in the income manager.
 * This command retrieves and displays the current incomes for the user.
 */
public class ListIncomeCommand extends Command {

    /**
     * Checks if the provided command matches the command to list incomes.
     *
     * @param command The command to be checked.
     * @return True if the command matches "list incomes", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.equals("list incomes");
    }

    /**
     * Executes the command to list all incomes by invoking the IncomeManager's method.
     */
    @Override
    public void execute() {
        IncomeManager.listIncomes();
    }
}
