package seedu.budgetbuddy.income;

import seedu.budgetbuddy.Ui;

import java.util.ArrayList;

public class IncomeManager {
    private static int numberOfIncomes = 0;
    private static ArrayList<Income> incomes = new ArrayList<>();

    /**
     * Adds a new income to the manager.
     *
     * @param income The income to be added.
     */
    public static void addIncome(Income income) {
        incomes.add(income);
        numberOfIncomes++;
        Ui.displayAcknowledgmentMessage(income.toString(), "added", "income", numberOfIncomes);
    }

    /**
     * Deletes an income from the manager at the specified index.
     *
     * @param index The index of the income to be deleted.
     */
    public static void deleteIncome(int index) {
        numberOfIncomes--;
        Ui.displayAcknowledgmentMessage(incomes.get(index).toString(), "deleted", "income", numberOfIncomes);
        incomes.remove(index);
    }

    /**
     * Returns the current number of incomes.
     *
     * @return The total number of incomes.
     */
    public static int getNumberOfIncomes() {
        return numberOfIncomes;
    }

    /**
     * Lists all the incomes managed by the manager.
     * Displays each income with its corresponding number.
     */
    public static void listIncomes() {
        String result = "";
        int counter = 1;
        for (Income income : incomes) {
            result += counter + ". " + income.toString() + "\n";
            counter++;
        }
        Ui.displayToUser(result);
    }
}
