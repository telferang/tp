package seedu.budgetbuddy.validators;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.expense.DisplayExpenseCommand;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.validators.expense.DisplayExpenseValidator;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

class DisplayExpenseValidatorTest {

    @Test
    void checkDisplayType_categoryIsEmpty_expectNullCategory(){
        DisplayExpenseValidator expenseValidator = new DisplayExpenseValidator();
        Category category = null;
        YearMonth yearMonth = validateYearMonth("02/2024");
        DisplayExpenseCommand command = (DisplayExpenseCommand) expenseValidator.checkDisplayType(category, yearMonth);
        assertEquals(null, command.getCategory());
        assertEquals(yearMonth,command.getMonth());
    }

    @Test
    void checkDisplayType_monthIsEmpty_expectNullMonth(){
        DisplayExpenseValidator expenseValidator = new DisplayExpenseValidator();
        Category category = Category.FOOD;
        YearMonth yearMonth = null;
        DisplayExpenseCommand command = (DisplayExpenseCommand) expenseValidator.checkDisplayType(category, yearMonth);
        assertEquals(category, command.getCategory());
        assertEquals(yearMonth,command.getMonth());
    }

    @Test
    void checkDisplayType_categoryMonthSpecified_expectSameValues(){
        DisplayExpenseValidator expenseValidator = new DisplayExpenseValidator();
        Category category = Category.FOOD;
        YearMonth yearMonth = validateYearMonth("02/2024");
        DisplayExpenseCommand command = (DisplayExpenseCommand) expenseValidator.checkDisplayType(category, yearMonth);
        assertEquals(category, command.getCategory());
        assertEquals(yearMonth,command.getMonth());
    }
}
