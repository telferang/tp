package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.income.IncomeManager;

public class ListIncomeCommand extends Command {
    public static boolean isCommand(String command) {
        return command.equals("list incomes");
    }

    public void execute() {
        IncomeManager.listIncomes();
    }
}
