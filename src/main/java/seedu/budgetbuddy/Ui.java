package seedu.budgetbuddy;

import java.util.Scanner;


public class Ui {

    public static final String SEPARATOR = "========================================================\n";
    public static final String WELCOME_MESSAGE = "Welcome to Budget Buddy!";
    public static final String EXIT_MESSAGE = "Bye!";
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Retrieves the user's command input. This method ensures that the user
     * does not submit an empty command by repeatedly prompting until valid input is provided.
     *
     * @return The user’s command as a trimmed {@code String}.
     */
    public static String getUserCommand() {
        String inputCommand;

        do {
            System.out.print("Enter commands: ");
            inputCommand = scanner.nextLine().trim();

            if (inputCommand.isEmpty()) {
                displayToUser("Input cannot be empty. Please try again.");
            }
        } while (inputCommand.isEmpty());
        return inputCommand;
    }

    /**
     * Displays a given message to the user, surrounded by separators for readability.
     *
     * @param message The message to display to the user.
     */
    public static void displayToUser(String message) {
        System.out.print(SEPARATOR);
        System.out.println(message);
        System.out.print(SEPARATOR);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public static void displayWelcomeMessage() {
        displayToUser(WELCOME_MESSAGE);
    }

    /**
     * Displays the exit message when the application ends.
     */
    public static void displayExitMessage() {
        displayToUser(EXIT_MESSAGE);
    }

    /**
     * Displays an acknowledgment message after an expense or income transaction is
     * added or deleted, along with the total count of transactions.
     *
     * @param transaction The details of the transaction.
     * @param addOrDelete Indicates whether the transaction was added or deleted.
     * @param expenseOrIncome Specifies whether the transaction is an expense or income.
     * @param count The total number of transactions of the specified type.
     */
    public static void displayAcknowledgmentMessage(String transaction, String addOrDelete, String expenseOrIncome, int count) {
        String result = "The following " + expenseOrIncome + " transaction has been " + addOrDelete + ":\n"
                + transaction + '\n'
                + "You have " + count + " " + expenseOrIncome + " transaction(s) in total";
        displayToUser(result);
    }
}
