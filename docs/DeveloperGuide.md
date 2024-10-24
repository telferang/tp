# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### 1. Introduction

BudgetBuddy is a CLI-based expense, income and budget tracking application. BudgetBuddy is designed to help users manage and monitor their daily and monthly expenses. The system tracks various categories of expenses and allows users to receive insights on their budget and spending patterns.

### 2. Setup Guide
This section describes how to setup the coding environment, along with the tools needed to work on BudgetBuddy

#### 2.1 Prerequisites
1. JDK 17
2. Any working IDE that supports Java (Intellij IDEA preferred)

### 3. Design

#### 3.1 Architecture

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ...                         | So that I can ...                                           |
|--------|----------|---------------------------------------|-------------------------------------------------------------|
|v1.0| new user | see usage instructions                | refer to them when I forget how to use the application      |
|v1.0| user     | view a history of my past income      | see how my income changed over time                         |
|v1.0| user     | view a history of my past expenses    | see how my expenses changed over time                       |
|v1.0| user     | categorise my expenses                | understand where my money is going                          | 
|v1.0| user     | search for specific expense entries   | quickly find past transactions                              |
|v1.0| user     | view a history of my past budgets     | see how my spending has changed over time                   |
|v2.0| user     | view my monthly savings               | plan for future expenses                                    |
|v2.0| user     | see where I am spending my money      | better manage my expenses                                   |
|v2.0| user     | find a to-do item by name             | locate a to-do without having to go through the entire list |
|v2.0| user     | edit an exisiting expense entry       | correct any mistakes made to an expense entry               |
|v2.0| user     | edit an exisiting income entry        | correct any mistakes made to an income entry                |
|v2.0| user     | track expenses in multiple currencies | manage international transactions                           |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

### 4. Implementation

#### 4.1 Add Budget Feature
The Add Budget Feature allows users to add budgets for different categories. This is handled by the AddBudgetCommand 
class, initialized by the Parser class.

- The Parser processes user input and creates an AddBudgetCommand object with parameters such as amount, category, 
and date.
- The AddBudgetValidator ensures that inputs are valid, checking for correct amounts, valid categories, 
and proper date format.
- If validation passes, the budget is added; otherwise, an error is raised.

#### 4.2 Deduct Budget Feature
The Deduct Budget Feature allows users to deduct an amount from an existing budget. This is managed by the 
DeductBudgetCommand class, initialized by the Parser class.

- The Parser processes user input and creates a DeductBudgetCommand object with the amount, category, 
and date of the deduction.
- The DeductBudgetValidator checks if the deduction is valid, ensuring the budget exists, the date is correct, 
and there are sufficient funds.
- If validation passes, the specified amount is deducted from the budget; otherwise, an error is raised.
