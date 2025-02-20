import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}
// lakshay uid 22BCS15481
public class EmployeeManagementSystem {
    private static final ArrayList<Employee> employees = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void addEmployee(int id, String name, double salary) {
        for (Employee emp : employees) {
            if (emp.id == id) {
                System.out.println("Error: Employee with ID " + id + " already exists.");
                return;
            }
        }
        employees.add(new Employee(id, name, salary));
        System.out.println("Employee Added: ID=" + id + ", Name=" + name + ", Salary=" + salary);
    }

    public static void updateEmployee(int id, double newSalary) {
        for (Employee emp : employees) {
            if (emp.id == id) {
                emp.salary = newSalary;
                System.out.println("Employee ID " + id + " updated successfully.");
                return;
            }
        }
        System.out.println("Error: Employee ID " + id + " not found.");
    }

    public static void removeEmployee(int id) {
        for (Employee emp : employees) {
            if (emp.id == id) {
                employees.remove(emp);
                System.out.println("Employee ID " + id + " removed successfully.");
                return;
            }
        }
        System.out.println("Error: Employee ID " + id + " not found.");
    }

    public static void searchEmployee(int id) {
        for (Employee emp : employees) {
            if (emp.id == id) {
                System.out.println("Employee Found: ID=" + emp.id + ", Name=" + emp.name + ", Salary=" + emp.salary);
                return;
            }
        }
        System.out.println("Error: Employee ID " + id + " not found.");
    }

    public static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee emp : employees) {
                System.out.println("ID: " + emp.id + ", Name: " + emp.name + ", Salary: " + emp.salary);
            }
        }
    }

    public static void main(String[] args) {
        // Test Cases
        displayEmployees(); // Test Case 1
        addEmployee(101, "Anish", 50000); // Test Case 2
        addEmployee(102, "Bobby", 60000); // Test Case 2
        updateEmployee(101, 55000); // Test Case 3
        searchEmployee(102); // Test Case 4
        removeEmployee(101); // Test Case 5
        displayEmployees(); // Test Case 6
        addEmployee(101, "Charlie", 70000);
        addEmployee(101, "Charlie", 70000);// Test Case 7 duplicacy case
        displayEmployees();
    }
}
// test case are executed properly 


   



