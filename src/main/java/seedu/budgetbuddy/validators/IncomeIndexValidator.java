package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.income.IncomeManager;

public class IncomeIndexValidator {
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
