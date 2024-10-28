# Developer Guide

---

## Table of Contents

---



## Acknowledgements

---

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

---

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### 1. Introduction

---

BudgetBuddy is a CLI-based expense, income and budget tracking application. BudgetBuddy is designed to help users manage and monitor their daily and monthly expenses. The system tracks various categories of expenses and allows users to receive insights on their budget and spending patterns.

### 2. Setup Guide

---

This section describes how to setup the coding environment, along with the tools needed to work on BudgetBuddy

#### 2.1 Prerequisites
1. JDK 17
2. Any working IDE that supports Java (Intellij IDEA preferred)
3. XChart

### 3. Design

---

#### 3.1 Architecture
The following diagram shows the rough overview of BudgetBuddy

![OverallDiagram.drawio.png](/docs/diagrams/OverallDiagram.drawio.png)

`BudgetBuddy` is the main class of application which the user can interact with directly.  The input from the user is 
processed by the main class and passed to the `Parser`. `Parser` will check for any valid keywords in the input using 
the `isCommand` method of all the `Command` object. Once the keywords are present, it will pass the input to a 
`Validator` object which validates the command on its formatting and details. Depending on the result of the `Validator`
, the command will be executed in `BudgetBuddy`. The `Validator` and `Object` genre classes utilizes methods and classes
present in the [transaction](/src/main/java/seedu/budgetbuddy/transaction) folder.

#### 3.2 Parser Class
The `Parser` class is to mainly determine whether the user input is valid, and proceed to process the command after.
It uses Boolean Methods to determine the presence of keywords, and then creates a Validator class to process the command
should the keywords be present.

The following are some examples:

| Boolean Methods                        | Check if input starts with | Feature Requires | Creates                                            |
|----------------------------------------|----------------------------|------------------|----------------------------------------------------|
| ListMonthlyExpensesCommand.isCommand() | list monthly expenses      | input            | ListMonthlyExpensesValidator.processCommand(input) |
| AddExpenseCommand.isCommand()          | add expense                | input            | AddExpenseValidator.processCommand(input)          | 

The Parser class is also used to parse lines from the `Storage` .txt file and loads it into the main application upon
restart. This will be covered more under the storage class.

#### 3.3 UI Class

The UI Class is to print out elements of the app in the CLI. It contains many methods used to print general, often-used
messages such as displayWelcomeMessage() and displayExitMessage().

#### 3.4 Command Class

The `Command` class is a parent class which is responsible for checking for input keywords. It has multiple subclasses,
which corresponds to a specific function of the application. Each of its subclass checks for specific keywords in the
user input. If the input is valid as checked by the `isCommand()` method, it will call its corresponding validator.

#### 3.5 Validator Classes

The Validator classes are a group of classes which is responsible for further checks on input, such as whether it has
the right formatting and contains all necessary details. It will return different subclasses of the command object
depending on whether the input is valid or not. Once the object is instantiated and returned, the `main` class will call
`execute` to execute the feature.

##### 3.4.1 and 3.5.1 XML Sequence Diagram

The following sequence diagram shows the process of what happens when a user input is passed through the application,
until it gets executed.

**Note**: 
1. Validator class is not a parent class, but we have grouped it under validators to prevent cluttering of 
the diagrams. 
2. Validator also returns different type of Command Classes depending on the validity of input, which has been omitted
for the same reason as above.

![CommandClass.drawio.png](/docs/diagrams/CommandClass.drawio.png)

### 4. Implementation

#### 4.1 Add Budget Feature
The Add Budget Feature allows users to add budgets for different categories. This is handled by the AddBudgetCommand
class, initialised by the Parser class.

* The Parser processes user input and creates an AddBudgetCommand object with parameters such as amount, category, and 
date.
* The AddBudgetValidator ensures that inputs are valid, checking for correct amounts, valid categories, and 
proper date format.
* If validation passes, the budget is added; otherwise, an error is raised.

#### 4.2 Deduct Budget Feature
The Deduct Budget Feature allows users to deduct an amount from an existing budget. This is managed by the
DeductBudgetCommand class, initialised by the Parser class.

* The Parser processes user input and creates a DeductBudgetCommand object with the amount, category, and 
date of the deduction.
* The DeductBudgetValidator checks if the deduction is valid, ensuring the budget exists, the date is correct, and 
there are sufficient funds.
* If validation passes, the specified amount is deducted from the budget; otherwise, an error is raised.

