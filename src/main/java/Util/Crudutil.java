package Util;

import controller.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Crudutil {

    static Connection connection= DBconnection.getInstance().getConnection();
    public static <T>T execute(String sql, Object... args) throws SQLException {
        PreparedStatement psTm = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            psTm.setObject((i+1),args[i]);
        }
        if (sql.startsWith("SELECT")||sql.startsWith("select")){
            return (T) psTm.executeQuery();
        }
        return (T)  (Boolean) (psTm.executeUpdate()>0);
    }
}
