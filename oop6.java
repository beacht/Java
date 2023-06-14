import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
- Project 1
- Justin Parrondo, Lance Campos, Tyler Beach
- NOTE TO GRADER: Please note that the only inputs that are validated
- in this program are the ones for which an example of validation
- was provided in the Sample Runs and/or described in the instructions.
- Everything else was assumed to not need validation because it was not
- specified in the instructions.
*/

public class Project1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean continueProgram = true;

        Student[] students = new Student[2];
        Arrays.fill(students, null);
        Faculty faculty = null;
        Staff staff = null;

        while (continueProgram) {
            System.out.println("Choose one of the options:");
            System.out.println("1-Enter the information of the faculty member\n" +
                    "2-Enter the information of the two students\n" +
                    "3-Print tuition invoice\n" +
                    "4-Print faculty information\n" +
                    "5-Enter the information of the staff member\n" +
                    "6-Print the information of the staff member\n" +
                    "7-Exit Program");
            System.out.print("\tEnter your selection: ");
            char selection = in.nextLine().charAt(0);
            switch (selection) {
                case '1':
                    if (faculty != null) {
                        System.out.println("You already have a faculty filled in. Do you want to update their information?");
                        System.out.print("Yes or No: ");
                        String answer = in.nextLine().toLowerCase();
                        if (answer.equals("no"))
                            continue;
                    }

                    System.out.println("Enter faculty info:");
                    System.out.print("\tName of the faculty: ");
                    String fullName = in.nextLine();
                    System.out.print("\tID: ");
                    String id = in.nextLine();
                    faculty = new Faculty(fullName, id);

                    boolean rankSetSuccessfully = false;
                    while (!rankSetSuccessfully) {
                        System.out.print("\tRank: ");
                        String status = in.nextLine();
                        try {
                            faculty.setStatus(status);
                            rankSetSuccessfully = true;
                        } catch (InputMismatchException e) {
                            System.out.println("\t\t\"" + status + "\" is invalid");
                        }
                    }

                    boolean departmentSetSuccessfully = false;
                    while (!departmentSetSuccessfully) {
                        System.out.print("\tDepartment: ");
                        String department = in.nextLine();
                        try {
                            faculty.setDepartment(department);
                            departmentSetSuccessfully = true;
                        } catch (InputMismatchException e) {
                            System.out.println("\t\t\"" + department + "\" is invalid");
                        }
                    }

                    System.out.println("\tFaculty successfully added!");
                    break;
                case '2':
                    if (students[0] != null && students[1] != null) {
                        System.out.println("You already have two students filled in. Do you want to update their information?");
                        System.out.print("Yes or No: ");
                        String answer = in.nextLine().toLowerCase();
                        if (answer.equals("no"))
                            continue;
                    }
                    System.out.println();
                    for (int i = 0; i < 2; i++) {
                        System.out.println("Enter student " + (i + 1) + " info");
                        System.out.print("\tName of Student: ");
                        fullName = in.nextLine();
                        System.out.print("\tID: ");
                        id = in.nextLine();
                        System.out.print("\tGpa: ");
                        double gpa = in.nextDouble();
                        System.out.print("\tCredit hours: ");
                        int creditHours = in.nextInt();
                        in.nextLine();

                        students[i] = new Student(fullName, id, gpa, creditHours);
                        System.out.println("Thanks!\n");
                    }
                    break;
                case '3':
                    if (students[0] == null && students[1] == null) {
                        System.out.println("Sorry! No students entered yet!");
                        continue;
                    }

                    System.out.printf("Which student? Enter 1 for %s or Enter 2 %s ?\n", students[0].getFullName(), students[1].getFullName());
                    int choice = in.nextInt();
                    in.nextLine();

                    System.out.printf("Here is the tuition invoice for %s :\n", students[choice - 1].getFullName());
                    System.out.println(students[choice - 1].getTuitionInvoice());
                    break;
                case '4':
                    if (faculty == null) {
                        System.out.println("Sorry! No Faculty information entered yet!");
                        continue;
                    }

                    System.out.println(faculty.getWorkerReport());
                    break;
                case '5':
                    if (staff != null) {
                        System.out.println("You already have a staff filled in. Do you want to update their information?");
                        System.out.print("Yes or No: ");
                        String answer = in.nextLine().toLowerCase();
                        if (answer.equals("no"))
                            continue;
                    }

                    System.out.print("\tName of the staff member: ");
                    fullName = in.nextLine();
                    System.out.print("\tEnter the id: ");
                    id = in.nextLine();
                    staff = new Staff(fullName, id);

                    departmentSetSuccessfully = false;
                    while (!departmentSetSuccessfully) {
                        System.out.print("\tDepartment: ");
                        String department = in.nextLine();
                        try {
                            staff.setDepartment(department);
                            departmentSetSuccessfully = true;
                        } catch (InputMismatchException e) {
                            System.out.println("\t\t\"" + department + "\" is invalid");
                        }
                    }

                    System.out.print("Status, Enter P for Part Time or Enter F for Full Time: ");
                    String status = in.nextLine().toLowerCase().equals("p") ? "part time" : "full time";
                    staff.setStatus(status);
                    break;
                case '6':
                    if (staff == null) {
                        System.out.println("Sorry! No Staff information entered yet!");
                        continue;
                    }
                    System.out.println(staff.getWorkerReport());
                    break;
                case '7':
                    continueProgram = false;
                    break;
                default:
                    System.out.println("Invalid entry- please try again");
            }
        }
        System.out.println("\n\n\nGoodbye!");
    }
}

// All other classes in this program will be a Person
class Person {
    private String fullName;
    private String id;

