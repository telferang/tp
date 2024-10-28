package seedu.budgetbuddy.validators.income;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.income.AddIncomeCommand;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AddIncomeValidatorTest {

    @Test
    void testNoDescriptionProvided() {
        Command result = AddIncomeValidator.processCommand("add income");
        assertTrue(result instanceof IncorrectCommand);
        assertEquals("No description provided.", ((IncorrectCommand) result).getFeedbackToUser());
    }

    @Test
    void testValidCommand() {
        // Assuming "Lunch income" is a valid description, 50.5 as a valid amount, and today's date as valid
        String command = "add income Lunch income a/50.5 d/25/10/2024";
        Command result = AddIncomeValidator.processCommand(command);

        assertTrue(result instanceof AddIncomeCommand, "Expected AddIncomeCommand instance");
        AddIncomeCommand addIncomeCommand = (AddIncomeCommand) result;

        assertEquals("Lunch income", addIncomeCommand.getDescription(), "Expected description to " +
                "match");
        assertEquals(50.5, addIncomeCommand.getAmount(), "Expected amount to match");
        assertEquals(LocalDate.of(2024, 10, 25), addIncomeCommand.getDate(),
                "Expected date to match");
    }

    @Test
    void testMissingAmount() {
        String command = "add income Salary d/25/10/2024";
        Command result = AddIncomeValidator.processCommand(command);

        assertTrue(result instanceof IncorrectCommand);
        assertEquals("Amount not entered.", ((IncorrectCommand) result).getFeedbackToUser());
    }

    @Test
    void testInvalidAmountFormat() {
        String command = "add income Bonus a/abc d/25/10/2024";
        Command result = AddIncomeValidator.processCommand(command);

        assertTrue(result instanceof IncorrectCommand);
        assertEquals("Invalid amount format. Amount should be a positive number.", ((IncorrectCommand) result)
                .getFeedbackToUser());
    }

    @Test
    void testMissingDate() {
        String command = "add income Freelance job a/100";
        Command result = AddIncomeValidator.processCommand(command);

        assertTrue(result instanceof AddIncomeCommand);
        AddIncomeCommand addIncomeCommand = (AddIncomeCommand) result;

        assertEquals("Freelance job", addIncomeCommand.getDescription());
        assertEquals(100, addIncomeCommand.getAmount());
        assertEquals(LocalDate.now(), addIncomeCommand.getDate(), "Expected default date to be today");
    }

    @Test
    void testInvalidDateFormat() {
        String command = "add income Part-time a/50 d/invalid-date";
        Command result = AddIncomeValidator.processCommand(command);

        assertTrue(result instanceof IncorrectCommand);
        assertEquals("Invalid date format. Use d/dd/MM/yyyy.", ((IncorrectCommand) result).
                getFeedbackToUser());
    }

    @Test
    void testEmptyDescription() {
        String command = "add income a/100 d/25/10/2024";
        Command result = AddIncomeValidator.processCommand(command);

        assertTrue(result instanceof IncorrectCommand);
        assertEquals("Description cannot be empty.", ((IncorrectCommand) result).getFeedbackToUser());
    }

    @Test
    void testNegativeAmount() {
        String command = "add income Refund a/-50 d/25/10/2024";
        Command result = AddIncomeValidator.processCommand(command);

        assertTrue(result instanceof IncorrectCommand);
        assertEquals("Amount must be a positive value.", ((IncorrectCommand) result).getFeedbackToUser());
    }
}
