package Login;

import Controller.DBconnection;
import modle.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Loging_controller implements  Loging_service{

    public  static  Loging_controller instance;

    public  static  Loging_controller getInstance(){
        return  instance==null? instance=new Loging_controller():instance;
    }


    @Override
    public Login getCustomer(String user_name,String password) throws SQLException {
        Connection connection = DBconnection.getInstance().getConnection();
        String query= "select * from users where username=? and password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,user_name);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            Login login = new Login(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            System.out.println(login);
           return login;

        }
return  null;

    }
}
//Connection connection = DBconnection.getInstance().getConnection();
//String query= "select * from users where username=? and password=?";
//PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setString(1,user_name);
//        preparedStatement.setString(2,password);
//ResultSet resultSet = preparedStatement.executeQuery();
//        if (resultSet.next()){
//Login login = new Login(resultSet.getInt(1),
//        resultSet.getString(2),
//        resultSet.getString(3),
//        resultSet.getString(4)
//);
//            System.out.println(login);
//           return login;
//
