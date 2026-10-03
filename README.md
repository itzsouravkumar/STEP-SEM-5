<a id="readme-top"></a>

<div align="center">
  <h1 align="center">Global Digital Bank</h1>
  <p align="center">
    A comprehensive Object-Oriented Programming (OOP) project designed for STEP Class Semester 5.
  </p>
</div>

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
        <li><a href="#system-architecture">System Architecture</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation-and-execution">Installation and Execution</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

## About The Project

The Global Digital Bank is a robust Java-based banking simulation built progressively through a series of structured developmental activities. The primary objective of this Semester 5 STEP project is to demonstrate the practical application of advanced Object-Oriented Programming principles, robust custom exception handling, and enterprise-level design patterns.

Rather than developing the entire system in a single monolithic phase, the architecture was evolved step-by-step. The system transitioned from simple conditional logic and boolean return states to a fully factory-driven, template-controlled architecture featuring dynamic business rules.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

* Java SE (JDK 8 or higher)
* Standard Java Libraries (java.util)
* Object-Oriented Principles (Encapsulation, Inheritance, Polymorphism, Abstraction)
* Design Patterns (Factory Pattern, Template Method Pattern)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### System Architecture

The project is highly decoupled and organized into distinct architectural layers:

* **Domain Layer (com.gdb.domain):** Contains the core banking models. The IAccount interface enforces a strict contract across all account types. The AbstractAccount base class implements common fields and dictates transaction workflows utilizing the Template Method Pattern. Subclasses such as SavingsAccount and CurrentAccount implement specific business rules.
* **Exceptions Layer (com.gdb.exceptions):** A comprehensive custom exception hierarchy extending AccountException. This cleanly separates business rule violations (e.g., MinimumBalanceViolationException and InsufficientBalanceException) from standard system errors.
* **Factory and Rules Engine:** Object creation is centralized within the AccountFactory. Business rules, including minimum balances and interest rates, are completely decoupled from entity classes and dynamically fetched at runtime from the AccountRulesEngine based on customer tenure.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started

Follow these steps to set up and run a local copy of the project.

### Prerequisites

Ensure the Java Development Kit (JDK) is installed and configured in your system's PATH.

* Java JDK (Version 8 or higher)
* Git

### Installation and Execution

1. Clone the repository
   ```sh
   git clone https://github.com/itzsouravkumar/STEP-SEM-5.git
   ```

2. Navigate to the project directory
   ```sh
   cd JAVA
   ```

3. Compile and Run the Comprehensive Showcase

   **Windows (Command Prompt):**
   ```cmd
   mkdir bin
   javac -d bin src\com\gdb\domain\*.java src\com\gdb\tests\*.java src\com\gdb\exceptions\*.java src\com\gdb\command\*.java src\com\gdb\logging\*.java src\com\gdb\service\*.java src\com\gdb\GlobalDigitalBankApp.java
   java -cp "bin;src\main\resources" com.gdb.GlobalDigitalBankApp
   ```

   **Windows (PowerShell):**
   ```powershell
   if (!(Test-Path bin)) { New-Item -ItemType Directory -Path bin }
   javac -d bin (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
   java -cp "bin;src\main\resources" com.gdb.GlobalDigitalBankApp
   ```

   **Linux and macOS (Terminal / Zsh):**
   ```bash
   mkdir -p bin
   find src -name "*.java" -print0 | xargs -0 javac -d bin
   java -cp "bin:src/main/resources" com.gdb.GlobalDigitalBankApp
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Usage

A dedicated entry point, GlobalDigitalBankApp.java, has been created to act as a Comprehensive Showcase. Executing this application demonstrates a unified output containing:

* **Factory Pattern Execution:** Dynamic instantiation of Savings and Current accounts.
* **Rules Engine Integration:** Minimum balances and interest rates dynamically applied based on the customer's tenure.
* **Exception Handling:** Graceful trapping and displaying of business rule violations, such as attempting to breach a minimum balance or providing an invalid PIN.
* **Polymorphism:** Iterating over a collection of IAccount objects to calculate total bank funds managed.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Roadmap

The project architecture was constructed over a series of progressive activities. The complete roadmap tracking these accomplishments is detailed below.

* [x] **Activity 1:** Creating the basic Account Class (Entity/Model)
* [x] **Activity 2:** Testing the basic Account Class
* [x] **Activity 3:** Enhancing the Account Class (Age/Type validations, boolean return states)
* [x] **Activity 4:** Testing the Enhanced Account Class
* [x] **Activity 5:** Introducing Custom Exceptions (Replacing boolean logic)
* [x] **Activity 6:** Testing the Account Class with Exceptions
* [x] **Activity 7:** Subclassing the Account Class (Savings, Current, Salary, FD)
* [x] **Activity 8:** Testing Subclasses and Polymorphism
* [x] **Activity 9:** Abstract Classes and Template Method Pattern (Workflow enforcement)
* [x] **Activity 10:** Banking Operations with Abstract Accounts (Transfers, Cycles)
* [x] **Activity 11:** Interface and Factory Pattern (Centralized creation)
* [x] **Activity 12:** Factory-Driven Banking System (Interface manipulation)
* [x] **Activity 13.1:** Account Rules Engine (In-Memory Maps and Tenure Lookups)
* [x] **Activity 13.2:** Dynamic Account Rules Integration (Linking Factory to Rules Engine)
* [x] **Activity 14:** External Properties Rules Engine (Properties-Driven Architecture)
* [x] **Activity 15:** Transfer with Daily Limits (Cross-Account Operations)
* [x] **Activity 16:** Transaction Model (Audit Logging and Immutable Objects)
* [x] **Activity 17:** Command Pattern + File Logging (Encapsulated Operations and Object Serialization)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Contact

Student Name / Contributor
STEP Class Semester 5

Project Link: [Github](https://github.com/itzsouravkumar/STEP-SEM-5)

<p align="right">(<a href="#readme-top">back to top</a>)</p>
