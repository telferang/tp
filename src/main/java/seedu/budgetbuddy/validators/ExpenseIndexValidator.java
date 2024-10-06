package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.transaction.expense.ExpenseManager;

/**
 * Validates the expense index input by the user.
 */
public class ExpenseIndexValidator {

    /**
     * Validates the given expense index.
     *
     * @param value The input string representing the expense index.
     * @return The validated index if it is a valid number within the range, or -1 if invalid.
     */
    public static int validateExpenseIndex(String value) {
        int index;
        try {
            index = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
        if (index >= 1 && index <= ExpenseManager.getNumberOfExpenses()) {
            return index;
        }
        return -1;
    }
}
