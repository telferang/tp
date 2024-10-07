package seedu.budgetbuddy;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.ExitCommand;
import seedu.budgetbuddy.transaction.budget.Budget;
import seedu.budgetbuddy.transaction.budget.BudgetManager;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class for the BudgetBuddy application.
 *
 * This class initiates the BudgetBuddy application by managing the overall lifecycle
 * of the application, including loading saved data, handling user commands, and saving
 * changes to a file. It processes user commands until the exit command is invoked.
 */
public class BudgetBuddy {
    private Storage storage;
    private ExpenseManager expenseManager;
    private IncomeManager incomeManager;
    private BudgetManager budgetManager;

    /**
     * Constructs a new BudgetBuddy instance, initializing file storage and loading saved data.
     * It attempts to create a file if it does not exist and loads data from the provided file path.
     *
     * @param filepath The path to the file where expenses, incomes, and budgets are stored.
     */
    public BudgetBuddy(String filepath) {
        storage = new Storage(filepath);
        try {
            storage.createFileIfNotExists();
            ArrayList<ArrayList<?>> data = storage.load();
            expenseManager = new ExpenseManager((ArrayList<Expense>) data.get(0));
            incomeManager = new IncomeManager((ArrayList<Income>) data.get(1));
            budgetManager = new BudgetManager((ArrayList<Budget>) data.get(2));
        } catch (IOException e) {
            Ui.showMessage("Error updating File");
        }
    }

    /**
     * Runs the BudgetBuddy application. Displays the welcome message and
     * continuously processes user commands until the exit command is executed.
     * After each command, it saves any changes to the storage file.
     */
    public void run() {
        Ui.displayWelcomeMessage();
        Command command;
        Parser parser = new Parser(expenseManager, incomeManager, budgetManager);
        do {
            String userCommandText = Ui.getUserCommand();
            command = parser.parseCommand(userCommandText);
            command.execute();
            try {
                storage.save(expenseManager, incomeManager, budgetManager);
            } catch (IOException e) {
                Ui.showMessage("Error updating File");
            }
        } while (!(command instanceof ExitCommand));
        System.exit(0);
    }

    /**
     * The entry point for the BudgetBuddy application. Creates a new BudgetBuddy instance
     * with the specified file path and starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new BudgetBuddy("./data/BudgetBuddy.txt").run();
    }
}

