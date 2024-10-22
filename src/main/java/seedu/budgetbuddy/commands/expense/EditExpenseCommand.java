package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.logging.Logger;

public class EditExpenseCommand extends Command {

    private static final Logger LOGGER = LoggerSetup.getLogger();
    private Category category;
    private LocalDate date;
    private Expense expense;
    private float amount;

    /**
     * Checks if the provided command matches the command to list expenses.
     *
     * @param command The command to be checked.
     * @return True if the command matches "list expenses", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("edit expenses");
    }

    public EditExpenseCommand(String command) {
        getExpense(command);
    }

    public void execute() {
        String editFields = getEditFields();
        if(editFields.isEmpty()) {
            //System.out.println("Empty edit parameter, exiting entry editor");
            return;
        }
        ExpenseManager.editExpense(editFields);
    }

    public String getEditFields(){
        Ui.showMessage("Edit the following fields as follows: Amount: a/, Category: c/, Date: d/\n" +
                "Currently Editing Entry: " +
                expense.toString());
        String editFields = Ui.getUserEditFields();
        return editFields;
    }

    private void getExpense(String command) {
        String[] split = command.split(" ",4);
        int editIndex = Integer.parseInt(split[2]);
        this.expense = ExpenseManager.getExpenseByIndex(editIndex);
    }

}
