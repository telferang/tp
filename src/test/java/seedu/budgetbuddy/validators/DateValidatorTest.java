package seedu.budgetbuddy.validators;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class DateValidatorTest {
    @Test
    void validateDate_validDate_expectParsedDate() {
        String input = "d/15/8/2023";
        LocalDate result = DateValidator.validateDate(input);
        LocalDate expected = LocalDate.of(2023, 8, 15);
        assertEquals(expected, result);
    }

    @Test
    void validateDate_invalidDate_expectNull() {
        String input = "d/invalid";
        LocalDate result = DateValidator.validateDate(input);
        assertNull(result);
    }
}
