# Student Scholarship Eligibility System

A beginner-friendly, modular Java console application designed to evaluate student scholarship eligibility based on **academic performance, attendance percentage, and annual family income**.

---

## Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Eligibility & Scholarship Rules](#eligibility--scholarship-rules)
  - [Grading System](#grading-system)
  - [Scholarship Categories](#scholarship-categories)
  - [Rejection Reasons](#rejection-reasons)
- [Project Architecture & Modular Structure](#project-architecture--modular-structure)
- [Java Concepts Demonstrated](#java-concepts-demonstrated)
- [Project Directory Structure](#project-directory-structure)
- [How to Run](#how-to-run)
  - [macOS / Linux (One-Shot Script)](#macos--linux-one-shot-script)
  - [Windows (One-Shot Script)](#windows-one-shot-script)
  - [Manual Compilation and Execution](#manual-compilation-and-execution)
- [Menu Overview](#menu-overview)
- [Sample Execution & Output](#sample-execution--output)
- [Viva Voce Preparation (Q&A)](#viva-voce-preparation-qa)

---

## Overview

Educational institutions require a transparent, fair, and rule-based system to process student applications for scholarships. This system automates the evaluation pipeline:
1. Registers multiple students.
2. Accepts subject-wise marks dynamically.
3. Computes total marks, academic percentage, and letter grade.
4. Checks compound eligibility conditions using logical operators (`&&`).
5. Assigns categorized scholarship grants (Merit, Academic, General, or Not Eligible).
6. Displays granular rejection reasons if a student is disqualified.
7. Supports full record management (**Update** and **Delete**).
8. Produces individual report cards and an aggregated **Class Summary**.

---

## Key Features

- **Menu-Driven Interface**: Interactive 9-option menu powered by a `do-while` loop and `switch` statement.
- **Multiple Student Processing**: Processes any number of students using parallel arrays.
- **Dynamic Subject Marks**: Supports varying subject counts per student using 2D arrays (`int[][]`).
- **Comprehensive Input Validation**: Robust loops prevent crashes caused by negative numbers, out-of-range marks, or non-numeric tokens.
- **Update Student Record**: Update name, attendance, income, marks, or all details with automatic result recalculation.
- **Delete Student Record**: Remove student records cleanly via array element shifting with confirmation checks.
- **Class Summary & Topper Tracking**: Real-time aggregation of eligible counts, category breakdown, highest percentage, and class topper.

---

## Eligibility & Scholarship Rules

### Grading System

| Percentage Range | Grade | Performance Level |
| :---: | :---: | :--- |
| **90% – 100%** | **A** | Outstanding |
| **80% – 89%** | **B** | Very Good |
| **70% – 79%** | **C** | Good |
| **60% – 69%** | **D** | Satisfactory |
| **Below 60%** | **F** | Fail / Unsatisfactory |

---

### Scholarship Categories

A student must satisfy **all three baseline conditions** to be eligible:
$$\text{Percentage} \ge 75\% \quad \text{AND} \quad \text{Attendance} \ge 75\% \quad \text{AND} \quad \text{Family Income} \le \text{Rs. } 3,00,000$$

When eligible, students are categorized into the following scholarship tiers:

| Category | Academic % | Attendance % | Max Family Income | Scholarship Amount |
| :--- | :---: | :---: | :---: | :---: |
| **Merit Scholarship** | $\ge 90\%$ | $\ge 85\%$ | $\le \text{Rs. } 3,00,000$ | **Rs. 50,000** |
| **Academic Scholarship** | $\ge 80\%$ | $\ge 75\%$ | $\le \text{Rs. } 3,00,000$ | **Rs. 30,000** |
| **General Scholarship** | $\ge 75\%$ | $\ge 75\%$ | $\le \text{Rs. } 3,00,000$ | **Rs. 15,000** |
| **Not Eligible** | Any other condition | Any other condition | $> \text{Rs. } 3,00,000$ | **Rs. 0** |

---

### Rejection Reasons

If a student is disqualified, the system specifies **every individual reason**:
- `- Academic percentage is below 75%.`
- `- Attendance is below 75%.`
- `- Annual family income exceeds Rs. 3,00,000.`

---

## Project Architecture & Modular Structure

The codebase is organized into **clean, modular sections**:

```text
ScholarshipEligibilitySystem
├── Menu Display & Control Loop (do-while & switch)
│
├── Part 1: Student Registration (registerStudents)
│   └── Captures batch size, names, roll numbers, attendance, and income
│
├── Part 2: Marks Entry (enterStudentMarks)
│   └── Allocates subject arrays, records and validates marks (0-100)
│
├── Part 3: Academic Calculations (calculateAcademicResults)
│   ├── calculateTotalMarks(int[] marks)
│   ├── calculatePercentage(int total, int subjects)
│   └── calculateGrade(double percentage)
│
├── Part 4: Scholarship Evaluation (checkScholarshipEligibility)
│   ├── checkEligibility(percentage, attendance, income)
│   ├── getScholarshipCategory(percentage, attendance, income)
│   ├── getScholarshipAmount(String category)
│   └── displayRejectionReasons(percentage, attendance, income)
│
├── Part 5: Reporting & Aggregation
│   ├── displayStudentResult() -> Individual formatted report cards
│   └── displayClassSummary()  -> Class-wide statistics & Topper
│
├── Part 6: Record Management (CRUD)
│   ├── updateStudentDetails() -> Modify details with auto-recalculation
│   └── deleteStudentRecord()  -> Safe array shifting & confirmation
│
└── Helpers: Input Validation
    ├── readPositiveInt()
    ├── readInt(min, max)
    ├── readDouble(min, max)
    └── readNonNegativeDouble()
```

---

## Java Concepts Demonstrated

| Concept | Implementation in Code |
| :--- | :--- |
| **`Scanner`** | Reading primitive tokens and full lines (`nextInt`, `nextDouble`, `nextLine`, `hasNextInt`). |
| **1D Arrays** | Storing parallel student attributes (`names[]`, `rollNumbers[]`, `percentages[]`, etc.). |
| **2D Arrays** | Jagged array `int[][] subjectMarks` storing subject marks for each student. |
| **Operators** | Arithmetic (`+`, `/`), Relational (`>=`, `<=`, `==`), Logical (`&&`, `||`, `!`). |
| **Conditionals** | `if-else`, `if-else-if` ladder for grades and categories; `switch` for menu and amounts. |
| **Loops** | `do-while` (menu loop), `while` (input validation loops), `for` (array iteration & shifting). |
| **Type Casting** | `(double) total / subjects` to avoid integer division truncation. |
| **Modular Design** | Pure, reusable static methods with single responsibilities. |

---

## Project Directory Structure

```text
Project/
├── .gitignore                      # Git ignore rules for build artifacts
├── README.md                       # Complete documentation & viva guide
├── ScholarshipEligibilitySystem.java # Main application source file
├── run.sh                          # One-shot build & run script for macOS/Linux
├── run.bat                         # One-shot build & run script for Windows
└── out/                            # Compiled class files directory
```

---

## How to Run

### macOS / Linux (One-Shot Script)

Make sure the script is executable and run it:
```bash
chmod +x run.sh
./run.sh
```

### Windows (One-Shot Script)

Double-click `run.bat` or execute in Command Prompt:
```cmd
run.bat
```

### Manual Compilation and Execution

If you prefer using the standard Java CLI directly:
```bash
# 1. Compile to the 'out' directory
javac -d out ScholarshipEligibilitySystem.java

# 2. Run the application
java -cp out ScholarshipEligibilitySystem
```

---

## Menu Overview

When launched, the application displays:

```text
========================================
    STUDENT SCHOLARSHIP SYSTEM
========================================
1. Register Student
2. Enter Student Marks
3. Calculate Academic Result
4. Check Scholarship Eligibility
5. Display Student Result
6. Display Class Summary
7. Update Student Details
8. Delete Student Record
9. Exit
========================================
Enter your choice: 
```

---

## Sample Execution & Output

### 1. Eligible Student (Merit Scholarship)
```text
========================================
       SCHOLARSHIP RESULT
========================================
Student Name    : Vishal
Roll Number     : 101

Subject Marks:
Subject 1       : 99
Subject 2       : 92
Subject 3       : 96
Subject 4       : 94

Total Marks     : 381
Percentage      : 95.25%
Grade           : A

Attendance      : 97%
Family Income   : Rs. 300000

Status          : ELIGIBLE
Scholarship     : Merit Scholarship
Amount          : Rs. 50000
========================================
```

### 2. Disqualified Student (Multiple Rejection Reasons)
```text
========================================
       SCHOLARSHIP RESULT
========================================
Student Name    : Somyajeet
Roll Number     : 102

Subject Marks:
Subject 1       : 78
Subject 2       : 89
Subject 3       : 87
Subject 4       : 88

Total Marks     : 342
Percentage      : 85.50%
Grade           : B

Attendance      : 67%
Family Income   : Rs. 400000

Status          : NOT ELIGIBLE
Scholarship     : None
Amount          : Rs. 0

NOT ELIGIBLE

Reasons:
- Attendance is below 75%.
- Annual family income exceeds Rs. 3,00,000.
========================================
```

### 3. Class Summary
```text
========================================
           CLASS SUMMARY
========================================
Total Students              : 3
Eligible Students           : 2
Not Eligible Students       : 1

Merit Scholarship           : 1
Academic Scholarship        : 0
General Scholarship         : 1

Highest Percentage          : 95.25%
Topper                      : Vishal
========================================
```

---

## Viva Voce Preparation (Q&A)

**Q1: How is the academic percentage calculated?**
> The percentage is `(totalObtained / totalMaximum) * 100`. Since each subject is marked out of 100, `totalMaximum` is `numberOfSubjects * 100`. When `totalMaximum` is zero or less, the calculation returns `0.0` to avoid division by zero. For example, 381 marks out of 400 is 95.25%.

**Q2: What is the difference between `&&` and `&`?**  
> `&&` is the short-circuit logical AND operator. If the first condition evaluates to `false`, the remaining expressions are skipped. `&` always evaluates every operand regardless of the left side.

**Q3: Why is `scanner.nextLine()` needed after `scanner.nextInt()`?**  
> `nextInt()` parses the integer digits but leaves the trailing newline character (`\n`) in the input buffer. Calling `nextLine()` clears that buffer, preventing subsequent String inputs from being skipped.

**Q4: How does the program prevent crashing if a user enters characters instead of numbers?**  
> The helper methods check `scanner.hasNextInt()` or `scanner.hasNextDouble()` before reading. If invalid characters are detected, the token is discarded with `scanner.nextLine()` and the user is politely re-prompted.

**Q5: How does the deletion operation work without using a database or dynamic Collections (`ArrayList`)?**  
> When a student at index $k$ is deleted, a `for` loop shifts all elements from index $k+1$ up to $N-1$ one position to the left. The last slot is nulled out, and the counter `totalStudents` is decremented by 1.

**Q6: What happens when student details or marks are updated?**  
> The `recalculateStudent(index)` method checks if calculations and evaluations have previously been run. If so, it automatically recalculates their total marks, percentage, grade, eligibility status, scholarship category, and award amount.

---

## Author & Project Info

- **Course**: Java Programming / Object-Oriented Programming Fundamentals
- **Environment**: Java 8+ (Tested on Java 21 LTS)
- **License**: Educational / Open-Source
