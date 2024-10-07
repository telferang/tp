package seedu.budgetbuddy.transaction.budget;

import seedu.budgetbuddy.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.YearMonth;
import java.util.ArrayList;

public class BudgetManagerTest {

    @BeforeEach
    public void setUp() {
        // Reset the static state of BudgetManager before each test
        BudgetManager.reset();
    }

    @Test
    public void testAddBudget_incrementsNumberOfBudgets() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 10));
        BudgetManager.addBudget(budget);
        assertEquals(1, BudgetManager.getNumberOfBudgets());
    }

    @Test
    public void testAddBudget_addsBudgetToList() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 10));
        BudgetManager.addBudget(budget);
        assertEquals(budget, BudgetManager.getBudgets().get(0));
    }

    @Test
    public void testDeleteBudget_decrementsNumberOfBudgets() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 10));
        BudgetManager.addBudget(budget);
        BudgetManager.deleteBudget(budget);
        assertEquals(0, BudgetManager.getNumberOfBudgets());
    }

    @Test
    public void testGetBudget_returnsCorrectBudget() {
        Budget budget = new Budget(100.0, YearMonth.of(2024, 10));
        BudgetManager.addBudget(budget);
        Budget result = BudgetManager.getBudget(YearMonth.of(2024, 10));
        assertEquals(budget, result);
    }

    @Test
    public void testGetBudget_returnsNullForNonExistentBudget() {
        Budget result = BudgetManager.getBudget(YearMonth.of(2024, 10));
        assertNull(result);
    }
}
