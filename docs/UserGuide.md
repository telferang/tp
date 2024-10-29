# User Guide

## Introduction
BudgetBuddy is a desktop application for managing finance and anything related to it, optimised for
use via a Command Line Interface (CLI)

Words in `UPPER_CASE` are the parameters to be supplied by the users.
* e.g in `list incomes [m/MONTH]`, `MONTH` is a parameter which can be used as `list incomes m/10/2024`
* Items in square brackets are optional, e.g: `list incomes` is a valid command.

Extraneous parameters for commands that do not take in parameters (such as `help`) will be ignored.
* e.g. if the command specified is `help 1234`, it will be interpreted as `help`

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Adding an expense: `add expense`
Adds a new expense to the list of expenses.

Format: `add expense DESCRIPTION a/AMOUNT [d/DATE] [c/CATEGORY]`

* The `DESCRIPTION` is a short description of the expense. All words without a tag will be the description
* The `AMOUNT` is the amount of the expense.
* The `DATE` (Optional) is the date of the expense in DD/MM/YYYY format. If date is not given, current date will be used
* The `CATEGORY` (Optional) is the category of the expense (FOOD, TRANSPORT, ENTERTAINMENT, EDUCATION, UTILITIES and OTHERS). The default category is OTHERS

Example of usage: 

`add expense air ticket a/123.45`

`add expense KFC lunch a/10.50 d/28/10/2024 c/FOOD`

### Deleting an expense: `delete expense`
Delete an expense from the list of expenses.

Format: `delete expense INDEX`

* The `INDEX` is the index of the expense to delete.

Example of usage:

`delete expense 1`

### Adding an income: `add income`
Adds a new income to the list of incomes.

Format: `add income DESCRIPTION a/AMOUNT [d/DATE]`

* The `DESCRIPTION` is a short description of the income. All words without a tag will be the description
* The `AMOUNT` is the amount of the income.
* The `DATE` (Optional) is the date of the expense in DD/MM/YYYY format. If date is not given, current date will be used

Example of usage:

`add income tuition a/123.45`

`add income Tesla stock dividend a/10.50 d/28/10/2024`

### Deleting an income: `delete income`
Delete an income from the list of incomes.

Format: `delete income INDEX`

* The `INDEX` is the index of the income to delete.

Example of usage:

`delete income 1`

### List remaining budgets: `list remaining budget`
Show the budget remaining after deducting the expenses seperated by month and category.

Format: `list remaining budget`

Example of usage:

`list remaining budget`

### List all expenses: `list expenses`
List the summary of expenses. User could additionally specify which summary type the would like to view.
A final line of total expenses based on summary type will be shown to users.

Format: `list expenses [c/CATEGORY m/MONTH]`

* The `CATEGORY` (Optional) is the summary type to search for expenses with the specified category
* The `MONTH` (Optional) is the summary type to search for expenses in the specified month in the format of`m/mm/yyyy`.

Example of usage:
`list expenses`
`list expenses m/10/2024`
`list expenses c/food m/10/2024`

### List all incomes: `list incomes`
List the summary of incomes. User could additionally specify which summary type the would like to view.
A final line of total income based on summary type will be shown to users.

Format: `list incomes [m/MONTH]`

* The `MONTH` (Optional) is the summary type to search for incomes in the specified month in the format of`m/mm/yyyy`.

Example of usage:
`list incomes`
`list incomes m/10/2024`

### Edit pre-existing expenses: `edit expenses`
Edit a pre-existing expense entry details. User can edit category, amount and date of the expense field. 2 set of input
will be required from the user.

Format:
1. `edit expenses INDEX`
2. `[a/AMOUNT c/CATEGORY d/DATE]`

* The `INDEX` is the index of the desired expenses based on list expense that the user wants to edit
* The `AMOUNT` (Optional) is the amount to be updated to
* The `CATEGORY` (Optional) is the category to be updated to
* The `DATE` (Optional) is the date to be updated to in the format of`d/dd/mm/yyyy`
* Note: For second input, at least one of the field must be provided, else it returns back to main menu

Example of usage:
1. `edit expenses 3`
2. `a/100 c/food d/15/10/2024`

### Edit pre-existing incomes: `edit incomes`
Edit a pre-existing income entry details. User can edit amount and date of the expense field. 2 set of input
will be required from the user.

Format:
1. `edit income INDEX`
2. `[a/AMOUNT d/DATE]`

* The `INDEX` is the index of the desired expenses based on list income that the user wants to edit
* The `AMOUNT` (Optional) is the amount to be updated to
* The `DATE` (Optional) is the date to be updated to, in the format of `d/dd/mm/yyyy`
* Note: For second input, at least one of the field must be provided, else it returns back to main menu

Example of usage:
1. `edit income 3`
2. `a/100 d/15/10/2024`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
* List expenses `list expenses [m/MONTH c/CATEGORY]`
* List incomes `list incomes [m/MONTH]`
* Edit expenses `edit expenses INDEX` + `[a/AMOUNT c/CATEGORY d/DATE]`
* Edit incomes `edit incomes INDEX` + `[a/AMOUNT d/DATE]`
