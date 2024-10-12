package seedu.budgetbuddy.validators;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.AddExpenseCommand;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.transaction.expense.Category;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddExpenseValidatorTest {

    @Test
    void processCommand_invalidAmount_returnsIncorrectCommand() {
        Command result = AddExpenseValidator.processCommand("add expense Lunch a/abc d/12/10/2024 c/FOOD");

        // Check if result is an IncorrectCommand and compare the message
        assertTrue(result instanceof IncorrectCommand);
        String message = ((IncorrectCommand) result).getFeedbackToUser();
        assertEquals("Invalid amount format. Amount should be a positive number.", message);
    }

    @Test
    void processCommand_noDescription_returnsIncorrectCommand() {
        Command result = AddExpenseValidator.processCommand("add expense a/100 d/12/10/2024 c/FOOD");

        // Check if result is an IncorrectCommand and compare the message
        assertTrue(result instanceof IncorrectCommand);
        String message = ((IncorrectCommand) result).getFeedbackToUser();
        assertEquals("Description cannot be empty.", message);
    }

    @Test
    void processCommand_invalidDate_returnsIncorrectCommand() {
        Command result = AddExpenseValidator.processCommand("add expense Lunch a/100 d/invalid_date c/FOOD");

        // Check if result is an IncorrectCommand and compare the message
        assertTrue(result instanceof IncorrectCommand);
        String message = ((IncorrectCommand) result).getFeedbackToUser();
        assertEquals("Invalid date format. Use d/dd/MM/yyyy.", message);
    }

    @Test
    void processCommand_noAmount_returnsIncorrectCommand() {
        Command result = AddExpenseValidator.processCommand("add expense Lunch d/12/10/2024 c/FOOD");

        // Check if result is an IncorrectCommand and compare the message
        assertTrue(result instanceof IncorrectCommand);
        String message = ((IncorrectCommand) result).getFeedbackToUser();
        assertEquals("Amount not entered.", message);
    }

    @Test
    void processCommand_negativeAmount_returnsIncorrectCommand() {
        Command result = AddExpenseValidator.processCommand("add expense Lunch a/-50 d/12/10/2024 c/FOOD");

        // Check if result is an IncorrectCommand and compare the message
        assertTrue(result instanceof IncorrectCommand);
        String message = ((IncorrectCommand) result).getFeedbackToUser();
        assertEquals("Amount must be a positive value.", message);
    }


    @Test
    void processCommand_validCommand_returnsAddExpenseCommand() {
        Command result = AddExpenseValidator.processCommand("add expense Lunch a/100 d/12/10/2024 c/FOOD");

        // Check if the result is an AddExpenseCommand (a valid command)
        assertTrue(result instanceof AddExpenseCommand);
        AddExpenseCommand command = (AddExpenseCommand) result;

        // Verify the details of the created command
        assertEquals("Lunch", command.getDescription());
        assertEquals(100, command.getAmount());
        assertEquals(LocalDate.of(2024, 10, 12), command.getDate());
        assertEquals(Category.FOOD, command.getCategory());
    }
}

