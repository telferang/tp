package seedu.budgetbuddy.commands.income;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;
import seedu.budgetbuddy.util.LoggerSetup;
import seedu.budgetbuddy.validators.income.EditIncomeValidator;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditIncomeCommand extends Command {

    private static final Logger LOGGER = LoggerSetup.getLogger();
    private static final double EMPTY_AMOUNT = -1.0;
    private static final LocalDate EMPTY_DATE = null;
    private static LocalDate date;
    private static double amount;
    private Income income;


    public EditIncomeCommand(Income income) {
        this.income = income;
    }

    /**
     * Checks if the provided command matches the command to edit incomes.
     *
     * @param command The command to be checked.
     * @return True if the command matches "edit incomes", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("edit incomes");
    }

    /**
     * Set date that will be used to update income
     *
     * @param newDate new Income Date
     */
    public static void setDate(LocalDate newDate) {
        date = newDate;
    }

    /**
     * Set amount that will be used to update income
     *
     * @param newAmount new Income Amount
     */
    public static void setAmount(double newAmount) {
        amount = newAmount;
    }

    /**
     * Execute Command to get fields to edit an income object
     * Ends Command if user input nothing
     * Shows Error Text if user input an invalid field
     * Else Edits the income based on input fields
     */
    public void execute() {
        String editFields = getEditFields();
        LOGGER.log(Level.INFO, "Successfully retrieve edit fields");
        Boolean validInput;
        if(editFields.isEmpty()) {
            return;
        }
        validInput = EditIncomeValidator.processSecondCommand(editFields);
        if(validInput) {
            processEdit();
        }
        LOGGER.log(Level.INFO, "Successfully edit income");
    }

    /**
     * Displays Custom Message that informs users to input edit fields
     * Then reads in user input.
     *
     * @return User input EditFields that will be used for editing Income {@code String}
     */
    public String getEditFields(){
        Ui.showMessage("Edit the following fields as follows: Amount: a/, Date: d/\n" +
                "Currently Editing Entry:\n" +
                income.toString());
        String editFields = Ui.getUserEditFields();
        return editFields;
    }

    /**
     * Finds the desired income from the list of incomes that will be updated
     * Saves Expense for future reference on successful find
     *
     * @param command User Input
     * @throws BudgetBuddyException
     */
    private void getIncome(String command) throws BudgetBuddyException {
        if (command.equals("edit incomes")) {
            throw new BudgetBuddyException("No index detected, try again with an index.");
        }
        try {
            String trimmedCommand = command.substring("edit incomes ".length());
            String[] parts = trimmedCommand.split(" ");
            int editIndex = Integer.parseInt(parts[0]) - 1;
            if (editIndex < 0) {
                throw new BudgetBuddyException("Edit index must be greater than 0.");
            }
            this.income = IncomeManager.getIncomeByIndex(editIndex);
        } catch (NumberFormatException e) {
            throw new BudgetBuddyException("Index must be a valid number larger than 0.");
        }

    }

    /**
     * Process which fields to edit based on values stored
     * For any field that is not left empty by user, it will update the Income object.
     */
    public void processEdit(){
        if(date != EMPTY_DATE) {
            income.editDate(date);
        }
        if(amount != EMPTY_AMOUNT) {
            income.editAmount(amount);
        }
        Ui.displayToUser("Edited Income:\n" + income.toString());
    }

}