#### 4.3 List Budget Feature
The List Budget Feature enables users to view all existing budgets or filter them based on specific criteria, 
such as date. This feature is managed by the ListBudgetCommand class, which is initialised by the Parser class.

* The Parser processes user input and creates a ListBudgetCommand object with parameters such as the date for filtering.
* The ListBudgetValidator checks if the list request is valid, verifying the format of input parameters.
* If validation passes, the ListBudgetCommand retrieves and displays the budgets that match the criteria through the UI; 
otherwise, an error message is shown.

#### 4.4 Search Expense Feature
The Search Expense Feature enables users to search for specific expenses based on a description provided by the 
user. This feature is managed by the `SearchExpensesCommand` class, initialized by the `Parser` class, with the help
of a helper class `SearchExpenseValidator` to validate and extract the user description. 
The `SearchExpensesCommand` object is then created with the keyword as an attribute. The class attributes and their
relevance is as follows: 

|Variable Name| Variable Type | Relevance |
|-------------|---------------|-----------|
|keyword| String | Description to find in expenses|

The BudgetBuddy class then calls the `execute()` method of the `SearchExpenseCommand` object which uses the 
`searchExpenses()` method in the `ExpenseManager` class, displaying the result to the user using the `Ui` class
`displayToUser()` method.

Below is a sequence diagram representing the execution of the Search Expense interaction: 
![SearchExpenseSequenceDiagram.drawio.png](diagrams/SearchExpenseSequenceDiagram.drawio.png)
Process Overview: 
1. The user issues a command to search for a specific expense i.e. `search expense Japan`. BudgetBuddy parses this
input with the help of the `Parser` class.
2. The `Parser` calls the `isCommand()` method of the `SearchExpenseCommand` class, to check if the user input 
starts with "search expense". 
3. If the user input starts with "search expense", the `Parser` then calls the `processCommand()` method of 
a helper class, `SearchExpenseValidator` to extract the description to be filtered on.
4. The `processCommand()` method above returns a new `SearchExpenseCommand` object initialized with the description
extracted as the `keyword` attribute.
5. BudgetBuddy then calls the `execute()` method of the `SearchExpenseCommand` object. 
6. If the keyword attribute is an empty string, the `SearchExpenseCommand` object calls the `searchEmptyMessage()`
method of the `Ui` class, displaying an error message to the user that no descriptor was provided.
7. Else, the `SearchExpenseCommand` object calls the `searchExpenses()` method of the `ExpenseManager` class,
filtering the `expenses` ArrayList and returning a String containing all expenses with the given descriptor
in the description of the expenses. The `SearchExpenseCommand` object then calls the `displayToUser()` method in `Ui`,
displaying this String to the user.

# Appendix

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ... | I want to ...                         | So that I can ...                                           |
|---------|----------|---------------------------------------|-------------------------------------------------------------|
| v1.0    | new user | see usage instructions                | refer to them when I forget how to use the application      |
| v1.0    | user     | add and delete income                 | track my incomes                                            |
| v1.0    | user     | add and delete expenses               | track my expenses                                           |
| v1.0    | user     | view a history of my past income      | see how my income changed over time                         |
| v1.0    | user     | view a history of my past expenses    | see how my expenses changed over time                       |
| v1.0    | user     | categorise my expenses                | understand where my money is going                          | 
| v1.0    | user     | search for specific expense entries   | quickly find past transactions                              |
| v1.0    | user     | view a history of my past budgets     | see how my spending has changed over time                   |
| v1.0    | user     | save and load my data automatically   | view my past entries                                        |
| v2.0    | user     | view the trends of my monthly expense | analyse my expense pattern                                  |
| v2.0    | user     | view the total expenses for one month | see a summary of my expenses for the month                  |
| v2.0    | user     | view my monthly savings               | plan for future expenses                                    |
| v2.0    | user     | see where I am spending my money      | better manage my expenses                                   |
| v2.0    | user     | find a to-do item by name             | locate a to-do without having to go through the entire list |
| v2.0    | user     | edit an exisiting expense entry       | correct any mistakes made to an expense entry               |
| v2.0    | user     | edit an exisiting income entry        | correct any mistakes made to an income entry                |
| v2.0    | user     | track expenses in multiple currencies | manage international transactions                           |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
