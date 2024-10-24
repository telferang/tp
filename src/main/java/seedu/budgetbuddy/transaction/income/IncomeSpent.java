package seedu.budgetbuddy.transaction.income;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

import java.time.YearMonth;

/**
 * The IncomeSpent class provides functionality to calculate and display
 * the percentage of income spent for a specified month.
 */
public class IncomeSpent {

    /**
     * Calculates the percentage of income spent for a specified month.
     *
     * @param month The month for which the spending percentage is calculated.
     * @return The percentage of income spent for the month, as a {@code double}.
     * @throws BudgetBuddyException If no income is recorded for the specified month.
     */
    public static double calculateSpentPercentage(YearMonth month) throws BudgetBuddyException {
        double monthlyIncome = IncomeManager.getMonthlyIncome(month);
        if (monthlyIncome <= 0) {
            throw new BudgetBuddyException("No income recorded for the specified month.");
        }
        double monthlyExpense = ExpenseManager.getMonthlyExpense(month);
        double monthlyIncomeSpent = monthlyIncome - monthlyExpense;
        return (monthlyIncomeSpent / monthlyIncome) * 100;
    }

    /**
     * Returns a formatted string representing the percentage of income spent
     * for a specified month.
     *
     * @param month The month to format the spending percentage for.
     * @return A string displaying the percentage of income spent for the month.
     */
    public static String toString(YearMonth month) {
        try {
            String formattedPercentage = String.format("%.1f", calculateSpentPercentage(month));
            return String.format("Percentage of income spent for %s: %s%%", month, formattedPercentage);
        } catch (BudgetBuddyException e) {
            return e.getMessage();
        }
    }

    /**
     * Displays the percentage of income spent for a specified month to the user.
     *
     * @param month The month for which to display the spending percentage.
     */
    public static void displaySpentPercentage(YearMonth month) {
        Ui.displayToUser(toString(month));
    }
}
