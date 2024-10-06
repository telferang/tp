package seedu.budgetbuddy;

import java.util.Scanner;

/**
 * The Ui class handles all user interactions by displaying messages to the user and
 * receiving input from them.
 */
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
    public static void displayAcknowledgmentMessage(String transaction, String addOrDelete, String expenseOrIncome,
                                                    int count) {
        String result = "The following " + expenseOrIncome + " transaction has been " + addOrDelete + ":\n"
                + transaction + '\n'
                + "You have " + count + " " + expenseOrIncome + " transaction(s) in total.";
        displayToUser(result);
    }

    /**
     * Displays a message regarding a budget transaction, indicating updating the amount,
     * and shows the current total number of budgets.
     *
     * @param transaction The details of the budget transaction.
     * @param count The total number of budgets after the transaction.
     */
    public static void displayBudgetTransactionMessage(String transaction, int count) {
        String result = "The following budget amount has been updated:\n"
                + transaction + '\n'
                + "You have " + count + " budget(s) in total.";
        displayToUser(result);
    }

    /**
     * Displays entire help message, acts as a help guide for new users.
     */
    public static void displayHelpMessage(){
        String message = "1. Add expense/income entry. Note: amount - a/, date - d/, category - c/ \n" +
                "Examples:\nadd expense plane ticket to Japan a/1000 d/25/12/2024 c/transport \n" +
                "add income tuition fees " + "a/1000 d/13/12/2024 \n" +
                "2. Delete expense/income entry as shown in the income and expense lists." +
                "Examples:\ndel expense 1 \n"  + "del income 2 \n" +
                "3. Tag new category to expense. \n" + "Example:\n tag expense 2 /c food \n" +
                "4. List all expenses/income. \n" + "Examples:\nlist expenses \n" + "list income\n" +
                "5. Display expenses based on category and month. Note: category - c/, month (optional) - m/\n" +
                "Example:\ndisplay expenses c/food m/10\n" +
                "6. Add budget for current month. Note: month - m/MM/yyyy \n" +
                "Example:\nadd budget a/1000 m/09/2024\n" +
                "7. Deduct budget for current month. Note: month - m/MM/yyyy \n" +
                "Example:\ndeduct budget a/500 m/10/2024\n" +
                "8. View budget for specific month. Note: month - m/MM/yyyy \n" +
                "Example:\nview budget m/05/2024\n" +
                "9. Exit app. \n" +
                "Example:\nbye\n";
        displayToUser(message);
    }
}
