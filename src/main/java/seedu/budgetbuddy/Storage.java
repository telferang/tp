package seedu.budgetbuddy;

import seedu.budgetbuddy.transaction.Transaction;
import seedu.budgetbuddy.transaction.budget.Budget;
import seedu.budgetbuddy.transaction.budget.BudgetManager;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class is responsible for handling the reading and writing of data from
 * and to the disk. It manages the creation of files, loading data into the program,
 * and saving the state of the Expense, Income, and Budget transactions.
 */
public class Storage {
    private String filePath;

    /**
     * Initializes the Storage object with the specified file path.
     *
     * @param filepath The path to the file where data will be saved and loaded.
     */
    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Loads the data from the file located at the specified file path.
     * It parses the file contents and converts it into the respective Expense, Income,
     * and Budget objects, storing them in lists.
     *
     * @return An ArrayList containing three ArrayLists for expenses, incomes, and budgets.
     * @throws FileNotFoundException If the file at the specified path does not exist.
     */
    public ArrayList<ArrayList<?>> load() throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist: " + file.getAbsolutePath());
        }
        ArrayList<Expense> expenses = new ArrayList<>();
        ArrayList<Income> incomes = new ArrayList<>();
        ArrayList<Budget> budgets = new ArrayList<>();
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Parser.parseFile(input, expenses, incomes, budgets);
        }
        sc.close();
        ArrayList<ArrayList<?>> list = new ArrayList<>();
        list.add(expenses);
        list.add(incomes);
        list.add(budgets);
        return list;
    }

    /**
     * Saves the current state of the Expense, Income, and Budget data to the file.
     * The file is overwritten with the latest data.
     *
     * @param expenseList The ExpenseManager containing the current list of expenses.
     * @param incomeList The IncomeManager containing the current list of incomes.
     * @param budgetList The BudgetManager containing the current list of budgets.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(ExpenseManager expenseList, IncomeManager incomeList, BudgetManager budgetList)
            throws IOException {

        FileWriter fw = new FileWriter(filePath, false); // Overwrites the file

        // Save expenses
        for (Expense expense : ExpenseManager.getExpenses()) {
            if (expense != null) {
                String line = getString(expense);
                fw.write(line + System.lineSeparator());
            }
        }

        // Save incomes
        for (Income income : IncomeManager.getIncomes()) {
            if (income != null) {
                String line = getString(income);
                fw.write(line + System.lineSeparator());
            }
        }

        // Save budgets
        for (Budget budget : BudgetManager.getBudgets()) {
            if (budget != null) {
                String line = getString(budget);
                fw.write(line + System.lineSeparator());
            }
        }
        fw.close();
    }

    /**
     * Converts a Transaction object into a string representation for saving to the file.
     * The string format differs depending on whether the transaction is an Expense or an Income.
     *
     * @param transaction The transaction to be converted.
     * @return A string representation of the transaction.
     */
    private String getString(Transaction transaction) {
        StringBuilder line = new StringBuilder();

        if (transaction instanceof Expense expense) {
            line.append("expense | ")
                    .append(expense.getDescription()).append(" | ")
                    .append(expense.getAmount()).append(" | ")
                    .append(expense.getDate().format(DateTimeFormatter.ofPattern("d/M/yyyy"))).append(" | ")
                    .append(expense.getCategory());
        } else if (transaction instanceof Income income) {
            line.append("income | ")
                    .append(income.getDescription()).append(" | ")
                    .append(income.getAmount()).append(" | ")
                    .append(income.getDate().format(DateTimeFormatter.ofPattern("d/M/yyyy")));
        }

        return line.toString();
    }

    /**
     * Converts a Budget object into a string representation for saving to the file.
     *
     * @param budget The budget to be converted.
     * @return A string representation of the budget.
     */
    private String getString(Budget budget) {
        return "budget | " + budget.getAmount() + " | " + budget.getDate().toString();
    }

    /**
     * Creates a new file at the specified path if it does not already exist.
     * If the file's parent directories do not exist, they will be created.
     *
     * @throws IOException If an error occurs while creating the file.
     */
    public void createFileIfNotExists() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.createNewFile();
        }
    }
}

