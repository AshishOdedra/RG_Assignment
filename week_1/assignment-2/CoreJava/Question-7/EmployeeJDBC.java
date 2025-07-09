import java.sql.*;

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
}

public class EmployeeJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/MyDB";
    private static final String USER = "root";
    private static final String PASSWORD = "Ashish@007";

    public void addEmployee(Employee emp) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO employee (id, name, department) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, emp.getId());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getDepartment());
            ps.executeUpdate();
            System.out.println("Employee added.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayEmployees() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM employee";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Department: " + rs.getString("department"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(int id, String newName, String newDept) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "UPDATE employee SET name = ?, department = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, newName);
            ps.setString(2, newDept);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee updated.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "DELETE FROM employee WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee deleted.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        EmployeeJDBC db = new EmployeeJDBC();

        Employee e1 = new Employee(1, "Ashish", "IT");
        Employee e2 = new Employee(2, "Neha", "HR");

        db.addEmployee(e1);
        db.addEmployee(e2);

        System.out.println("\nAll Employees:");
        db.displayEmployees();

        System.out.println("\nUpdating Employee ID 1:");
        db.updateEmployee(1, "Ashish Odedra", "DevOps");

        System.out.println("\nAfter Update:");
        db.displayEmployees();

        System.out.println("\nDeleting Employee ID 2:");
        db.deleteEmployee(2);

        System.out.println("\nAfter Deletion:");
        db.displayEmployees();
    }
}
