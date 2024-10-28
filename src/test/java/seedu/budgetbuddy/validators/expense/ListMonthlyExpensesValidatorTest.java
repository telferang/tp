package seedu.budgetbuddy.validators.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.expense.ListMonthlyExpensesCommand;
import seedu.budgetbuddy.transaction.Category;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListMonthlyExpensesValidatorTest {

    private String baseCommand;

    @BeforeEach
    void setUp() {
        // Set up the base command or any common variables used in tests
        baseCommand = "list monthly expenses";
    }

    @Test
    void testValidYearMonthInput() {
        Command command = ListMonthlyExpensesValidator.processCommand(baseCommand + " m/10/2023");
        assertTrue(command instanceof ListMonthlyExpensesCommand);
        assertEquals(YearMonth.of(2023, 10), ((ListMonthlyExpensesCommand) command).getYearMonth());
    }

    @Test
    void testValidYearMonthWithCategory() {
        Command command = ListMonthlyExpensesValidator.processCommand(baseCommand + " m/10/2023 c/Food");
        assertTrue(command instanceof ListMonthlyExpensesCommand);
        assertEquals(YearMonth.of(2023, 10), ((ListMonthlyExpensesCommand) command).getYearMonth());
        assertEquals(Category.FOOD, ((ListMonthlyExpensesCommand) command).getCategory());
    }

    @Test
    void testMissingYearMonthInput() {
        Command command = ListMonthlyExpensesValidator.processCommand(baseCommand);
        assertTrue(command instanceof IncorrectCommand);
        assertEquals("Please provide a year.", ((IncorrectCommand) command).getFeedbackToUser());
    }

    @Test
    void testInvalidYearMonthFormat() {
        Command command = ListMonthlyExpensesValidator.processCommand(baseCommand + " m/abc");
        assertTrue(command instanceof IncorrectCommand);
        assertEquals("Invalid date format. Please use 'm/MM/YYYY'.", ((IncorrectCommand) command).getFeedbackToUser());
    }

    @Test
    void testInvalidCategoryFormat() {
        Command command = ListMonthlyExpensesValidator.processCommand(baseCommand + " m/10/2023 c/invalidCategory");
        assertEquals(YearMonth.of(2023, 10), ((ListMonthlyExpensesCommand) command).getYearMonth());
        assertEquals(Category.OTHERS, ((ListMonthlyExpensesCommand) command).getCategory());
    }

    @Test
    void testInvalidCommandFormat() {
        Command command = ListMonthlyExpensesValidator.processCommand(baseCommand + " m/10/2023 x/extraArg");
        assertTrue(command instanceof IncorrectCommand);
        assertEquals("Invalid command format.", ((IncorrectCommand) command).getFeedbackToUser());
    }
}