package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.AddIncomeCommand;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddIncomeValidator {

    public static Command processCommand(String command) {
        String description = "";
        double amount = 0.0;
        LocalDate date = LocalDate.now();

        String trimmedCommand = command.substring("add income ".length());
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


        return new AddIncomeCommand(description, amount, date);
    }
}
