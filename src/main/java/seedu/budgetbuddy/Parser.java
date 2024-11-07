package seedu.budgetbuddy;

import seedu.budgetbuddy.commands.budget.ListRemainingBudgetCommand;
import seedu.budgetbuddy.commands.expense.BreakdownExpensesCommand;
import seedu.budgetbuddy.commands.expense.DeleteExpenseCommand;
import seedu.budgetbuddy.commands.expense.AddExpenseCommand;
import seedu.budgetbuddy.commands.expense.DisplayExpensesForMonthWithCategoriesGraphCommand;
import seedu.budgetbuddy.commands.expense.EditExpenseCommand;
import seedu.budgetbuddy.commands.expense.SearchExpenseCommand;
import seedu.budgetbuddy.commands.expense.ListExpenseCommand;
import seedu.budgetbuddy.commands.expense.DisplayTotalExpensesCommand;
import seedu.budgetbuddy.commands.income.AddIncomeCommand;
import seedu.budgetbuddy.commands.budget.AddBudgetCommand;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.budget.DeductBudgetCommand;
import seedu.budgetbuddy.commands.income.DeleteIncomeCommand;
import seedu.budgetbuddy.commands.ExitCommand;
import seedu.budgetbuddy.commands.HelpCommand;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.budget.ListBudgetCommand;
import seedu.budgetbuddy.commands.income.DisplayIncomeSpentCommand;
import seedu.budgetbuddy.commands.income.EditIncomeCommand;
import seedu.budgetbuddy.commands.income.ListIncomeCommand;
import seedu.budgetbuddy.commands.saving.DisplaySavingsCommand;
import seedu.budgetbuddy.commands.expense.ListMonthlyExpensesCommand;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.transaction.budget.Budget;
import seedu.budgetbuddy.transaction.budget.BudgetManager;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;
import seedu.budgetbuddy.validators.expense.DisplayExpensesForMonthWithCategoriesValidator;
import seedu.budgetbuddy.validators.expense.EditExpenseValidator;
import seedu.budgetbuddy.validators.expense.ListExpenseValidator;
import seedu.budgetbuddy.validators.income.AddIncomeValidator;
import seedu.budgetbuddy.validators.budget.AddBudgetValidator;
import seedu.budgetbuddy.validators.budget.DeductBudgetValidator;
import seedu.budgetbuddy.validators.income.DeleteIncomeValidator;
import seedu.budgetbuddy.validators.income.DisplayIncomeSpentValidator;
import seedu.budgetbuddy.validators.income.EditIncomeValidator;
import seedu.budgetbuddy.validators.income.ListIncomeValidator;
import seedu.budgetbuddy.validators.budget.ListBudgetValidator;
import seedu.budgetbuddy.validators.expense.AddExpenseValidator;
import seedu.budgetbuddy.validators.expense.DeleteExpenseValidator;
import seedu.budgetbuddy.validators.expense.DisplayTotalExpensesValidator;
import seedu.budgetbuddy.validators.expense.SearchExpenseValidator;
import seedu.budgetbuddy.validators.saving.DisplaySavingsValidator;
import seedu.budgetbuddy.validators.expense.ListMonthlyExpensesValidator;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
/**
 * The Parser class is responsible for interpreting user commands.
 * It analyzes the user input, identifies the corresponding command,
 * and returns the appropriate Command object for execution.
 */
public class Parser {
    private static ExpenseManager expenseManager;
    private static IncomeManager incomeManager;
    private static BudgetManager budgetManager;

    public Parser(ExpenseManager expenseManager, IncomeManager incomeManager, BudgetManager budgetManager) {
        Parser.expenseManager = expenseManager;
        Parser.incomeManager = incomeManager;
        Parser.budgetManager = budgetManager;
    }

