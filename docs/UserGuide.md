# User Guide

---

## Table of Contents

---

[Introduction](#introduction) <br>
[Quick Start](#quick-start) <br>
[Features](#features-) <br>
&nbsp;&nbsp;1. [Expense](#expense) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Add Expense](#1-adding-an-expense-add-expense) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Delete Expense](#2-deleting-an-expense-delete-expense) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [List Expenses](#3-list-all-expenses-list-expenses) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Edit Expenses](#4-edit-pre-existing-expenses-edit-expenses) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Display Monthly Expenses Chart](#5-display-monthly-expenses-chart-display-monthly-expenses) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Display Expenses for the Month with Categories Chart](#6-display-expenses-for-the-month-with-categories-chart-display-expenses-with-categories) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Search Expense](#7-search-expense-search-expense) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Breakdown Expenses](#8-breakdown-expenses-breakdown-expenses) <br>
&nbsp;&nbsp;2. [Income](#income) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Add Income](#1-adding-an-income-add-income) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Delete Income](#2-deleting-an-income-delete-income) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Edit Income](#3-edit-pre-existing-incomes-edit-incomes) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [List Income](#4-list-all-incomes-list-incomes) <br>
&nbsp;&nbsp;3. [Budget](#budget) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Add Budget](#1-adding-a-budget-add-budget) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Deduct Budget](#2-deducting-a-budget-deduct-budget) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [List Budget](#3-listing-budgets-list-budgets) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [List Remaining Budget](#4-list-remaining-budgets-list-remaining-budget) <br>
&nbsp;&nbsp;4. [Saving](#saving) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Display Savings](#1-display-savings-display-savings-m) <br>
&nbsp;&nbsp;5. [Income Spent](#income-spent) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&bull; [Display Income Spent](#1-display-income-spent-display-income-spent) <br>
&nbsp;&nbsp;6. [Miscellaneous](#miscellaneous) <br>
[FAQ](#faq) <br>
[Command Summary](#command-summary) <br>


## Introduction
BudgetBuddy is a desktop application for managing finance and anything related to it, optimised for
use via a Command Line Interface (CLI)

Words in `UPPER_CASE` are the parameters to be supplied by the users.
* e.g in `list incomes [m/MONTH]`, `MONTH` is a parameter which can be used as `list incomes m/10/2024`
* Items in square brackets are optional, e.g: `list incomes` is a valid command.

Extraneous parameters for commands that do not take in parameters (such as `help`) will be ignored.
* e.g. if the command specified is `help 1234`, it will be interpreted as `help`

## Quick Start

1. Download the JAR File from our GitHub Release and put into your preferred directory.
2. Open your terminal and cd to that directory.
3. Enter `java -jar BudgetBuddy.jar` and enjoy the app!

## Features 

---

### Expense

---

#### 1. Adding an expense: `add expense`
Adds a new expense to the list of expenses.

Format: `add expense DESCRIPTION a/AMOUNT [d/DATE] [c/CATEGORY]`

* The `DESCRIPTION` is a short description of the expense. All words without a tag will be the description
* The `AMOUNT` is the amount of the expense.
* The `DATE` (Optional) is the date of the expense in DD/MM/YYYY format. If date is not given, current date will be used
* The `CATEGORY` (Optional) is the category of the expense (FOOD, TRANSPORT, ENTERTAINMENT, EDUCATION, UTILITIES and OTHERS). The default category is OTHERS

Example of usage:

`add expense air ticket a/123.45`

`add expense KFC lunch a/10.50 d/28/10/2024 c/FOOD`

#### 2. Deleting an expense: `delete expense`
Delete an expense from the list of expenses.

Format: `delete expense INDEX`

* The `INDEX` is the index of the expense to delete.

Example of usage:

`delete expense 1`

#### 3. List all expenses: `list expenses`
List the summary of expenses. User could additionally specify which summary type the would like to view.
A final line of total expenses based on summary type will be shown to users.

Format: 
1. `list expenses`
2. `list expenses [m/MONTH]`
3. `list expenses [m/MONTH] [c/CATEGORY]`

* The `CATEGORY` (Optional) is the summary type to search for expenses with the specified category
* The `MONTH` (Optional) is the summary type to search for expenses in the specified month in the format of`m/mm/yyyy`.

Example of usage:
`list expenses`
`list expenses m/10/2024`
`list expenses m/10/2024 c/food`

#### 4. Edit pre-existing expenses: `edit expenses`
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

#### 5. Display Monthly Expenses Chart: `display monthly expenses`
Display a XY-Chart of your monthly expenses. The X-Axis will be the months of the year while the Y-Axis
is the total expenses for that particular month.

Format:
`display monthly expenses y/YEAR`

* The `YEAR` is the year of the expenses to be displayed
* The format of `YEAR` should be `YYYY`

Example of Usage:

`display monthly expenses y/2024`

Below is an example of the chart displayed.

![XY-Chart.png](images/XY-Chart.png)

#### 6. Display Expenses for the Month with Categories Chart: `display expenses with categories`
Display a PieChart of your expenses for the month, which is sliced by categories. The legend of the PieChart is the 
expenses for each category, and the percentage that each slice takes is on the PieChart itself.

Format:
`display expenses with categories m/MONTH`

* `MONTH` is the month of the expenses to be displayed
* `MONTH` should be in the form `MM/YYYY`

Example of Usage:

`display expenses with categories m/09/2024`

Below is an example of the chart displayed.

![PieChart.png](images/PieChart.png)

#### 7. Search Expense: `search expense`
Displays the expense entries based on a certain keyword provided by the user. The relevant expense entries are then
filtered based on their description.

Format: `search expense KEYWORD(S)`

* Displays expense entries with descriptions containing the keyword(s) provided by the user.
* If no keyword is provided by the user, an error message will show up, prompting the user to key in a valid keyword.
* If no expenses are found based on the keyword provided, a message indicating that no expense entries are found will
  show up.
* Filtering on the provided keyword(s) is case insensitive.

Example of usage:
1. `search expense Japan`

#### 8. Breakdown Expenses: `breakdown expenses`
Displays a breakdown of the user’s expenses, by category. The feature displays the total expenditure per category,
along with a percentage indicating the percentage of total expense spent per category.

Format: `breakdown expenses`

Example of usage:
1. `breakdown expenses`

### Income

---

#### 1. Adding an income: `add income`
Adds a new income to the list of incomes.

Format: `add income DESCRIPTION a/AMOUNT [d/DATE]`

* The `DESCRIPTION` is a short description of the income. All words without a tag will be the description
* The `AMOUNT` is the amount of the income.
* The `DATE` (Optional) is the date of the expense in DD/MM/YYYY format. If date is not given, current date will be used

Example of usage:

`add income tuition a/123.45`

`add income Tesla stock dividend a/10.50 d/28/10/2024`

#### 2. Deleting an income: `delete income`
Delete an income from the list of incomes.

Format: `delete income INDEX`

* The `INDEX` is the index of the income to delete.

Example of usage:

`delete income 1`

#### 3. Edit pre-existing incomes: `edit incomes`
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

#### 4. List all incomes: `list incomes`
List the summary of incomes. User could additionally specify which summary type the would like to view.
A final line of total income based on summary type will be shown to users.

Format: `list incomes [m/MONTH]`

* The `MONTH` (Optional) is the summary type to search for incomes in the specified month in the format of`m/mm/yyyy`.

Example of usage:
`list incomes`
`list incomes m/10/2024`

### Budget

---

#### 1. Adding a budget: `add budget`
Adds an amount to the month’s current budget. Budget is cumulative for the current month.

Format: `add budget a/AMOUNT [m/MONTH] [c/CATEGORY]`

* The `AMOUNT` is the amount of the budget to be added.
* The `MONTH` (Optional) is the month of the budget in MM/YYYY format. If month is not given, current month will be used.
* The `CATEGORY` (Optional) is the category of the budget (FOOD, TRANSPORT, ENTERTAINMENT, EDUCATION, UTILITIES and OTHERS). 
The default category is OTHERS.

Examples of usages:
1. `add budget a/800`  
2. `add budget a/700 m/11/2024 c/TRANSPORT`

#### 2. Deducting a budget: `deduct budget`
Deducts an amount to the month’s current budget. Budget cannot go below 0.

Format: `deduct budget a/AMOUNT [m/MONTH] [c/CATEGORY]`

* The `AMOUNT` is the amount of the budget to be deducted.
* The `MONTH` (Optional) is the month of the budget in MM/YYYY format. If month is not given, current month will be used.
* The `CATEGORY` (Optional) is the category of the budget (FOOD, TRANSPORT, ENTERTAINMENT, EDUCATION, UTILITIES and OTHERS).
  The default category is OTHERS.

Examples of usages:
1. `deduct budget a/1500`  
2. `deduct budget a/400 m/09/2024 c/FOOD`

#### 3. Listing budgets: `list budgets`
View all budgets, up to the latest 12 budgets. Able to view the budget for a specific month as well.

Format: `list budgets [m/MONTH]`

* The `MONTH` (Optional) is the month of the budget in MM/YYYY format. If month is not given,
  up to 12 latest budgets will be listed.

Examples of usages:
1. `list budgets`  
2. `list budgets m/09/2024`

#### 4. List remaining budgets: `list remaining budget`
Show the budget remaining after deducting the expenses seperated by month and category.

Format: `list remaining budget`

Example of usage:

`list remaining budget`

### Saving

---

#### 1. Display Savings: `display savings [m/]`
Displays the total savings of the user, calculated based on Total Income - Total Expense. User can choose to display
total savings, or a more detailed savings by month based on their input.

Format: `display savings [m/]`
* If m/ is not provided, total savings of the user is displayed.

Examples of usages:
1. `display savings m/`
2. `display savings`

### Income Spent

---

#### 1. Display Income Spent: `display income spent`
Displays the percentage of income spent for the specified month. Spending only includes total expenses of the month.

Format: `display income spent [m/MONTH]`
* The MONTH (Optional) is the month of the budget in MM/YYYY format. If month is not given, current month will be used.

Examples of usages:
1. `display income spent`
2. `display income spent m/10/2024`

### Miscellaneous

---

#### 1. Exit Program: `bye`

Exits the program and save automatically.

Format: `bye`

#### 2. Help Sheet: `help`

Prints the list of commands for the user.

Format: `help`

## FAQ

---

**Q**: How do I transfer my data to another computer? 

**A**: Copy the BudgetBuddy.txt created under the data folder under the same directory as your JAR file. Transfer the .txt 
file to the other computer and replace it with the new .txt file created by your other computer.

**Q**: What if some of my lines in my file has the wrong formatting?

**A**: BudgetBuddy automatically skip lines that are corrupted in the data .txt file, so the program will not crash.
To fix the corrupted line, you can either edit the data file yourself to correct the format, or simply delete the line
and execute the command in the JAR file again.

**Q**: Can I add or create my own category type?

**A**: As of the current version, creating own category type is currently **not** supported. All features that requires
a category will be grouped as `OTHERS` if no category is provided.o

**Q**: What would the PieChart show if I have no expenses for the month when I am using `display expenses with categories`?

**A**: The PieChart will just be empty with the Legend showing that value of all categories to be 0. 

**Q**: What would the XY-Chart show if I have no expenses for some months of the year when I am using `display monthly expenses`?

**A**: The value at that particular month will just be 0.

## Command Summary

---

### Expense

- **Add Expense**: `add expense DESCRIPTION a/AMOUNT [d/DATE] [c/CATEGORY]`
- **Delete Expense**: `delete expense INDEX`
- **List Expenses**: `list expenses [c/CATEGORY m/MONTH]`
- **Edit Expense**:
  1. `edit expenses INDEX`
  2. `[a/AMOUNT c/CATEGORY d/DATE]`
- **Display Monthly Expenses Chart**: `display monthly expenses y/YEAR`
- **Display Expenses with Categories Chart**: `display expenses with categories m/MM/YYYY`
- **Search Expense**: `search expense KEYWORD(S)`
- **Breakdown Expenses**: `breakdown expenses`

### Income

- **Add Income**: `add income DESCRIPTION a/AMOUNT [d/DATE]`
- **Delete Income**: `delete income INDEX`
- **Edit Income**:
  1. `edit income INDEX`
  2. `[a/AMOUNT d/DATE]`
- **List Incomes**: `list incomes [m/MONTH]`

### Budget

- **Add Budget**: `add budget a/AMOUNT [m/MONTH] [c/CATEGORY]`
- **Deduct Budget**: `deduct budget a/AMOUNT [m/MONTH] [c/CATEGORY]`
- **List Budgets**: `list budgets [m/MONTH]`
- **List Remaining Budget**: `list remaining budget`

### Saving

- **Display Savings**: `display savings [m/]`


### Income Spent

- **Display Income Spent**: `display income spent [m/MONTH]`

