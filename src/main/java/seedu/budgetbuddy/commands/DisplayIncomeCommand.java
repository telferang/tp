package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.transaction.income.IncomeManager;

import java.time.YearMonth;

public class DisplayIncomeCommand extends Command {

    private YearMonth month;

    /**
     * Constructs a DisplayIncomeCommand with no specified month
     */
    public DisplayIncomeCommand() {
        this.month = null;
    }

    /**
     * Constructs a DisplayIncomeCommand with a valid month field
     * @param month
     */
    public DisplayIncomeCommand(YearMonth month) {
        this.month = month;
    }

    /**
     * Checks if the provided command matches the command to display income.
     *
     * @param command The command to be checked.
     * @return True if the command matches "display expenses", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("display incomes");
    }

    @Override
    public void execute() {
        if(month == null) {
            IncomeManager.listIncomes();
        }
        else{
            IncomeManager.displayIncomeWithMonth(month);
        }
    }
}
