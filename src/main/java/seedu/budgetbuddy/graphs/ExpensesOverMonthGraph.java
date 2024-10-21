package seedu.budgetbuddy.graphs;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import seedu.budgetbuddy.transaction.expense.Expense;

import javax.swing.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpensesOverMonthGraph {
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

    public static double ExpensesForMonth(Map<YearMonth, Double> monthlyExpenseMap, YearMonth yearMonth) {
        return monthlyExpenseMap.getOrDefault(yearMonth, 0.0);
    }

    public static void ChartPrinter(Map<YearMonth, Double> monthlyExpenseMap, int year) {
        // Create a list to hold all months in the year
        List<Double> xAxis = new ArrayList<>();
        List<Double> yAxis = new ArrayList<>();

        // Loop through each month of the year
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

        // Set the default close operation on the EDT
        javax.swing.SwingUtilities.invokeLater(() -> frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE));
    }
}


