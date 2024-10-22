package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.util.LoggerSetup;
import seedu.budgetbuddy.validators.expense.EditExpenseValidator;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.logging.Logger;

public class EditExpenseCommand extends Command {

    private static final Logger LOGGER = LoggerSetup.getLogger();
    private static Category category;
    private static LocalDate date;
    private Expense expense;
    private static double amount;

    /**
     * Checks if the provided command matches the command to list expenses.
     *
     * @param command The command to be checked.
     * @return True if the command matches "list expenses", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("edit expenses");
    }

    public EditExpenseCommand(String command) throws BudgetBuddyException {
        getExpense(command);
    }

    public static void setDate(LocalDate newDate) {
        date = newDate;
    }

    public static void setAmount(double newAmount) {
        amount = newAmount;
    }

    public static void setCategory(Category newCategory) {
        category = newCategory;
    }

    public void execute() {
        String editFields = getEditFields();
        Boolean validInput;
        if(editFields.isEmpty()) {
            return;
        }
        validInput = EditExpenseValidator.processCommand(editFields);
        if(validInput) {
            processEdit();
        }
    }

    public String getEditFields(){
        Ui.showMessage("Edit the following fields as follows: Amount: a/, Category: c/, Date: d/\n" +
                "Currently Editing Entry:\n" +
                expense.toString());
        String editFields = Ui.getUserEditFields();
        return editFields;
    }

    private void getExpense(String command) throws BudgetBuddyException {
        String[] split = command.split(" ",4);
        int editIndex = Integer.parseInt(split[2]) + 1;
        this.expense = ExpenseManager.getExpenseByIndex(editIndex);
    }

    private void processEdit(){
        if(category != Category.EMPTY) {
            expense.editCategory(category);
        }
        if(date != null) {
            expense.editDate(date);
        }
        if(amount != -1.0) {
            expense.editAmount(amount);
        }
        Ui.displayToUser("Edited Expense:\n" + expense.toString());
    }

}
