package seedu.budgetbuddy.graphs;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import seedu.budgetbuddy.transaction.expense.Expense;

import javax.swing.JFrame;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that generates and displays a graph showing expenses over months in a given year.
 */
public class ExpensesOverMonthGraph {

    /**
     * Builds a map of YearMonth to total expenses for that month.
     * It accumulates expenses into their respective months.
     *
     * @param expenses An ArrayList of Expense objects.
     * @return A map where the keys are YearMonth objects and the values are total expenses for that month.
     */
    public static Map<YearMonth, Double> monthMapBuilder(ArrayList<Expense> expenses) {
        Map<YearMonth, Double> monthlyExpenseMap = new HashMap<>();

        for (Expense expense : expenses) {
            YearMonth month = YearMonth.from(expense.getDate()); // Correctly get YearMonth
            double amount = expense.getAmount();

            // Accumulate the expense amounts for each month
            monthlyExpenseMap.put(month, monthlyExpenseMap.getOrDefault(month, 0.0) + amount);
        }
        return monthlyExpenseMap;
    }

    /**
     * Retrieves the total expense for a given YearMonth from the monthly expense map.
     *
     * @param monthlyExpenseMap A map containing YearMonth as keys and total expenses as values.
     * @param yearMonth The YearMonth for which the total expense is to be retrieved.
     * @return The total expense for the given YearMonth, or 0.0 if no expenses are found for that month.
     */
    public static double expensesForMonth(Map<YearMonth, Double> monthlyExpenseMap, YearMonth yearMonth) {
        return monthlyExpenseMap.getOrDefault(yearMonth, 0.0);
    }

    /**
     * Prints a chart that displays expenses for each month in the specified year.
     * The chart is displayed using Swing and is set to close without terminating the program.
     *
     * @param monthlyExpenseMap A map containing YearMonth as keys and total expenses as values.
     * @param year The year for which the expenses will be displayed.
     */
    public static void chartPrinter(Map<YearMonth, Double> monthlyExpenseMap, int year) {
        // Create a list to hold all months in the year
        List<Double> xAxis = new ArrayList<>();
        List<Double> yAxis = new ArrayList<>();

        for (int month = 1; month <= 12; month++) {
            YearMonth yearMonth = YearMonth.of(year, month); // Use a fixed year for the chart
            double amount = monthlyExpenseMap.getOrDefault(yearMonth, 0.0); // Get the amount or zero if no expenses
            xAxis.add((double) month); // Month as double
            yAxis.add(amount); // Corresponding expense amount
        }

        // Build the chart
        XYChart expensesChart = new XYChartBuilder().width(800).height(600)
                .title("Expenses Over Month Graph")
                .xAxisTitle("Month")
                .yAxisTitle("Expenses")
                .build();

        // Add the series
        expensesChart.addSeries("Monthly Expenses", xAxis, yAxis);

        // Create the SwingWrapper
        SwingWrapper<XYChart> swingWrapper = new SwingWrapper<>(expensesChart);
        JFrame frame = swingWrapper.displayChart();
        frame.setTitle("BudgetBuddy");

        // Set the default close operation on the EDT
        javax.swing.SwingUtilities.invokeLater(() -> frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE));
    }
}

