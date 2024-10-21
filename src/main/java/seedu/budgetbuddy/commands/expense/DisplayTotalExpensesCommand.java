package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command for displaying total expenses for a specific year or month.
 */
public class DisplayTotalExpensesCommand extends Command {
    private static final Logger LOGGER = LoggerSetup.getLogger();
    private int year;
    private YearMonth month;

    /**
     * Constructs a command to display expenses for the specified year.
     *
     * @param year The year for which total expenses are to be displayed.
     */
    public DisplayTotalExpensesCommand(int year) {
        this.year = year;
    }

    /**
     * Constructs a command to display expenses for the specified year and month.
     *
     * @param year  The year for which total expenses are to be displayed.
     * @param month The YearMonth object representing the month for which total expenses are to be displayed.
     */
    public DisplayTotalExpensesCommand(int year, YearMonth month) {
        this.year = year;
        this.month = month;
    }

    /**
     * Checks if the given command matches the expected format for displaying monthly expenses.
     *
     * @param command The command string to check.
     * @return true if the command matches the expected format; false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("display monthly expenses");
    }

    /**
     * Executes the command to display either a graph of total expenses for the year
     * or the total expenses for the specified month.
     */
    @Override
    public void execute() {
        if (month == null) {
            LOGGER.log(Level.INFO, "Displaying expense graph");
            ExpenseManager.displayExpensesOverMonthGraph(year);
        } else {
            LOGGER.log(Level.INFO, "Displaying monthly expense");
            ExpenseManager.displayTotalExpensesForMonth(month);
        }
    }
}