    public Person(String fullName, String id) {
        this.fullName = fullName;
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getId() {
        return id;
    }
}

// A Student will have a public method getTuitionInvoice to
// easily generate the desired invoice
class Student extends Person {
    // Constants
    private final double PRICE_PER_CREDIT_HOUR = 236.45;
    private final double ADMIN_FEE = 52;
    private final double MINIMUM_GPA_FOR_DISCOUNT = 3.85;

    private double gpa;
    private int creditHours;

    public Student(String fullName, String id, double gpa, int creditHours) {
        super(fullName, id);
        this.gpa = gpa;
        this.creditHours = creditHours;
    }

    private double getPaymentAmountBeforeDiscount() {
        return creditHours * PRICE_PER_CREDIT_HOUR + ADMIN_FEE;
    }

    private double getDiscountAmount(double paymentAmountBeforeDiscount) {
        return gpa >= MINIMUM_GPA_FOR_DISCOUNT ? 0.15 * paymentAmountBeforeDiscount : 0;
    }

    // Returns a string of a Student's tuition invoice
    public String getTuitionInvoice() {
        // Make necessary calculations
        double paymentAmountBeforeDiscount = getPaymentAmountBeforeDiscount();
        double discountAmount = getDiscountAmount(paymentAmountBeforeDiscount);
        double totalPaymentAmount = paymentAmountBeforeDiscount - discountAmount;

        // Create string
        String line = "---------------------------------------------------------------------------";
        String tuitionInvoice = line + "\n";

        tuitionInvoice += String.format("%-31s%s\n", super.getFullName(), super.getId());
        tuitionInvoice += "Credit Hours: " + creditHours + String.format(" ($%.2f/credit hour)\n", PRICE_PER_CREDIT_HOUR);
        tuitionInvoice += String.format("Fees: $%d\n", (int) ADMIN_FEE);
        tuitionInvoice += "\n\n";

        String totalPaymentString = String.format("Total payment (after discount): $%,.2f", totalPaymentAmount);
        String discountAmountString = String.format("($%s discount applied)", discountAmount == 0 ? "0" : String.format("%,.2f", discountAmount));

        // Calculate and add the spaces that should be between the above two
        String spaces = "";
        for (int i = 75 - discountAmountString.length(); i > totalPaymentString.length(); i--)
            spaces += " ";
        tuitionInvoice += String.format("%s%s%s\n", totalPaymentString, spaces, discountAmountString);
        tuitionInvoice += line;

        return tuitionInvoice;
    }
}

// Both Staff and Faculty will be a Worker, just with a different abstract validation
// function (that needs to be implemented) for a valid status. The public getWorkerReport()
// will be able to generate the desired report as a String, given that department and status
// were both set with no exceptions thrown.
abstract class Worker extends Person {
    private String department;
    private String status;

    private boolean isDepartmentValid(String department) {
        ArrayList<String> validDepartments = new ArrayList<String>(Arrays.asList("english", "mathematics", "engineering"));
        return validDepartments.contains(department.toLowerCase());
    }

    abstract boolean isStatusValid(String status);

    public Worker(String fullName, String id) {
        super(fullName, id);
    }

    // Set the department of a Worker, throwing an InputMismatchException
    // if it is not a valid department
    public void setDepartment(String department) {
        if (!isDepartmentValid(department))
            throw new InputMismatchException();

        this.department = department;
    }

    // Set the status of a Worker, throwing an InputMismatchException
    // if it is not a valid status
    public void setStatus(String status) {
        if (!isStatusValid(status))
            throw new InputMismatchException();

        this.status = status;
    }

    // Simple private helper method that will capitalize
    // the first letter of every space-separated word
    private String getCapitalizedString(String s) {
        String[] splitString = s.split(" ");
        for (int i = 0; i < splitString.length; i++) {
            splitString[i] = splitString[i].substring(0, 1).toUpperCase() + splitString[i].substring(1).toLowerCase();
        }

        String capitalizedString = "";
        for (int i = 0; i < splitString.length - 1; i++) {
            capitalizedString += splitString[i] + " ";
        }

        return capitalizedString + splitString[splitString.length - 1];
    }

    // Simple helper method that wraps the above for department.
    // It must be known that department was set before trying to
    // run this method.
    private String getCapitalizedDepartment() {
        return getCapitalizedString(department);
    }

    // Simple helper method that wraps the above for status
    // It must be known that status was set before trying to
    // run this method.
    private String getCapitalizedStatus() {
        return getCapitalizedString(status);
    }

    // Generate the desired worker report as a String. It must
    // be known that status was set before trying to run this method.
    public String getWorkerReport() {
        String line = "---------------------------------------------------------------------------";
        String workerReport = line + "\n";

        workerReport += String.format("%-31s%s\n", super.getFullName(), super.getId());
        workerReport += getCapitalizedDepartment() + " Department, " + getCapitalizedStatus() + "\n";
        workerReport += line;

        return workerReport;
    }
}

// A Faculty is simply a Worker with 'professor' and 'adjunct' as valid statuses
class Faculty extends Worker {
    @Override
    public boolean isStatusValid(String status) {
        ArrayList<String> validStatuses = new ArrayList<String>(Arrays.asList("professor", "adjunct"));
        return validStatuses.contains(status.toLowerCase());
    }

    public Faculty(String fullName, String id) {
        super(fullName, id);
    }
}

// A Staff is simply a Worker with 'part time' and 'full time' as valid statuses
class Staff extends Worker {
    @Override
    public boolean isStatusValid(String status) {
        ArrayList<String> validStatuses = new ArrayList<String>(Arrays.asList("part time", "full time"));
        return validStatuses.contains(status.toLowerCase());
    }

    public Staff(String fullName, String id) {
        super(fullName, id);
    }
}