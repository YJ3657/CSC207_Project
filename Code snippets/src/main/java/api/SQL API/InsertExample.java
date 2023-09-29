import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class ConnectionExample {
    public static void main(String[] args) {
        Connection connection null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test",
                    "root",
                    "root"
            );
            System.out.println("Connection made successful");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            String statement = "INSERT INTO boards (id, pw, problems, logintime, notes) " +
            "VALUES (?, ?, ?, ?, now(), ?)";

            PreparedStatement pstatement = conn.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
            pstatement.setString(1, "ryuyeon3");
            pstatement.setString(2, "password");
            pstatement.setString(3, "25");
            pstatement.setString(5, "Having problem with quesiton.4");
            pstatement.executeUpdate(); // Pushing the SQL statement
            pstatement.close();
            if(connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection out");
                } catch (SQLException e) {}
            }
        }
    }
}