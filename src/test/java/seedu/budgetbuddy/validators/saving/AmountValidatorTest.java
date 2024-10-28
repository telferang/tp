package seedu.budgetbuddy.validators.saving;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.validators.AmountValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AmountValidatorTest {

    @Test
    void validateAmount_inputIsDouble_expectDouble() {
        AmountValidator amountValidator = new AmountValidator();
        String input = "a/123.45";
        Double result = amountValidator.validateAmount(input);
        assertEquals(Double.valueOf(123.45), result);
    }

    @Test
    void validateAmount_inputIsNotDouble_expectNegativeOne() {
        AmountValidator amountValidator = new AmountValidator();
        String input = "a/invalid";
        Double result = amountValidator.validateAmount(input);
        assertEquals(Double.valueOf(-1), result);
    }
}
