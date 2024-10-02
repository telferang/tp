package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.income.IncomeManager;

/**
 * Validates the income index input by the user.
 */
public class IncomeIndexValidator {

    /**
     * Validates the given income index.
     *
     * @param value The input string representing the income index.
     * @return The validated index if it is a valid number within the range, or -1 if invalid.
     */
    public static int validateIncomeIndex(String value) {
        int index;
        try {
            index = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
        if (index >= 1 && index <= IncomeManager.getNumberOfIncomes()) {
            return index;
        }
        return -1;
    }
}
