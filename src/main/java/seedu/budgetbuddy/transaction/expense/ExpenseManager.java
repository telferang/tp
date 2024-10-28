package seedu.budgetbuddy.transaction.expense;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.budget.RemainingBudgetManager;
import seedu.budgetbuddy.util.LoggerSetup;
import seedu.budgetbuddy.graphs.ExpensesOverMonthGraph;

import java.time.YearMonth;
import java.util.ArrayList;

import java.time.LocalDate;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages a list of expenses, providing functionalities to add, delete,
 * and list expenses, as well as tracking the total number of expenses.
 */
public class ExpenseManager {
    private static final Logger LOGGER = LoggerSetup.getLogger();
    private static int numberOfExpenses = 0;
    private static ArrayList<Expense> expenses = new ArrayList<>();

    /**
     * Construct a ExpenseManager of array content expenses
     *
     * @param expenses is the content to be instantiated
     */
    public ExpenseManager(ArrayList<Expense> expenses, int numberOfExpenses) {
        assert numberOfExpenses >= 0 : "numberOfExpenses should be greater than 0";
        ExpenseManager.expenses = expenses;
        ExpenseManager.numberOfExpenses = numberOfExpenses;
    }

    /**
     * Construct a ExpenseManager of array content expenses
     */
    public ExpenseManager() {

    }

    /**
     * Adds a new expense to the manager.
     *
     * @param expense The expense to be added.
     */
    public static void addExpense(Expense expense) {
        expenses.add(expense);
        numberOfExpenses++;
        String budgetRemaining = new RemainingBudgetManager().getRemainingBudgets(expense.getDate()
                , expense.getCategory());
        String result = "The following expense transaction has been added:\n"
                + expense + '\n'
                + "You have " + numberOfExpenses + " expense transaction(s) in total.\n" + budgetRemaining;
        Ui.displayToUser(result);
    }

    /**
     * Load a new expense from storage to the manager.
     *
     * @param expense The expense to be added.
     */
    public static void loadExpense(Expense expense) {
        expenses.add(expense);
        numberOfExpenses++;
    }

    /**
     * Deletes an expense from the manager at the specified index.
     *
     * @param index The index of the expense to be deleted.
     */
    public static void deleteExpense(int index) {
        numberOfExpenses--;
        String result = "The following expense transaction has been deleted:\n"
                + expenses.get(index) + '\n'
                + "You have " + numberOfExpenses + " expense transaction(s) in total.\n";
        LocalDate date = expenses.get(index).getDate();
        Category category = expenses.get(index).getCategory();
        expenses.remove(index);
        String budgetRemaining = new RemainingBudgetManager().getRemainingBudgets(date, category);
        result += budgetRemaining;
        Ui.displayToUser(result);
    }

    /**
     * Returns the current number of expenses.
     *
     * @return The total number of expenses.
     */
    public static int getNumberOfExpenses() {
        return numberOfExpenses;
    }

    /**
     * Calculates the total expenses for a specified month.
     *
     * @param month The month to calculate expenses for.
     * @return The total expenses for the month; returns 0.0 if no expense is found.
     */
    public static double getMonthlyExpense(YearMonth month) {
        double sum = 0;
        for (Expense expense : expenses) {
            if(month.equals(getYearMonthFromDate(expense.getDate()))) {
                sum += expense.getAmount();
            }
        }
        return sum;
    }

    /**
     * Lists all the expenses managed by the manager.
     * Displays each expense with its corresponding number.
     */
    public static void listExpenses() {
        String result = "";
        int counter = 0;
        for (Expense expense : expenses) {
            counter++;
            result += counter + ". " + expense.toString() + "\n";
        }
        result += "There are " + counter + " expense(s) in total";
        LOGGER.log(Level.INFO, "Listing {0} expenses", numberOfExpenses);
        Ui.displayToUser(result);
    }

    /**
     * Display all expense that matches with month & category field
     * Displays each expense with its corresponding number.
     * @param category
     * @param month
     * @return result String to be displayed to user
     */
    public static String listExpensesWithCategoryAndDate(Category category, YearMonth month) {
        assert category != null : "category cannot be null";
        assert month != null : "month cannot be null";
        String result = "";
        int counter = 0;
        for (Expense expense : expenses) {
            if(category.equals(expense.getCategory()) && month.equals(getYearMonthFromDate(expense.getDate()))) {
                counter++;
                result += counter + ". " + expense.toString() + "\n";
            }
        }
        if(result.equals("")) {
            result = getEmptyDisplayMessage();
        } else {
            result += "There are " + counter + " expense(s) in " + month + " for " + category;
        }
        return result;
    }

