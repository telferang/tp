package seedu.budgetbuddy.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Validates and converts the date given.
 */
public class DateValidator {
    /**
     * Validates the date given.
     *
     * @param part The part containing the date.
     * @return The parsed date or null if invalid.
     */
    public static LocalDate validateDate(String part) {
        try {
            return LocalDate.parse(part.substring(2), DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            return null;  // Indicates invalid date
        }
    }
}
