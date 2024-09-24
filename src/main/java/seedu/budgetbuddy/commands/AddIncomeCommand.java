package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.income.IncomeManager;
import seedu.budgetbuddy.income.Income;

import java.time.LocalDate;

public class AddIncomeCommand extends Command {
    String description;
    double amount;
    LocalDate date;

    public static boolean isCommand(String command) {
        return command.startsWith("add income");
    }

    public AddIncomeCommand(String description, double amount, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public void execute(){
        IncomeManager.addIncome(new Income(description, amount, date));
    }
}