    /**
     * Analyzes the user's input and returns the appropriate {@code Command} object.
     * It checks the input against known commands for expense, income, or exit operations.
     *
     * @param userCommandText The input string provided by the user.
     * @return The corresponding {@code Command} to execute, or an {@code IncorrectCommand}
     *         if the input is invalid.
     */
    public Command parseCommand(String userCommandText) throws BudgetBuddyException {
        if (AddExpenseCommand.isCommand(userCommandText)) {
            return AddExpenseValidator.processCommand(userCommandText);
        }
        if (DeleteExpenseCommand.isCommand(userCommandText)) {
            return DeleteExpenseValidator.processCommand(userCommandText);
        }
        if (ListExpenseCommand.isCommand(userCommandText)) {
            return ListExpenseValidator.processCommand(userCommandText);
        }
        if (AddIncomeCommand.isCommand(userCommandText)) {
            return AddIncomeValidator.processCommand(userCommandText);
        }
        if (DeleteIncomeCommand.isCommand(userCommandText)) {
            return DeleteIncomeValidator.processCommand(userCommandText);
        }
        if (AddBudgetCommand.isCommand(userCommandText)) {
            return AddBudgetValidator.processCommand(userCommandText);
        }
        if (DeductBudgetCommand.isCommand(userCommandText)) {
            return DeductBudgetValidator.processCommand(userCommandText);
        }
        if (ListBudgetCommand.isCommand(userCommandText)) {
            return ListBudgetValidator.processCommand(userCommandText);
        }
        if (ExitCommand.isCommand(userCommandText)) {
            return new ExitCommand();
        }
        if (HelpCommand.isCommand(userCommandText)){
            return new HelpCommand();
        }
        if (ListIncomeCommand.isCommand(userCommandText)) {
            return ListIncomeValidator.processCommand(userCommandText);
        }
        if (SearchExpenseCommand.isCommand(userCommandText)){
            return SearchExpenseValidator.processCommand(userCommandText);
        }
        if (DisplayTotalExpensesCommand.isCommand(userCommandText)){
            return DisplayTotalExpensesValidator.processCommand(userCommandText);
        }
        if (ListMonthlyExpensesCommand.isCommand(userCommandText)) {
            return ListMonthlyExpensesValidator.processCommand(userCommandText);
        }
        if (DisplayIncomeSpentCommand.isCommand(userCommandText)) {
            return DisplayIncomeSpentValidator.processCommand(userCommandText);
        }
        if(EditExpenseCommand.isCommand(userCommandText)){
            return EditExpenseValidator.processFirstCommand(userCommandText);
        }
        if(EditIncomeCommand.isCommand(userCommandText)){
            return EditIncomeValidator.processFirstCommand(userCommandText);
        }
        if (ListRemainingBudgetCommand.isCommand(userCommandText)) {
            return new ListRemainingBudgetCommand();
        }
        if (DisplaySavingsCommand.isCommand(userCommandText)){
            return DisplaySavingsValidator.processCommand(userCommandText);
        }
        if (BreakdownExpensesCommand.isCommand(userCommandText)){
            return new BreakdownExpensesCommand();
        }
        if (DisplayExpensesForMonthWithCategoriesGraphCommand.isCommand(userCommandText)){
            return DisplayExpensesForMonthWithCategoriesValidator.processCommand(userCommandText);
        }
        return new IncorrectCommand("Invalid input");
    }

    /**
     * Parses a line of input from the file and categorizes it as an expense, income, or budget.
     * Each line is split based on the delimiter " | ", and the resulting parts are used to create
     * the appropriate object (Expense, Income, or Budget).
     *
     * @param input The line of text from the file to be parsed.
     */
    public static void parseFile(String input) {

        String[] parts = input.split(" \\| ");
        String type = parts[0]; // Determines if it's expense, income, or budget

        switch (type.toLowerCase()) {
        case "expense": {
            try {
                if (parts.length != 5) {
                    Ui.showMessage("Invalid Storage Format: " + input);
                    return;
                }
                String description = parts[1];
                double amount = Double.parseDouble(parts[2]);
                LocalDate date = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("d/M/yyyy"));
                Category category = Category.valueOf(parts[4].toUpperCase()); // Ensure category exists for expense

                expenseManager.loadExpense(new Expense(description, amount, date, category));
            } catch (Exception e) {
                Ui.showMessage("Invalid Storage Format: " + input);
            }

            break;
        }
        case "income": {
            try {
                if (parts.length != 4) {
                    Ui.showMessage("Invalid Storage Format: " + input);
                    return;
                }
                String description = parts[1];
                double amount = Double.parseDouble(parts[2]);
                LocalDate date = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("d/M/yyyy"));

                incomeManager.loadIncome(new Income(description, amount, date)); // No category needed for income
            } catch (Exception e) {
                Ui.showMessage("Invalid Input Format: " + input);
            }

            break;
        }
        case "budget": {
            try {
                if (parts.length != 4) {
                    Ui.showMessage("Invalid Storage Format: " + input);
                    return;
                }

                YearMonth budgetDate = YearMonth.parse(parts[2], DateTimeFormatter.ofPattern("yyyy-MM"));

                if (budgetManager.getBudget(budgetDate) != null) {
                    Ui.showMessage("Repeated budget entry: " + input);
                    return;
                }

                // Adjust date format for YearMonth
                String categoryPart = parts[3].trim();
                categoryPart = categoryPart.substring(1, categoryPart.length() - 1);
                Budget budget = new Budget(budgetDate);

                String[] categories = categoryPart.split(", ");
                for (String categoryEntry : categories) {
                    String[] categorySplit = categoryEntry.split("=");
                    Category category = Category.valueOf(categorySplit[0].toUpperCase());
                    double categoryAmount = Double.parseDouble(categorySplit[1]);
                    budget.addAmount(category, categoryAmount);
                }
                budgetManager.addBudget(budget);
            } catch (Exception e) {
                Ui.showMessage("Invalid Input Format: " + input);
            }

            break;
        }
        default:
            System.out.println("Unknown type in file: " + type);
        }
    }
}
