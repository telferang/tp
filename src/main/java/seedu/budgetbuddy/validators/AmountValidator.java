package seedu.budgetbuddy.validators;

/**
 * Validates and converts amount from String to double.
 */
public class AmountValidator {
    /**
     * Converts amount from String to double.
     *
     * @param part The String containing the amount.
     * @return The parsed amount or -1 if invalid.
     */
    public static double validateAmount(String part) {
        try {
            return Double.parseDouble(part.substring(2));
        } catch (NumberFormatException e) {
            return -1;  // Indicates invalid amount
        }
    }
}
