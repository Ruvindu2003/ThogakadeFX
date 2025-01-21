package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static DBconnection instance;

    private Connection connection;

    private  DBconnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade",
                    "root",
                    "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        return  connection;
    }
    public static  DBconnection getInstance(){
        return  instance == null ? instance=new DBconnection() :instance;
    }
}






