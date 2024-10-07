package seedu.budgetbuddy.transaction.budget;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.YearMonth;

public class BudgetTest {

    @Test
    public void testAddAmount_increasesBudgetAmount() {
        // Arrange: Set up a budget with an initial amount
        Budget budget = new Budget(100.0, YearMonth.of(2024, 10));

        // Act: Add an additional amount
        budget.addAmount(50.0);

        // Assert: Check if the amount was updated correctly
        assertEquals(150.0, budget.getAmount());
    }

    @Test
    public void testDeductAmount_decreasesBudgetAmount() {
        // Arrange: Set up a budget with an initial amount
        Budget budget = new Budget(100.0, YearMonth.of(2024, 10));

        // Act: Deduct an amount
        budget.deductAmount(30.0);

        // Assert: Check if the amount was deducted correctly
        assertEquals(70.0, budget.getAmount());
    }

    @Test
    public void testDeductAmount_budgetBecomesZeroWhenFullyDeducted() {
        // Arrange: Set up a budget with an initial amount
        Budget budget = new Budget(100.0, YearMonth.of(2024, 10));

        // Act: Deduct the full amount
        budget.deductAmount(100.0);

        // Assert: Check if the amount becomes zero
        assertEquals(0.0, budget.getAmount());
    }

    @Test
    public void testToString_returnsCorrectString() {
        // Arrange: Create a budget object
        Budget budget = new Budget(200.0, YearMonth.of(2024, 10));

        // Act: Get the string representation
        String result = budget.toString();

        // Assert: Check if the string is formatted correctly
        assertEquals("Amount: 200.0  Date: 2024-10", result);
    }

    @Test
    public void testGetAmount_returnsCorrectAmount() {
        // Arrange: Create a budget object
        Budget budget = new Budget(250.0, YearMonth.of(2024, 9));

        // Act & Assert: Verify the budget amount
        assertEquals(250.0, budget.getAmount());
    }

    @Test
    public void testGetDate_returnsCorrectDate() {
        // Arrange: Create a budget object
        Budget budget = new Budget(250.0, YearMonth.of(2024, 9));

        // Act & Assert: Verify the budget date
        assertEquals(YearMonth.of(2024, 9), budget.getDate());
    }
}
