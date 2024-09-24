package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.expense.ExpenseManager;

public class ExpenseIndexValidator {
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
