import java.util.ArrayList;

class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Department: " + department);
    }
}


public class EmployeeCRUD {
    private ArrayList<Employee> employees = new ArrayList<>();

    // Create
    public void addEmployee(Employee emp) {
        employees.add(emp);
        System.out.println("Employee added.");
    }

    // Read
    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            for (Employee emp : employees) {
                emp.display();
            }
        }
    }

    // Update
    public void updateEmployee(int id, String newName, String newDept) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                emp.setName(newName);
                emp.setDepartment(newDept);
                System.out.println("Employee updated.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    // Delete
    public void deleteEmployee(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                employees.remove(emp);
                System.out.println("Employee deleted.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    // Main method for testing
    public static void main(String[] args) {
        EmployeeCRUD crud = new EmployeeCRUD();


        // Sample operations
        crud.addEmployee(new Employee(1, "Ashish", "IT"));
        crud.addEmployee(new Employee(2, "Neha", "HR"));

        System.out.println("\nAll Employees:");
        crud.displayEmployees();

        System.out.println("\nUpdating Employee with ID 1:");
        crud.updateEmployee(1, "Ashish Odedra", "DevOps");

        System.out.println("\nAll Employees after update:");
        crud.displayEmployees();

        System.out.println("\nDeleting Employee with ID 2:");
        crud.deleteEmployee(2);

        System.out.println("\nAll Employees after deletion:");
        crud.displayEmployees();
    }
}