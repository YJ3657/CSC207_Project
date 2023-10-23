//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConnectionExample {
//    public static void main(String[] args) {
//        Connection connection null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/test",
//                    "root",
//                    "root"
//            );
//            System.out.println("Connection made successful");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if(connection != null) {
//                try {
//                    connection.close();
//                    System.out.println("Connection out");
//                } catch (SQLException e) {}
//            }
//        }
//    }
//}