    /**
     * Display all expense that matches with category field
     * Displays each expense with its corresponding number.
     * @param category
     * @return result String to be displayed to user
     */
    public static String listExpensesWithCategory(Category category) {
        assert category != null : "category cannot be null";
        String result = "";
        int counter = 0;
        for (Expense expense : expenses) {
            if(category.equals(expense.getCategory())) {
                counter++;
                result += counter + ". " + expense.toString() + "\n";
            }
        }
        if(result.equals("")) {
            result = getEmptyDisplayMessage();
        } else {
            result += "There are " + counter + " expense(s) for " + category;
        }
        return result;
    }

    /**
     * Display all expense that matches with month field
     * Displays each expense with its corresponding number.
     * @param month
     * @return result String to be displayed to user
     */
    public static String listExpensesWithDate(YearMonth month) {
        assert month != null : "month cannot be null";
        String result = "";
        int counter = 0;
        for (Expense expense : expenses) {
            if(month.equals(getYearMonthFromDate(expense.getDate()))) {
                counter++;
                result += counter + ". " + expense.toString() + "\n";
            }
        }
        if(result.equals("")) {
            result = getEmptyDisplayMessage();
        } else {
            result += "There are " + counter + " expense(s) in " + month;
        }
        return result;
    }

    /**
     * Filters expenses with descriptions that contain the keyword(s) provided by user.
     * @param keyword
     * @return result String displayed to user
     */
    public static String searchExpenses(String keyword){
        assert keyword != null: "Keyword should not be null";
        String result = "";
        if (keyword.equals("")){
            result = getEmptyDisplayMessage();
            return result;
        }
        int counter = 1;
        for (Expense expense : expenses) {
            if (expense.getDescription().toLowerCase().contains(keyword.toLowerCase())){
                result += counter + ". " + expense.toString() + "\n";
                counter++;
            }
        }
        if (result.equals("")){
            result = getEmptyDisplayMessage();
        }
        return result;
    }

    /**
     * Displays a graph of expenses over the given year.
     *
     * @param year The year for which the expenses graph is to be displayed.
     */
    public static void displayExpensesOverMonthGraph(int year) {
        ArrayList<Expense> expensesOverMonthArray = getExpenses();
        Map<YearMonth, Double> monthlyExpensesMap = ExpensesOverMonthGraph.monthMapBuilder(expensesOverMonthArray);
        ExpensesOverMonthGraph.chartPrinter(monthlyExpensesMap, year);
    }

    /**
     * Displays the total expenses for a specific month.
     *
     * @param yearMonth The YearMonth object representing the month for which the total expenses are to be displayed.
     */
    public static void listTotalExpensesForMonth(YearMonth yearMonth) {
        ArrayList<Expense> expensesOverMonthArray = getExpenses();
        Map<YearMonth, Double> monthlyExpensesMap = ExpensesOverMonthGraph.monthMapBuilder(expensesOverMonthArray);
        Ui.displayToUser("Your expenses for " + yearMonth.toString() + " is " +
                ExpensesOverMonthGraph.expensesForMonth(monthlyExpensesMap, yearMonth));
    }

