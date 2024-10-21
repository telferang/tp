package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisplayExpenseGraphCommand extends Command {
    private static final Logger LOGGER = LoggerSetup.getLogger();
    private int year;
    private YearMonth month;

    public DisplayExpenseGraphCommand(int year) {this.year = year;}

    public DisplayExpenseGraphCommand(int year, YearMonth month) {
        this.year = year;
        this.month = month;
    }

    public static boolean isCommand(String command) {
        return command.startsWith("display monthly expenses");
    }

    @Override
    public void execute() {
        if(month == null) {
            LOGGER.log(Level.INFO, "Displaying expense graph");
            ExpenseManager.displayExpensesOverMonthGraph(year);
        }else{
            LOGGER.log(Level.INFO, "Displaying monthly expense");
            ExpenseManager.displayTotalExpensesForMonth(month);
        }
    }
}
