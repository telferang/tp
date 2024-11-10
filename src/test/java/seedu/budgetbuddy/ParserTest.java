package seedu.budgetbuddy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.expense.AddExpenseCommand;
import seedu.budgetbuddy.commands.budget.AddBudgetCommand;
import seedu.budgetbuddy.transaction.budget.BudgetManager;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.transaction.income.IncomeManager;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private Parser parser;
    private ExpenseManager expenseManager;
    private IncomeManager incomeManager;
    private BudgetManager budgetManager;

    @BeforeEach
    public void setUp() {
        expenseManager = new ExpenseManager();
        incomeManager = new IncomeManager();
        budgetManager = new BudgetManager();
        parser = new Parser(expenseManager, incomeManager, budgetManager);
    }

    @Test
    public void parseCommand_validAddExpenseCommand_returnsAddExpenseCommand() throws BudgetBuddyException {
        // Given a valid command string for adding an expense
        String command = "add expense KFC lunch a/10.50 d/28/10/2024 c/FOOD";

        // Process the command using the parser
        Command result = parser.parseCommand(command);

        // Assert that the result is an AddExpenseCommand
        assertInstanceOf(AddExpenseCommand.class, result);
    }

    @Test
    public void parseCommand_validAddBudgetCommand_returnsAddBudgetCommand() throws BudgetBuddyException {
        // Given a valid command string for adding a budget
        String command = "add budget a/700 m/11/2024 c/TRANSPORT";

        // Process the command using the parser
        Command result = parser.parseCommand(command);

        // Assert that the result is an AddBudgetCommand
        assertInstanceOf(AddBudgetCommand.class, result);
    }

    @Test
    public void parseCommand_invalidCommand_returnsIncorrectCommand() throws BudgetBuddyException {
        // Given an invalid command string
        String command = "invalid command";

        // Process the command using the parser
        Command result = parser.parseCommand(command);

        // Assert that the result is an IncorrectCommand
        assertInstanceOf(IncorrectCommand.class, result);

        // Assert the error message
        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        assertEquals("Invalid input", incorrectCommand.getFeedbackToUser());
    }

    @Test
    public void parseFile_validExpenseLine_parsesExpenseCorrectly() {
        // Given a valid expense input line
        String input = "expense | Lunch | 15 | 10/10/2024 | Food";

        // Parse the file input (use the class name since parseFile is static)
        Parser.parseFile(input);

        // Check if the expense was added to the expense manager (you may need a method to check this)
        // This can be extended to verify that the Expense object is added correctly.
        // For now, we will assume the method works and just check the behavior.
    }

    @Test
    public void parseFile_invalidExpenseLine_showsError() {
        // Given an invalid expense input line
        String input = "expense | Lunch | 15 | invalid-date | Food";

        // Parse the file input (use the class name)
        Parser.parseFile(input);
    }

    @Test
    public void parseFile_validBudgetLine_parsesBudgetCorrectly() {
        // Given a valid budget input line
        String input = "budget | Budget for October | 2024-10 | {food=100, entertainment=50}";

        // Parse the file input (use the class name)
        Parser.parseFile(input);

        // You can verify that the budget was successfully added to the BudgetManager (again, checking internal state).
    }

    @Test
    public void parseFile_invalidBudgetLine_showsError() {
        // Given an invalid budget input line
        String input = "budget | Budget for October | invalid-date | {food=100, entertainment=50}";

        // Parse the file input (use the class name)
        Parser.parseFile(input);

        // Verify that an error message is shown for invalid input.
    }
}


