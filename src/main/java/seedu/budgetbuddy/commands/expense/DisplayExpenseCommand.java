package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.expense.Category;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

import java.time.YearMonth;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisplayExpenseCommand extends Command {

    private static Logger logger = Logger.getLogger(DisplayExpenseCommand.class.getName());
    private Category category;
    private YearMonth month;

    /**
     * Constructs a DisplayExpenseCommand with valid category and month field
     * @param category
     * @param month
     */
    public DisplayExpenseCommand(Category category, YearMonth month) {
        this.category = category;
        this.month = month;
    }

    /**
     * Constructs a DisplayExpenseCommand with valid month field
     * @param month
     */
    public DisplayExpenseCommand(YearMonth month) {
        this.category = null;
        this.month = month;
    }

    /**
     * Constructs a DisplayExpenseCommand with valid category field
     * @param category
     */
    public DisplayExpenseCommand(Category category) {
        this.category = category;
        this.month = null;
    }

    /**
     * Constructs a DisplayExpenseCommand with no specified date or category
     */
    public DisplayExpenseCommand() {
        this.category = null;
        this.month = null;
    }

    /**
     * Checks if the provided command matches the command to display expenses.
     *
     * @param command The command to be checked.
     * @return True if the command matches "display expenses", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("display expenses");
    }

    public Category getCategory() {
        return category;
    }

    public YearMonth getMonth() {
        return month;
    }

    /**
     * Executes the command to list all expenses by invoking the ExpenseManager's method.
     */
    @Override
    public void execute() {
        if (category == null && month == null) {
            logger.log(Level.INFO, "Displaying expenses listed with no Filter");
            ExpenseManager.listExpenses();
        } else if (category == null){
            logger.info("Displaying expenses listed in the Month: " + month);
            Ui.displayToUser(ExpenseManager.displayExpensesWithDate(month));
        } else if (month == null) {
            logger.info("Displaying expenses listed with Category: " + category);
            Ui.displayToUser(ExpenseManager.displayExpensesWithCategory(category));
        } else {
            logger.info("Displaying expenses listed with Category: " + category + " and Month: " + month);
            Ui.displayToUser(ExpenseManager.displayExpensesWithCategoryAndDate(category,month));
        }
    }

}