    /**
     * Breaks down all expenses of the user by category.
     * @return String that displays total expenses of the user and the amount and percentage of total expenses spent
     *     per category.
     */
    public static String breakdownExpensesByCategory() {
        String result = "";
        double totalExpensesFood = 0;
        double totalExpensesOthers = 0;
        double totalExpensesTransport = 0;
        double totalExpensesEntertainment = 0;
        double totalExpensesUtilities = 0;
        double totalExpensesEducation = 0;
        for (Expense expense : expenses) {
            switch (expense.getCategory()) {
            case FOOD -> totalExpensesFood += expense.getAmount();
            case EDUCATION -> totalExpensesEducation += expense.getAmount();
            case TRANSPORT -> totalExpensesTransport += expense.getAmount();
            case UTILITIES -> totalExpensesUtilities += expense.getAmount();
            case ENTERTAINMENT -> totalExpensesEntertainment += expense.getAmount();
            case OTHERS -> totalExpensesOthers += expense.getAmount();
            default -> LOGGER.warning("Invalid category type detected.");
            }
        }
        assert totalExpensesFood >= 0 : "Total expense for food cannot be negative";
        assert totalExpensesOthers >= 0 : "Total expense for others cannot be negative";
        assert totalExpensesTransport >= 0 : "Total expense for transport cannot be negative";
        assert totalExpensesEntertainment >= 0 : "Total expense for entertainment cannot be negative";
        assert totalExpensesUtilities >= 0 : "Total expense for utilities cannot be negative";
        assert totalExpensesEducation >= 0 : "Total expense for education cannot be negative";
        double totalExpenses = totalExpensesEducation + totalExpensesFood + totalExpensesEntertainment +
                totalExpensesTransport + totalExpensesUtilities + totalExpensesOthers;
        if (totalExpenses == 0) {
            result += "Total expenses: 0. You have not indicated any expense yet.";
        } else {
            result = "Total expenses: " + totalExpenses + "\n" + "Food: " + totalExpensesFood + "(" +
                    String.format("%.2f", totalExpensesFood / totalExpenses * 100) + "%)\n" + "Transport: " +
                    totalExpensesTransport + "(" + String.format("%.2f", totalExpensesTransport / totalExpenses * 100) +
                    "%)\n" + "Utilities: " + totalExpensesUtilities + "(" +
                    String.format("%.2f", totalExpensesUtilities / totalExpenses * 100) + "%)\n" + "Entertainment: " +
                    totalExpensesEntertainment + "(" +
                    String.format("%.2f", totalExpensesEntertainment / totalExpenses * 100) + "%)\n" + "Education: " +
                    totalExpensesEducation + "(" +
                    String.format("%.2f", totalExpensesEducation / totalExpenses * 100) + "%)\n" + "Others: " +
                    totalExpensesOthers + "(" + String.format("%.2f", totalExpensesOthers / totalExpenses * 100) +
                    "%)\n";
        }
        return result;
    }

    /**
     * Displays the total expenses for a specific month on a specific category
     *
     * @param yearMonth The YearMonth object representing the month for which the total expenses are to be displayed.
     * @param category The Category object representing the category of the total expenses to be displayed.
     */
    public static void listTotalExpensesForMonthWithCategories(YearMonth yearMonth, Category category) {
        ArrayList<Expense> expensesOverMonthArray = getExpenses();
        double totalAmount = 0.0;

        Ui.displayToUser("The Expenses for " + yearMonth + " under category: " + category);
        for (Expense expense : expensesOverMonthArray) {
            YearMonth expenseYearMonth = getYearMonthFromDate(expense.getDate());

            if(expenseYearMonth.equals(yearMonth) && category.equals(expense.getCategory())) {
                totalAmount += expense.getAmount();
            }
        }
        System.out.println(totalAmount);
    }

    /**
     * Extract YearMonth value from date
     * @param date
     * @return
     */
    public static YearMonth getYearMonthFromDate(LocalDate date) {
        assert date != null: "Date should not be null";
        return YearMonth.from(date);
    }

    /**
     * Generates a custom empty Display Expense message
     * @return custom display Expense message
     */
    public static String getEmptyDisplayMessage() {
        return "No expense entry with given parameters found, try again with a different parameter.";
    }

    /**
     * A get-function to obtain the information in the current Expense List.
     *
     * @return return the expense ArrayList
     */
    public static ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public static Expense getExpenseByIndex(int index) throws BudgetBuddyException {
        if(index > numberOfExpenses) {
            throw new BudgetBuddyException("Input index is larger than the number of expenses. " +
                    "Try with a smaller index");
        }
        return expenses.get(index);
    }

    /**
     * Resets the state of the ExpenseManager by clearing all expenses and
     * setting the total number of expenses to zero.
     * <p>
     * This method is used for unit testing, ensuring that each test
     * starts with a clean slate and does not retain any state from
     * previous tests.
     * </p>
     */
    public static void reset() {
        numberOfExpenses = 0;
        expenses.clear();
    }
}
