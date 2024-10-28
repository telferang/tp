package seedu.budgetbuddy.transaction.saving;

import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * Displays the savings of the user.
 * The user can specify if they wish to see their total savings by month or in total.
 */
public class SavingsManager {
    private static final Logger LOGGER = LoggerSetup.getLogger();

    /**
     * Displays the total savings of the user based on all their incomes and expenses.
     * Total Savings = Total Income - Total Expenses
     */
    public static String displayTotalSavings(){
        double savings = 0;
        YearMonth firstIncome = YearMonth.now();
        YearMonth firstExpense = YearMonth.now();
        String result = "";
        if (IncomeManager.getIncomes().size() > 0){
            for (Income income: IncomeManager.getIncomes()){
                savings += income.getAmount();
                YearMonth incomeYearMonth = IncomeManager.getYearMonthFromDate(income.getDate());
                if (incomeYearMonth.compareTo(firstIncome) == -1){
                    firstIncome = incomeYearMonth;
                }
            }
        }

        if (ExpenseManager.getExpenses().size() > 0){
            for (Expense expense: ExpenseManager.getExpenses()){
                savings -= expense.getAmount();
                YearMonth expenseYearMonth = ExpenseManager.getYearMonthFromDate(expense.getDate());
                if (expenseYearMonth.compareTo(firstExpense) == -1){
                    firstExpense = expenseYearMonth;
                }
            }
        }
        result += "Total savings: " + savings + "\n";
        if (IncomeManager.getIncomes().size() > 0){
            result += "First income: " + firstIncome + "\n";
        }
        if (ExpenseManager.getExpenses().size() > 0){
            result += "First expense: " +  firstExpense + "\n";
        }
        LOGGER.info("Listing total savings");
        return result;
    }

    /**
     * Displays the savings by month in chronological order based on their monthly expenses and incomes.
     * Monthly savings = Monthly Income - Monthly Expense
     */
    public static String displayTotalSavingsByMonth(){
        String result = "";
        ArrayList<YearMonth> listYearMonths = new ArrayList<>();
        ArrayList<Saving> savings = new ArrayList<>();
        if (IncomeManager.getIncomes().size() > 0){
            for (Income income: IncomeManager.getIncomes()){
                YearMonth incomeYearMonth = IncomeManager.getYearMonthFromDate(income.getDate());
                int indexOfSaving = findYearMonthFromArray(listYearMonths, incomeYearMonth);
                if (indexOfSaving == -1){
                    listYearMonths.add(incomeYearMonth);
                    savings.add(new Saving(incomeYearMonth, income.getAmount()));
                } else{
                    savings.get(indexOfSaving).addIncome(income.getAmount());
                }
            }
        }

        if (ExpenseManager.getExpenses().size() > 0) {
            for (Expense expense : ExpenseManager.getExpenses()) {
                YearMonth expenseYearMonth = ExpenseManager.getYearMonthFromDate(expense.getDate());
                int indexOfSaving = findYearMonthFromArray(listYearMonths, expenseYearMonth);
                if (indexOfSaving == -1) {
                    listYearMonths.add(expenseYearMonth);
                    savings.add(new Saving(expenseYearMonth, -expense.getAmount()));
                } else {
                    savings.get(indexOfSaving).deductExpense(expense.getAmount());
                }
            }
        }
        if (savings.size() == 0){
            result = "Total savings: 0\n";
            return result;
        }
        savings.sort(Comparator.comparing(Saving::getYearMonth));
        for (Saving saving: savings){
            result += "Savings in " + saving.getYearMonth() +": " + saving.getSavings() + "\n";
        }
        LOGGER.info("Displaying savings by month");
        return result;
    }

    /**
     * Helper method to get the index of a specified YearMonth in the ArrayList to update savings.
     * @param listYearMonth ArrayList containing all YearMonths of Saving objects.
     * @param yearMonth Specific YearMonth to find in the ArrayList
     * @return index of the YearMonth in the ArrayList, otherwise -1.
     */
    private static int findYearMonthFromArray(ArrayList<YearMonth> listYearMonth, YearMonth yearMonth){
        if (!listYearMonth.contains(yearMonth)){
            return -1;
        } else{
            return listYearMonth.indexOf(yearMonth);
        }
    }
}
