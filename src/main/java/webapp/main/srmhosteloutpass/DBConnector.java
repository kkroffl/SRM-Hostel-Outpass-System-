package webapp.main.srmhosteloutpass;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/hostel_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Koushik@123";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        System.out.println("Inside DB Connector");
    }

}
