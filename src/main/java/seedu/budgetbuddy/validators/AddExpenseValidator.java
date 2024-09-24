package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.expense.Category;
import seedu.budgetbuddy.commands.AddExpenseCommand;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddExpenseValidator{


    public static Command processCommand(String command) {
        String description = "";
        double amount = 0.0;
        LocalDate date = LocalDate.now();
        Category category = Category.OTHERS;

        String trimmedCommand = command.substring("add expense ".length());
        String[] parts = trimmedCommand.split(" ");
        boolean containsDescription = false;

        for (String part : parts) {
            if (part.startsWith("a/")) {  // Amount
                try {
                    amount = Double.parseDouble(part.substring(2));
                } catch (NumberFormatException e) {
                    return new IncorrectCommand("Invalid amount format.");
                }
            } else if (part.startsWith("d/")) {
                String dateStr = part.substring(2);
                try {
                    date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("d/M/yyyy"));
                } catch (DateTimeParseException e) {
                    return new IncorrectCommand("Invalid date format. Use d/dd/MM/yyyy.");
                }
            } else if (part.startsWith("c/")) {
                String categoryStr = part.substring(2).toUpperCase();
                try {
                    category = Category.valueOf(categoryStr);
                } catch (IllegalArgumentException e) {
                    return new IncorrectCommand("Invalid category");
                }
            } else {  // Description
                if (!containsDescription) {
                    description += part + " ";
                    containsDescription = true;
                } else {
                    description += part + " ";
                }
            }
        }

        description = description.trim();

        if (description.isEmpty()) {
            return new IncorrectCommand("Description cannot be empty.");
        }

        if (amount <= 0) {
            return new IncorrectCommand("Amount must be greater than zero.");
        }


        return new AddExpenseCommand(description, amount, date, category);
    }
}
