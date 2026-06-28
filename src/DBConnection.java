

import java.sql.*;

public class DBConnection{
    private static final String url="jdbc:mysql://localhost:3306/inventory_billing_sys";
    private static final String username="your_username";
    private static final String password="your_password";

    public static Connection getConnection(){
        Connection con = null;

        try {
            con =DriverManager.getConnection(url,username,password);
            //System.out.println("DB Connection Successful");
        } catch (SQLException e) {
            System.out.println("Error in DB Connection: "+e.getMessage());
        }
        return con;
    }
}