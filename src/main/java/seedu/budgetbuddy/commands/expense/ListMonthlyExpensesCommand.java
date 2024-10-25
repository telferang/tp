package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.logging.Logger;

/**
 * Represents a command to list monthly expenses, optionally filtered by category.
 */
public class ListMonthlyExpensesCommand extends Command {
    private static final Logger LOGGER = LoggerSetup.getLogger();
    private YearMonth yearMonth;
    private Category category;

    /**
     * Constructs a {@code ListMonthlyExpensesCommand} for the specified year and month.
     *
     * @param yearMonth The year and month to list expenses for.
     */
    public ListMonthlyExpensesCommand(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    /**
     * Constructs a {@code ListMonthlyExpensesCommand} for the specified year, month, and category.
     *
     * @param yearMonth The year and month to list expenses for.
     * @param category The category to filter expenses by.
     */
    public ListMonthlyExpensesCommand(YearMonth yearMonth, Category category) {
        this.yearMonth = yearMonth;
        this.category = category;
    }

    /**
     * Checks if the command string starts with "list monthly expenses",
     * identifying it as a ListMonthlyExpensesCommand.
     *
     * @param command The command string to check.
     * @return True if the command is a ListMonthlyExpensesCommand, false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("list monthly expenses");
    }

    /**
     * Executes the command to list total expenses for the specified month.
     * If a category is provided, expenses are filtered by that category.
     */
    @Override
    public void execute() {
        if (category != null) {
            ExpenseManager.listTotalExpensesForMonthWithCategories(yearMonth, category);
        } else {
            ExpenseManager.listTotalExpensesForMonth(yearMonth);
        }
    }
}
