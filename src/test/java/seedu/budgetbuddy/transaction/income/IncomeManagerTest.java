package seedu.budgetbuddy.transaction.income;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncomeManagerTest {

    private IncomeManager incomeManager;
    private ArrayList<Income> incomes;
    private int numberOfIncomes;

    @BeforeEach
    void initializeTestContent() {
        incomes = new ArrayList<>();
        numberOfIncomes = 0;
        incomeManager = new IncomeManager(incomes, numberOfIncomes);
    }

    @Test
    void addIncome_validIncome_incomeAddedSuccessfully() {
        Income income = new Income("Salary", 5000, LocalDate.of(2024, 10, 1));
        incomeManager.addIncome(income);

        assertEquals(1, incomeManager.getNumberOfIncomes());
        assertEquals(income, incomeManager.getIncomes().get(0));
    }

    @Test
    void deleteIncome_validIndex_incomeDeletedSuccessfully() {
        Income income = new Income("Salary", 5000, LocalDate.of(2024, 10, 1));
        incomeManager.addIncome(income);

        // Now deleting the income at index 0 (since it's the first item)
        incomeManager.deleteIncome(0);

        assertEquals(0, incomeManager.getNumberOfIncomes());
        assertTrue(incomeManager.getIncomes().isEmpty());
    }

    @Test
    void listIncomes_withIncomes_displaysIncomes() {
        Income income1 = new Income("Salary", 5000, LocalDate.of(2024, 10, 1));
        Income income2 = new Income("Bonus", 1000, LocalDate.of(2024, 10, 15));
        incomeManager.addIncome(income1);
        incomeManager.addIncome(income2);

        // Test output to UI, this can be validated through mocking the Ui class
        incomeManager.listIncomes();
        // assert the UI displays correct output
    }

    @Test
    void listIncomeWithMonth_emptyMonth_displaysNoIncomeMessage() {
        YearMonth month = YearMonth.of(2024, 9);
        incomeManager.listIncomeWithMonth(month);

        // assert the UI displays the empty message
    }

    @Test
    void getYearMonthFromDate_validDate_returnsCorrectYearMonth() {
        LocalDate date = LocalDate.of(2024, 10, 1);
        YearMonth expected = YearMonth.of(2024, 10);
        assertEquals(expected, IncomeManager.getYearMonthFromDate(date));
    }
}

