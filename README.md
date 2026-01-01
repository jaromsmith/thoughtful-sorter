# Thoughtful AI - Technical Challenge (Package Sorter)

## Overview
This repository contains a Java-based implementation for a robotic arm package sorter specified at https://thoughtfulautomation.notion.site/Core-Engineering-Technical-Screen-b61b6f6980714c198dc49b91dd23d695. The system categorizes packages into **STANDARD**, **SPECIAL**, or **REJECTED** stacks based on their physical dimensions and mass.

## Logic Implementation
The solution determines the package status based on the following criteria:
- **Bulky**: Volume >= 1,000,000 cm³ OR any dimension >= 150 cm.
- **Heavy**: Mass >= 20 kg.
- **Sorting Rules**:
    - **REJECTED**: Both bulky and heavy.
    - **SPECIAL**: Either bulky or heavy (but not both).
    - **STANDARD**: Neither bulky nor heavy.

## Project Structure
- `Main.java`: Contains the core `sort` function and input validation logic.
- `MainTest.java`: A standalone test runner that verifies the logic against multiple scenarios and edge cases without requiring external dependencies.

## How to Run

### 1. Compile the Project
Open a terminal in the project directory and run:
```
javac Main.java MainTest.java
```

### 2. Run the Sorter (Manual Example)
To run the default execution example in the Main class:
```
java Main
```

### 3. Run the Test Suite
To verify the implementation against the full suite of requirement-driven test cases:
```
java MainTest
```

## Features
- **Validation**: Strict enforcement of positive values for all inputs; throws IllegalArgumentException for zero or negative dimensions/mass.
- **Boundary Precision**: Explicitly handles "greater than or equal to" boundary conditions as specified in the requirements.
- **Zero-Dependency**: Built using standard Java SE for maximum portability and ease of review.
