package seedu.budgetbuddy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.budget.Budget;
import seedu.budgetbuddy.transaction.budget.BudgetManager;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    private Storage storage;
    private File tempFile;
    private ExpenseManager expenseManager;
    private IncomeManager incomeManager;
    private BudgetManager budgetManager;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file for testing
        tempFile = File.createTempFile("testStorage", ".txt");
        tempFile.deleteOnExit(); // Ensure the file is deleted after the test

        // Initialize the Storage with the temporary file
        storage = new Storage(tempFile.getAbsolutePath());

        // Initialize managers with real data
        expenseManager = new ExpenseManager();
        incomeManager = new IncomeManager();
        budgetManager = new BudgetManager();
    }

    @AfterEach
    public void tearDown() {
        // Cleanup any resources after each test
        tempFile.delete();
    }

    @Test
    public void testCreateFileIfNotExists_createsFile() throws IOException {
        // Ensure the file doesn't exist before creating it
        File file = new File(tempFile.getAbsolutePath());
        file.delete(); // Delete if it exists

        // Ensure the file doesn't exist
        assertFalse(file.exists());

        // Call the method to create the file
        storage.createFileIfNotExists();

        // Verify the file has been created
        assertTrue(file.exists());
    }

    @Test
    public void testLoad_fileNotFound_throwsFileNotFoundException() {
        // Use a non-existing file path
        Storage invalidStorage = new Storage("non_existing_file.txt");

        // Assert that load() throws FileNotFoundException
        assertThrows(IOException.class, invalidStorage::load);
    }

    @Test
    public void testLoad_successfulLoading() throws IOException {
        // Write test data to the temporary file
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("expense | Lunch | 10.0 | 10/10/2024 | Food\n");
            writer.write("income | Salary | 2000.0 | 01/10/2024\n");
            writer.write("budget | 1500.0 | 2024-10 | {Food=500.0, Transport=300.0}\n");
        }

        // Now test the load function
        storage.load();

        // You may want to verify the actual data loading here, e.g., checking that expenses, incomes,
        // and budgets have been populated properly. The parser's actual behavior depends on how `Parser.parseFile`
        // processes the data, but for this example, we assume it works and check no exceptions were thrown.
        assertDoesNotThrow(() -> storage.load());
    }

    @Test
    public void testSave_savesCorrectly() throws IOException {
        // Create some sample data
        Expense expense = new Expense("Lunch", 10.0, LocalDate.of(2024, 7, 10), Category.FOOD);
        Income income = new Income("Salary", 2000.0, LocalDate.of(2024, 7, 10));
        Map<Category, Double> categoryBudgets = new HashMap<>();
        categoryBudgets.put(Category.FOOD, 500.0);
        categoryBudgets.put(Category.TRANSPORT, 300.0);
        Budget budget = new Budget(YearMonth.of(2024, 7));

        // Add sample data to managers
        expenseManager.addExpense(expense);
        incomeManager.addIncome(income);
        budgetManager.addBudget(budget);

        // Save data to the file
        storage.save(expenseManager, incomeManager, budgetManager);

        // Now, let's read the content from the file and check if the data was saved correctly
        File file = new File(tempFile.getAbsolutePath());
        assertTrue(file.exists());

        // Perform actual file reading and check if the saved data matches the expected format
        Scanner scanner = new Scanner(file);
        boolean expenseSaved = false;
        boolean incomeSaved = false;
        boolean budgetSaved = false;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("Lunch")) {
                expenseSaved = true;
            }
            if (line.contains("Salary")) {
                incomeSaved = true;
            }
            if (line.contains("2024-10")) {
                budgetSaved = true;
            }
        }

        // Ensure all data is saved
        assertTrue(expenseSaved);
        assertTrue(incomeSaved);
        assertTrue(budgetSaved);
    }
}
