package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.transaction.expense.Category;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

import java.time.LocalDate;
import java.time.YearMonth;

public class DisplayExpenseCommand extends Command {

    private Category category;
    private YearMonth date;

    public DisplayExpenseCommand(Category category, YearMonth date) {
        this.category = category;
        this.date = date;
    }

    public DisplayExpenseCommand(YearMonth date) {
        this.category = null;
        this.date = date;
    }

    public DisplayExpenseCommand(Category category) {
        this.category = category;
        this.date = null;
    }

    public DisplayExpenseCommand() {
        this.category = null;
        this.date = null;
    }

    /**
     * Checks if the provided command matches the command to list expenses.
     *
     * @param command The command to be checked.
     * @return True if the command matches "list expenses", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("display expense");
    }

    /**
     * Executes the command to list all expenses by invoking the ExpenseManager's method.
     */
    @Override
    public void execute() {
        if (category == null && date == null) {
            ExpenseManager.listExpenses();
        } else if (category == null){
            ExpenseManager.displayExpensesWithDate(date);
        } else if (date == null) {
            ExpenseManager.displayExpensesWithCategory(category);
        } else {
            ExpenseManager.displayExpensesWithCategoryAndDate(category,date);
        }
    }

}
