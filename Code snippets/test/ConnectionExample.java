import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionExample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://csc207:3306/thisisjava",
                    "remoteUser",
                    "thisismysql*"
            );
            System.out.println("Connection Success");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection cut");
                } catch (SQLException e) {}
            }
        }
    }

}