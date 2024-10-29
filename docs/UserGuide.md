# User Guide

## Introduction

{Give a product intro}

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

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
