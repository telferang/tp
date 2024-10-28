package seedu.budgetbuddy.transaction.expense;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.transaction.Category;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseManagerTest {

    private static int numberOfExpenses = 0;
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static ExpenseManager expenseManager;
    private static final String EMPTY_DISPLAY_STRING =
            "No expense entry with given parameters found, try again with a different parameter.";

    void initializeTestContent(){
        expenseManager = new ExpenseManager(expenses, numberOfExpenses);
        expenseManager.reset();
        Expense newExpense = new Expense("New Food",
                12,
                LocalDate.parse("2024-02-12"),
                Category.FOOD);
        expenseManager.addExpense(newExpense);
    }
    void initializeEmptyTestContent(){
        expenseManager = new ExpenseManager(new ArrayList<>(), numberOfExpenses);
    }

    String getExpectedString(){
        String result = "";
        int counter = 1;
        for (Expense expense : ExpenseManager.getExpenses()) {
            result += counter + ". " + expense.toString() + "\n";
            counter++;
        }
        result += "Your total expenses for FOOD in February 2024 is $12.0";
        return result;
    }

    @Test
    void displayExpensesWithCategoryAndDate_validMonthCategory_expectOneEntry() {
        initializeTestContent();
        Category category = Category.FOOD;
        YearMonth yearMonth = YearMonth.of(2024, 2);
        assertEquals(getExpectedString(),
                ExpenseManager.listExpensesWithCategoryAndDate(category, yearMonth));
    }

    @Test
    void displayExpensesWithCategoryAndDate_incorrectCategory_expectEmptyDisplayString() {
        Category category = Category.TRANSPORT;
        YearMonth yearMonth = YearMonth.of(2024, 2);
        assertEquals(EMPTY_DISPLAY_STRING,
                ExpenseManager.listExpensesWithCategoryAndDate(category, yearMonth));
    }

    @Test
    void displayExpensesWithCategoryAndDate_incorrectMonth_expectEmptyDisplayString() {
        Category category = Category.FOOD;
        YearMonth yearMonth = YearMonth.of(2024, 1);
        assertEquals(EMPTY_DISPLAY_STRING,
                ExpenseManager.listExpensesWithCategoryAndDate(category, yearMonth));
    }

    @Test
    void searchExpense_noDescriptor_expectEmptyDisplayString(){
        initializeTestContent();
        assertEquals(EMPTY_DISPLAY_STRING, ExpenseManager.searchExpenses(""));
    }

    @Test
    void searchExpense_descriptorProvidedNoFoundExpense_expectEmptyDisplayString(){
        initializeTestContent();
        assertEquals(EMPTY_DISPLAY_STRING, ExpenseManager.searchExpenses("RANDOMMESSAGE"));
    }

    @Test
    void searchExpense_foundExpense_expectOutput(){
        initializeTestContent();
        assertEquals("1. Description: New Food  Amount: 12.0  Date: 2024-02-12  Category: FOOD\n",
                ExpenseManager.searchExpenses("New"));
    }

    @Test
    void breakdownExpensesByCategory_noExpenses_expectNoExpensesMessage(){
        initializeEmptyTestContent();
        assertEquals("Total expenses: 0. You have not indicated any expense yet.",
                ExpenseManager.breakdownExpensesByCategory());
    }

}
