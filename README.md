# 💰 Welcome to the Banking App 💰



## 📝 Table of Contents

1.  [About](#about)
2.  [Key Features](#key-features)
3.  [Getting Started](#getting-started)
4.  [Available Commands](#available-commands)
5.  [Notes](#notes)
6.  [Future Improvements](#future-improvements)

-----

## About

This is a simple text based command line bank account program, designed to simulate basic bank teller operations within a single session.

## Key Features

The program allows a bank teller to perform the following core operations:

1.  **Create** a new bank account for a customer.
2.  **Deposit** and **withdraw** cash to/from an account.
3.  **Display** the balance of a specific account.
4.  **View** a statement of all transactions.
5.  **Quit** the program.

-----

## Getting Started

Follow these steps to set up and run the application on your local machine.

1.  **Clone repository:**:

`` git clone git@github.com:leah-massey/Bank-App-Kotlin.git ``

2.  **Build and Run (Local CLI):**:

`` ./gradlew run --console=plain ``


3. **Run Tests:**:

 `` ./gradlew test``

 A report of the test results will be generated in the build/reports/tests/test/ directory.

-----

## Available Commands

Commands are **case-insensitive** and must follow the required format:

| Command            | Format                                | Description                                                    |
|:-------------------|:--------------------------------------|:---------------------------------------------------------------|
| **Create Account** | `NewAccount [First Name] [Last Name]` | Creates a new account. Prints the account number upon success. |
| **Deposit**        | `Deposit [sum] [Account Number]`      | Deposits the specified amount (`sum`) into the given account.  |
| **Withdraw**       | `Withdraw [sum] [Account Number]`     | Withdraws the specified amount (`sum`) from the given account. |
| **Balance**        | `Balance [Account Number]`            | Displays the current balance of the account.                   |
| **Statement**      | `Statement [Account Number]`          | Displays all transactions made to the account.                 |
| **Quit**           | `Quit ⏎`                              | Exits the program and closes the session.                      |

-----

## Notes

* **Data Persistence:** Data is **not persisted**. All account data and transaction history are stored only in memory and will be lost when the program is ended.
* **Account Numbers:** Account numbers start at 10000 and increase incrementally by 1.
* **Inputs:** Commands are not case-sensitive.
* **Names:** Names can contain various characters (numbers, hyphen, etc.). The application does not identify name typos. Double-barrelled names should be separated by a hyphen (e.g., `Mary-Jane`).

-----

