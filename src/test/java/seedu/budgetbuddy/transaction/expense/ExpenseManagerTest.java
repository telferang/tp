package seedu.budgetbuddy.transaction.expense;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseManagerTest {

    private static int numberOfExpenses = 0;
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static ExpenseManager expenseManager;
    private static final String EMPTY_DISPLAY_STRING = "No expense entry with given parameters found, try again with a different parameter.";

    void initializeTestContent(){
        expenseManager = new ExpenseManager(expenses, numberOfExpenses);
        Expense newExpense = new Expense("New Food",
                12,
                LocalDate.parse("2024-02-12"),
                Category.FOOD);
        expenseManager.addExpense(newExpense);
    }

    String getExpectedString(){
        String result = "";
        int counter = 1;
        for (Expense expense : ExpenseManager.getExpenses()) {
            result += counter + ". " + expense.toString() + "\n";
            counter++;
        }
        return result;
    }

    @Test
    void displayExpensesWithCategoryAndDate_validMonthCategory_expectOneEntry() {
        initializeTestContent();
        Category category = Category.FOOD;
        YearMonth yearMonth = YearMonth.of(2024, 2);
        assertEquals(getExpectedString(),
                ExpenseManager.displayExpensesWithCategoryAndDate(category, yearMonth));
    }

    @Test
    void displayExpensesWithCategoryAndDate_incorrectCategory_expectEmptyDisplayString() {
        Category category = Category.TRANSPORT;
        YearMonth yearMonth = YearMonth.of(2024, 2);
        assertEquals(EMPTY_DISPLAY_STRING,
                ExpenseManager.displayExpensesWithCategoryAndDate(category, yearMonth));
    }

    @Test
    void displayExpensesWithCategoryAndDate_incorrectMonth_expectEmptyDisplayString() {
        Category category = Category.FOOD;
        YearMonth yearMonth = YearMonth.of(2024, 1);
        assertEquals(EMPTY_DISPLAY_STRING,
                ExpenseManager.displayExpensesWithCategoryAndDate(category, yearMonth));
    }
}