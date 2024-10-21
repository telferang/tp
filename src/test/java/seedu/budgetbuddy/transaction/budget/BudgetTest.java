package seedu.budgetbuddy.transaction.budget;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.transaction.Category;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetTest {

    @Test
    public void testAddAmount_inputIsDouble_increasesBudgetTotalMonthlyBudget() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 10));
        budget.addAmount(Category.FOOD,50.0);
        assertEquals(150.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void testDeductAmount_inputIsDouble_decreasesBudgetTotalMonthlyBudget() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 10));
        budget.deductAmount(Category.OTHERS,30.0);
        assertEquals(70.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void testAddAmount_inputIsInt_increasesBudgetTotalMonthlyBudget() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 2));
        budget.addAmount(Category.ENTERTAINMENT,150);
        assertEquals(250.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void testDeductAmount_inputIsInt_decreasesBudgetTotalMonthlyBudget() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 2));
        budget.deductAmount(Category.TRANSPORT,70);
        assertEquals(30.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void testDeductAmount_inputIsDouble_budgetBecomesZeroWhenFullyDeducted() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 10));
        budget.deductAmount(Category.EDUCATION,100.0);
        assertEquals(0.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void testDeductAmount_inputIsInt_budgetBecomesZeroWhenFullyDeducted() {
        Budget budget = new Budget(200.0, YearMonth.of(2024, 10));
        budget.deductAmount(Category.FOOD,200);
        assertEquals(0.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void testToString_returnsCorrectString() {
        Budget budget = new Budget(200.0, YearMonth.of(2024, 10));
        String result = budget.toString();
        assertEquals("Amount: 200.0  Date: 2024-10", result);
    }

    @Test
    public void testGetAmount_returnsCorrectTotalMonthlyBudget() {
        Budget budget = new Budget(250.0, YearMonth.of(2024, 9));
        assertEquals(250.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void testGetDate_returnsCorrectDate() {
        Budget budget = new Budget(250.0, YearMonth.of(2024, 9));
        assertEquals(YearMonth.of(2024, 9), budget.getDate());
    }
}
