package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DisplayExpenseGraphCommand extends Command {
    private static final Logger LOGGER = LoggerSetup.getLogger();
    private int year;

    public DisplayExpenseGraphCommand(int year) {this.year = year;}

    public static boolean isCommand(String command) {
        return command.startsWith("display monthly expenses");
    }

    @Override
    public void execute() {
        LOGGER.log(Level.INFO, "Displaying expense graph");
        ExpenseManager.displayExpensesOverMonthGraph(year);
    }
}
