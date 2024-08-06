package managemant_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDatabaseManager {
	
    private static final String URL = "jdbc:mysql://0.0.0.1:xxxx/DB_name"; // JDBC Connection string
    private static final String USER = "root";
    private static final String PASSWORD = "your password";  // password

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addStudent(String firstName, String lastName, String email, int age) {
        String sql = "INSERT INTO students(first_name, last_name, email, age) VALUES(?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setInt(4, age);
            pstmt.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getStudents() {
        String sql = "SELECT * FROM students";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                                   rs.getString("first_name") + "\t" +
                                   rs.getString("last_name") + "\t" +
                                   rs.getString("email") + "\t" +
                                   rs.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateStudent(int id, String firstName, String lastName, String email, int age) {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, email = ?, age = ? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setInt(4, age);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            System.out.println("Student updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Student deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        StudentDatabaseManager manager = new StudentDatabaseManager();
//        manager.addStudent("Shivansh", "Rawat", "shivanshr.20@gmail.com", 23);
//        manager.getStudents();
        manager.updateStudent(4, "Shivansh", "Rawat", "john.smith@example.com", 22);
//        manager.getStudents();
//        manager.deleteStudent(1);
//        manager.getStudents();
    }
}
