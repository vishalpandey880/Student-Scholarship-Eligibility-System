import java.util.Scanner;

public class ScholarshipEligibilitySystem {

    // Global Scanner for reading user input
    private static final Scanner scanner = new Scanner(System.in);

    // Data storage for multiple students using simple arrays
    private static int totalStudents = 0;           //An array used to store the names of multiple students
    private static String[] names;       
    private static int[] rollNumbers;
    private static double[] attendances;
    private static double[] incomes;
    private static int[][] subjectMarks;
    private static int[] totalMarks;
    private static double[] percentages;
    private static char[] grades;
    private static boolean[] isEligible;
    private static String[] scholarshipCategories;
    private static double[] scholarshipAmounts;

    // Flags to ensure proper execution order in the menu
    private static boolean registered = false;
    private static boolean marksEntered = false;
    private static boolean calculated = false;
    private static boolean evaluated = false;

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            choice = readInt("Enter your choice: ", 1, 9);
            System.out.println();

            switch (choice) {
                case 1:
                    registerStudents();
                    break;
                case 2:
                    enterStudentMarks();
                    break;
                case 3:
                    calculateAcademicResults();
                    break;
                case 4:
                    checkScholarshipEligibility();
                    break;
                case 5:
                    displayStudentResult();
                    break;
                case 6:
                    displayClassSummary();
                    break;
                case 7:
                    updateStudentDetails();
                    break;
                case 8:
                    deleteStudentRecord();
                    break;
                case 9:
                    System.out.println("Thank you for using Student Scholarship System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose between 1 and 9.");
            }
            System.out.println();
        } while (choice != 9);
    }

    // =========================================================================
    // MENU DISPLAY (Module 11)
    // =========================================================================
    public static void displayMenu() {
        System.out.println("========================================");
        System.out.println("    STUDENT SCHOLARSHIP SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Register Student");
        System.out.println("2. Enter Student Marks");
        System.out.println("3. Calculate Academic Result");
        System.out.println("4. Check Scholarship Eligibility");
        System.out.println("5. Display Student Result");
        System.out.println("6. Display Class Summary");
        System.out.println("7. Update Student Details");
        System.out.println("8. Delete Student Record");
        System.out.println("9. Exit");
        System.out.println("========================================");
    }

    // =========================================================================
    // PART 1: STUDENT REGISTRATION (Module 1 & Module 9)
    // =========================================================================
    public static void registerStudents() {                        //Creates a method
        System.out.print("Enter number of students: ");
        totalStudents = readPositiveInt();

        // Initialize parallel arrays based on total student count
        names = new String[totalStudents];              
        rollNumbers = new int[totalStudents];
        attendances = new double[totalStudents];
        incomes = new double[totalStudents];
        subjectMarks = new int[totalStudents][];
        totalMarks = new int[totalStudents];
        percentages = new double[totalStudents];
        grades = new char[totalStudents];
        isEligible = new boolean[totalStudents];
        scholarshipCategories = new String[totalStudents];
        scholarshipAmounts = new double[totalStudents];

        // Loop to accept details for each student
        for (int i = 0; i < totalStudents; i++) {
            System.out.println("\n---------- Student " + (i + 1) + " ----------");
            System.out.print("Enter Student Name    : ");
            names[i] = normalizeName(scanner.nextLine());

            System.out.print("Enter Roll Number     : ");
            rollNumbers[i] = readPositiveInt();

            System.out.print("Enter Attendance % (0-100): ");
            attendances[i] = readDouble(0, 100);

            System.out.print("Enter Annual Income   : ");
            incomes[i] = readNonNegativeDouble();
        }

        registered = true;
        marksEntered = false;
        calculated = false;
        evaluated = false;
        System.out.println("\n[SUCCESS] " + totalStudents + " student(s) registered successfully!");
    }

    private static String normalizeName(String name) {
        return name.trim().replaceAll("\\s+", " ");
    }

    // =========================================================================
    // PART 2: MARKS ENTRY (Module 2)
    // =========================================================================
    public static void enterStudentMarks() {
        if (!registered) {
            System.out.println("[ERROR] Please register students first (Option 1).");
            return;
        }

        for (int i = 0; i < totalStudents; i++) {
            System.out.println("\nMarks Entry for Student: " + names[i] + " (Roll No: " + rollNumbers[i] + ")");
            System.out.print("Enter number of subjects: ");
            int numSubjects = readPositiveInt();

            // Array to store marks for this student
            subjectMarks[i] = new int[numSubjects];

            for (int s = 0; s < numSubjects; s++) {
                System.out.print("Enter marks for Subject " + (s + 1) + " (0-100): ");
                subjectMarks[i][s] = readInt(0, 100);
            }

            // Display entered marks immediately
            System.out.println("\nMarks entered for " + names[i] + ":");
            for (int s = 0; s < numSubjects; s++) {
                System.out.println("Subject " + (s + 1) + " : " + subjectMarks[i][s]);
            }
        }

        marksEntered = true;
        calculated = false;
        evaluated = false;
        System.out.println("\n[SUCCESS] Marks entered successfully for all students!");
    }

    // =========================================================================
    // PART 3: ACADEMIC CALCULATION (Module 3)
    // =========================================================================
    public static int calculateTotalMarks(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    public static double calculatePercentage(int totalObtained, int totalMaximum) {
        if (totalMaximum <= 0) {
            return 0.0;
        }
        return ((double) totalObtained / totalMaximum) * 100.0;
    }

    public static char calculateGrade(double percentage) {
        if (percentage >= 90) {
            return 'A';
        } else if (percentage >= 80) {
            return 'B';
        } else if (percentage >= 70) {
            return 'C';
        } else if (percentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static void calculateAcademicResults() {
        if (!marksEntered) {
            System.out.println("[ERROR] Please enter student marks first (Option 2).");
            return;
        }

        for (int i = 0; i < totalStudents; i++) {
            totalMarks[i] = calculateTotalMarks(subjectMarks[i]);
            percentages[i] = calculatePercentage(totalMarks[i], subjectMarks[i].length * 100);
            grades[i] = calculateGrade(percentages[i]);
        }

        calculated = true;
        evaluated = false;
        System.out.println("[SUCCESS] Academic results calculated successfully for all students!");
    }

    // =========================================================================
    // PART 4: SCHOLARSHIP VALIDATION & CATEGORIES (Module 4, 5 & 6)
    // =========================================================================
    public static boolean checkEligibility(double percentage, double attendance, double familyIncome) {
        // Must satisfy all three basic conditions
        return percentage >= 75 && attendance >= 75 && familyIncome <= 300000;
    }

    public static String getScholarshipCategory(double percentage, double attendance, double familyIncome) {
        // Merit Scholarship: % >= 90, Attendance >= 85, Income <= 300,000
        if (percentage >= 90 && attendance >= 85 && familyIncome <= 300000) {
            return "Merit Scholarship";
        }
        // Academic Scholarship: % >= 80, Attendance >= 75, Income <= 300000
        else if (percentage >= 80 && attendance >= 75 && familyIncome <= 300000) {
            return "Academic Scholarship";
        }
        // General Scholarship: % >= 75, Attendance >= 75, Income <= 300000
        else if (percentage >= 75 && attendance >= 75 && familyIncome <= 300000) {
            return "General Scholarship";
        }
        // Not Eligible
        else {
            return "Not Eligible";
        }
    }

    public static double getScholarshipAmount(String category) {
        switch (category) {
            case "Merit Scholarship":
                return 50000;
            case "Academic Scholarship":
                return 30000;
            case "General Scholarship":
                return 15000;
            default:
                return 0;
        }
    }

    public static void displayRejectionReasons(double percentage, double attendance, double familyIncome) {
        System.out.println("NOT ELIGIBLE\n");
        System.out.println("Reasons:");
        if (percentage < 75) {
            System.out.println("- Academic percentage is below 75%.");
        }
        if (attendance < 75) {
            System.out.println("- Attendance is below 75%.");
        }
        if (familyIncome > 300000) {
            System.out.println("- Annual family income exceeds Rs. 3,00,000.");
        }
    }

    public static void checkScholarshipEligibility() {
        if (!calculated) {
            System.out.println("[ERROR] Please calculate academic results first (Option 3).");
            return;
        }

        for (int i = 0; i < totalStudents; i++) {
            isEligible[i] = checkEligibility(percentages[i], attendances[i], incomes[i]);
            scholarshipCategories[i] = getScholarshipCategory(percentages[i], attendances[i], incomes[i]);
            scholarshipAmounts[i] = getScholarshipAmount(scholarshipCategories[i]);
        }

        evaluated = true;
        System.out.println("[SUCCESS] Scholarship eligibility checked successfully for all students!");
    }

    // =========================================================================
    // PART 5: DISPLAY REPORTS (Module 8 & Module 10)
    // =========================================================================
    public static void displayStudentResult() {
        if (!evaluated) {
            System.out.println("[ERROR] Please check scholarship eligibility first (Option 4).");
            return;
        }

        for (int i = 0; i < totalStudents; i++) {
            System.out.println("========================================");
            System.out.println("       SCHOLARSHIP RESULT");
            System.out.println("========================================");
            System.out.println("Student Name    : " + names[i]);
            System.out.println("Roll Number     : " + rollNumbers[i]);
            System.out.println();
            System.out.println("Subject Marks:");
            for (int s = 0; s < subjectMarks[i].length; s++) {
                System.out.println("Subject " + (s + 1) + "       : " + subjectMarks[i][s]);
            }
            System.out.println();
            System.out.println("Total Marks     : " + totalMarks[i]);
            System.out.printf("Percentage      : %.2f%%\n", percentages[i]);
            System.out.println("Grade           : " + grades[i]);
            System.out.println();
            System.out.printf("Attendance      : %.0f%%\n", attendances[i]);
            System.out.printf("Family Income   : Rs. %.0f\n", incomes[i]);
            System.out.println();

            if (isEligible[i]) {
                System.out.println("Status          : ELIGIBLE");
                System.out.println("Scholarship     : " + scholarshipCategories[i]);
                System.out.printf("Amount          : Rs. %.0f\n", scholarshipAmounts[i]);
            } else {
                System.out.println("Status          : NOT ELIGIBLE");
                System.out.println("Scholarship     : None");
                System.out.println("Amount          : Rs. 0\n");
                displayRejectionReasons(percentages[i], attendances[i], incomes[i]);
            }
            System.out.println("========================================\n");
        }
    }

    public static void displayClassSummary() {
        if (!evaluated) {
            System.out.println("[ERROR] Please check scholarship eligibility first (Option 4).");
            return;
        }

        int eligibleCount = 0;
        int notEligibleCount = 0;
        int meritCount = 0;
        int academicCount = 0;
        int generalCount = 0;

        double highestPercentage = -1;
        String topper = "N/A";

        for (int i = 0; i < totalStudents; i++) {
            if (isEligible[i]) {
                eligibleCount++;
            } else {
                notEligibleCount++;
            }

            switch (scholarshipCategories[i]) {
                case "Merit Scholarship":
                    meritCount++;
                    break;
                case "Academic Scholarship":
                    academicCount++;
                    break;
                case "General Scholarship":
                    generalCount++;
                    break;
                default:
                    break;
            }

            if (percentages[i] > highestPercentage) {
                highestPercentage = percentages[i];
                topper = names[i];
            }
        }

        System.out.println("========================================");
        System.out.println("           CLASS SUMMARY");
        System.out.println("========================================");
        System.out.printf("Total Students              : %d\n", totalStudents);
        System.out.printf("Eligible Students           : %d\n", eligibleCount);
        System.out.printf("Not Eligible Students       : %d\n", notEligibleCount);
        System.out.println();
        System.out.printf("Merit Scholarship           : %d\n", meritCount);
        System.out.printf("Academic Scholarship        : %d\n", academicCount);
        System.out.printf("General Scholarship         : %d\n", generalCount);
        System.out.println();
        System.out.printf("Highest Percentage          : %.2f%%\n", highestPercentage);
        System.out.printf("Topper                      : %s\n", topper);
        System.out.println("========================================");
    }

    // =========================================================================
    // PART 6: RECORD MANAGEMENT (UPDATE & DELETE)
    // =========================================================================

    /**
     * Finds the index of a student by Roll Number.
     *
     * @param roll Roll Number to search for
     * @return Array index if found, -1 otherwise
     */
    public static int findStudentIndexByRoll(int roll) {
        for (int i = 0; i < totalStudents; i++) {
            if (rollNumbers[i] == roll) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Recalculates academic results and scholarship eligibility for a student
     * after their details or marks are updated.
     *
     * @param index Student array index
     */
    
    public static void recalculateStudent(int index) {
        if (calculated && subjectMarks[index] != null) {
            totalMarks[index] = calculateTotalMarks(subjectMarks[index]);
            percentages[index] = calculatePercentage(totalMarks[index], subjectMarks[index].length * 100);
            grades[index] = calculateGrade(percentages[index]);
        }
        if (evaluated && calculated && subjectMarks[index] != null) {
            isEligible[index] = checkEligibility(percentages[index], attendances[index], incomes[index]);
            scholarshipCategories[index] = getScholarshipCategory(percentages[index], attendances[index], incomes[index]);
            scholarshipAmounts[index] = getScholarshipAmount(scholarshipCategories[index]);
        }
    }

    /**
     * Updates an existing student's record (Name, Attendance, Income, or Marks).
     */
    public static void updateStudentDetails() {
        if (!registered || totalStudents == 0) {
            System.out.println("[ERROR] No students registered in the system.");
            return;
        }

        System.out.print("Enter Roll Number of student to update: ");
        int roll = readPositiveInt();
        int index = findStudentIndexByRoll(roll);

        if (index == -1) {
            System.out.println("[ERROR] Student with Roll Number " + roll + " not found!");
            return;
        }

        System.out.println("\n----------------------------------------");
        System.out.println("         UPDATE STUDENT RECORD          ");
        System.out.println("----------------------------------------");
        System.out.println("Selected Student: " + names[index] + " (Roll No: " + rollNumbers[index] + ")");
        System.out.println("1. Update Name");
        System.out.println("2. Update Attendance Percentage");
        System.out.println("3. Update Annual Family Income");
        System.out.println("4. Update Subject Marks");
        System.out.println("5. Update All Details");
        System.out.println("6. Cancel");
        int updateChoice = readInt("Enter your choice (1-6): ", 1, 6);

        switch (updateChoice) {
            case 1:
                System.out.print("Enter new Name (Current: " + names[index] + "): ");
                names[index] = normalizeName(scanner.nextLine());
                System.out.println("[SUCCESS] Name updated successfully!");
                break;

            case 2:
                System.out.print("Enter new Attendance % (0-100) (Current: " + attendances[index] + "%): ");
                attendances[index] = readDouble(0, 100);
                recalculateStudent(index);
                System.out.println("[SUCCESS] Attendance updated successfully!");
                break;

            case 3:
                System.out.print("Enter new Annual Income (Current: Rs. " + incomes[index] + "): ");
                incomes[index] = readNonNegativeDouble();
                recalculateStudent(index);
                System.out.println("[SUCCESS] Annual Income updated successfully!");
                break;

            case 4:
                System.out.print("Enter number of subjects: ");
                int numSubjects = readPositiveInt();
                subjectMarks[index] = new int[numSubjects];
                for (int s = 0; s < numSubjects; s++) {
                    System.out.print("Enter marks for Subject " + (s + 1) + " (0-100): ");
                    subjectMarks[index][s] = readInt(0, 100);
                }
                marksEntered = true;
                recalculateStudent(index);
                System.out.println("[SUCCESS] Marks updated successfully!");
                break;

            case 5:
                System.out.print("Enter new Name: ");
                names[index] = normalizeName(scanner.nextLine());
                System.out.print("Enter new Attendance % (0-100): ");
                attendances[index] = readDouble(0, 100);
                System.out.print("Enter new Annual Income: ");
                incomes[index] = readNonNegativeDouble();

                System.out.print("Enter number of subjects: ");
                int count = readPositiveInt();
                subjectMarks[index] = new int[count];
                for (int s = 0; s < count; s++) {
                    System.out.print("Enter marks for Subject " + (s + 1) + " (0-100): ");
                    subjectMarks[index][s] = readInt(0, 100);
                }
                marksEntered = true;
                recalculateStudent(index);
                System.out.println("[SUCCESS] All details updated successfully!");
                break;

            case 6:
                System.out.println("Update cancelled.");
                break;
        }
    }

    /**
     * Deletes a student record by Roll Number and shifts array elements.
     */
    public static void deleteStudentRecord() {
        if (!registered || totalStudents == 0) {
            System.out.println("[ERROR] No students registered in the system.");
            return;
        }

        System.out.print("Enter Roll Number of student to delete: ");
        int roll = readPositiveInt();
        int index = findStudentIndexByRoll(roll);

        if (index == -1) {
            System.out.println("[ERROR] Student with Roll Number " + roll + " not found!");
            return;
        }

        System.out.println("Found student: " + names[index] + " (Roll No: " + rollNumbers[index] + ")");
        System.out.print("Are you sure you want to delete this record? (yes/no): ");
        String confirm = scanner.nextLine().trim();

        if (!confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("y")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        // Shift elements left to remove the student
        for (int i = index; i < totalStudents - 1; i++) {
            names[i] = names[i + 1];
            rollNumbers[i] = rollNumbers[i + 1];
            attendances[i] = attendances[i + 1];
            incomes[i] = incomes[i + 1];
            subjectMarks[i] = subjectMarks[i + 1];
            totalMarks[i] = totalMarks[i + 1];
            percentages[i] = percentages[i + 1];
            grades[i] = grades[i + 1];
            isEligible[i] = isEligible[i + 1];
            scholarshipCategories[i] = scholarshipCategories[i + 1];
            scholarshipAmounts[i] = scholarshipAmounts[i + 1];
        }

        // Clear the last element slot
        int last = totalStudents - 1;
        names[last] = null;
        subjectMarks[last] = null;
        scholarshipCategories[last] = null;

        totalStudents--;

        if (totalStudents == 0) {
            registered = false;
            marksEntered = false;
            calculated = false;
            evaluated = false;
        }

        System.out.println("[SUCCESS] Student record for Roll Number " + roll + " deleted successfully!");
    }

    // =========================================================================
    // INPUT VALIDATION HELPERS (Module 7)
    // =========================================================================
    public static int readPositiveInt() {
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Please enter a positive number: ");
                scanner.nextLine();
                continue;
            }
            int val = scanner.nextInt();
            scanner.nextLine(); // clear newline
            if (val <= 0) {
                System.out.print("Must be greater than 0. Please try again: ");
            } else {
                return val;
            }
        }
    }

    public static int readInt(int min, int max) {
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Enter a number (" + min + "-" + max + "): ");
                scanner.nextLine();
                continue;
            }
            int val = scanner.nextInt();
            scanner.nextLine();
            if (val < min || val > max) {
                System.out.print("Invalid! Please enter a value between " + min + " and " + max + ": ");
            } else {
                return val;
            }
        }
    }

    public static int readInt(String prompt, int min, int max) {
        System.out.print(prompt);
        return readInt(min, max);
    }

    public static double readDouble(double min, double max) {
        while (true) {
            if (!scanner.hasNextDouble()) {
                System.out.print("Invalid input! Enter a number between " + min + " and " + max + ": ");
                scanner.nextLine();
                continue;
            }
            double val = scanner.nextDouble();
            scanner.nextLine();
            if (val < min || val > max) {
                System.out.print("Out of range! Value must be between " + min + " and " + max + ": ");
            } else {
                return val;
            }
        }
    }

    public static double readNonNegativeDouble() {
        while (true) {
            if (!scanner.hasNextDouble()) {
                System.out.print("Invalid input! Enter a valid amount: ");
                scanner.nextLine();
                continue;
            }
            double val = scanner.nextDouble();
            scanner.nextLine();
            if (val < 0) {
                System.out.print("Income cannot be negative! Please enter again: ");
            } else {
                return val;
            }
        }
    }
}
