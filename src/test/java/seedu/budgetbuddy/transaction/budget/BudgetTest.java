package seedu.budgetbuddy.transaction.budget;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.YearMonth;

public class BudgetTest {

    @Test
    public void testAddAmount_inputIsDouble_increasesBudgetAmount() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 10));
        budget.addAmount(50.0);
        assertEquals(150.0, budget.getAmount());
    }

    @Test
    public void testDeductAmount_inputIsDouble_decreasesBudgetAmount() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 10));
        budget.deductAmount(30.0);
        assertEquals(70.0, budget.getAmount());
    }

    @Test
    public void testAddAmount_inputIsInt_increasesBudgetAmount() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 2));
        budget.addAmount(150);
        assertEquals(250.0, budget.getAmount());
    }

    @Test
    public void testDeductAmount_inputIsInt_decreasesBudgetAmount() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 2));
        budget.deductAmount(70);
        assertEquals(30.0, budget.getAmount());
    }

    @Test
    public void testDeductAmount_inputIsDouble_budgetBecomesZeroWhenFullyDeducted() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 10));
        budget.deductAmount(100.0);
        assertEquals(0.0, budget.getAmount());
    }

    @Test
    public void testDeductAmount_inputIsInt_budgetBecomesZeroWhenFullyDeducted() {
        Budget budget = new Budget(200.0, YearMonth.of(2024, 10));
        budget.deductAmount(200);
        assertEquals(0.0, budget.getAmount());
    }

    @Test
    public void testToString_returnsCorrectString() {
        Budget budget = new Budget(200.0, YearMonth.of(2024, 10));
        String result = budget.toString();
        assertEquals("Amount: 200.0  Date: 2024-10", result);
    }

    @Test
    public void testGetAmount_returnsCorrectAmount() {
        Budget budget = new Budget(250.0, YearMonth.of(2024, 9));
        assertEquals(250.0, budget.getAmount());
    }

    @Test
    public void testGetDate_returnsCorrectDate() {
        Budget budget = new Budget(250.0, YearMonth.of(2024, 9));
        assertEquals(YearMonth.of(2024, 9), budget.getDate());
    }
}
