package seedu.budgetbuddy.validators;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.expense.ListExpenseCommand;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.validators.expense.ListExpenseValidator;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

class DisplayExpenseValidatorTest {

    @Test
    void checkListType_categoryIsEmpty_expectNullCategory(){
        ListExpenseValidator expenseValidator = new ListExpenseValidator();
        Category category = null;
        YearMonth yearMonth = validateYearMonth("02/2024");
        ListExpenseCommand command = (ListExpenseCommand) expenseValidator.checkListType(category, yearMonth);
        assertEquals(null, command.getCategory());
        assertEquals(yearMonth,command.getMonth());
    }

    @Test
    void checkListType_monthIsEmpty_expectNullMonth(){
        ListExpenseValidator expenseValidator = new ListExpenseValidator();
        Category category = Category.FOOD;
        YearMonth yearMonth = null;
        ListExpenseCommand command = (ListExpenseCommand) expenseValidator.checkListType(category, yearMonth);
        assertEquals(category, command.getCategory());
        assertEquals(yearMonth,command.getMonth());
    }

    @Test
    void checkListType_categoryMonthSpecified_expectSameValues(){
        ListExpenseValidator expenseValidator = new ListExpenseValidator();
        Category category = Category.FOOD;
        YearMonth yearMonth = validateYearMonth("02/2024");
        ListExpenseCommand command = (ListExpenseCommand) expenseValidator.checkListType(category, yearMonth);
        assertEquals(category, command.getCategory());
        assertEquals(yearMonth,command.getMonth());
    }
}
