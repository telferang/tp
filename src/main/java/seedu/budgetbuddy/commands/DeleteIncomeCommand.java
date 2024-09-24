package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.income.IncomeManager;

public class DeleteIncomeCommand extends Command {
    int index;

    public static boolean isCommand(String command) {
        return command.startsWith("delete income");
    }
    public DeleteIncomeCommand(int index) {
        this.index = index;
    }

    public void execute(){
        IncomeManager.deleteIncome(index);
    }
}
