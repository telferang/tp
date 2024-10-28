package seedu.budgetbuddy;

import java.util.Scanner;

/**
 * The Ui class handles all user interactions by displaying messages to the user and
 * receiving input from them.
 */
public class Ui {

    public static final String SEPARATOR = "========================================================";
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
     * Retrieves the user's command input.
     * If an empty command is received, it will end the command and give an
     * acknowledgment message.
     *
     * @return The user’s command as a trimmed {@code String}.
     */
    public static String getUserEditFields(){
        String inputCommand;

        do {
            System.out.print("Enter edit Field: ");
            inputCommand = scanner.nextLine().trim();

            if (inputCommand.isEmpty()) {
                displayToUser("Empty Input Detected, Exiting change menu.");
                return "";
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
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
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
     * Displays a message indicating that a budget has been deleted because its amount has reached zero,
     * and shows the current total number of remaining budgets.
     *
     * @param transaction The details of the deleted budget.
     * @param count The total number of budgets after the deletion.
     */
    public static void displayBudgetDeletedMessage(String transaction, int count) {
        String result = "The following budget has been deleted as its amount reached zero:\n"
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
                "4. List expenses based on category and month. Note(optional): category - c/, month - m/MM/yyyy\n" +
                "Example:\nlist expenses c/food m/10/2024\n" +
                "5. List income based on month. Note: month (optional) - m/MM/yyyy\n" +
                "Example:\nlist incomes m/10/2024\n" +
                "6. Add budget for current month. Note: month - m/MM/yyyy \n" +
                "Example:\nadd budget a/1000 m/09/2024\n" +
                "7. Deduct budget for current month. Note: month - m/MM/yyyy \n" +
                "Example:\ndeduct budget a/500 m/10/2024\n" +
                "8. list budget for specific month. Note: month - m/MM/yyyy \n" +
                "Example:\nlist budget m/05/2024\n" +
                "9. list budget for the 12 most recent entries. \n" +
                "Example:\nlist budget\n" +
                "10. edit expense fields (with Category, Amount, Date).\n" +
                "Example:\nedit expenses 3\n" +
                "a/1000 d/12/10/2024 c/food\n" +
                "11. Exit app. \n" +
                "Example:\nbye";
        displayToUser(message);
    }

    /**
     * Prints message to user if there is no description provided in search.
     */
    public static void searchEmptyMessage(){
        String result = "Please key in a valid descriptor to search.";
        displayToUser(result);
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }
}
