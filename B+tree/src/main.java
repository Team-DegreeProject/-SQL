import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String URL = "jdbc:OurSql";
        String USER = "xxx";
        String PASSWORD = "xxx";
        Connection conn=null;
        try {
            Class.forName("OurSQLDriver.OurSqlDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
