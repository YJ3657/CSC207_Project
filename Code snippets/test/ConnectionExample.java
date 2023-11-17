//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class ConnectionExample {
//    public static void main(String[] args) {
//        Connection conn = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://100.70.207.179:3306/thisisjava",
//                    "remoteUser",
//                    "thisismysql*"
//            );
//            System.out.println("Connection Success");
//
//            String sql = "INSERT INTO users (userid, username, userpassword, userage, useremail) " +
//                    "VALUES (?, ?, ?, ?, ?)";
//
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, "Matthew");
//            pstmt.setString(2, "matth");
//            pstmt.setString(3, "124");
//            pstmt.setInt(4, 100);
//            pstmt.setString(5, "matty@mycompany.com");
//
//            int rows = pstmt.executeUpdate();
//            System.out.println("saved row number: " + rows);
//            pstmt.close();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if(conn != null) {
//                try {
//                    conn.close();
//                    System.out.println("Connection cut");
//                } catch (SQLException e) {}
//            }
//        }
//    }
//
//}