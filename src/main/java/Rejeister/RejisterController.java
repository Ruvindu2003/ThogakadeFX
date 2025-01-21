package Rejeister;

import Controller.DBconnection;
import modle.Login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RejisterController  implements  Rejister_service{
    public  static  RejisterController instance;

    private  RejisterController (){

    }
        public static  RejisterController getInstance()   {
        return instance ==null ? instance=new RejisterController():instance;
        }

    @Override
    public List<Login> getAlluser() {

        List<Login>logins=new ArrayList<>();
        try {
            ResultSet resultSet= (ResultSet) DBconnection.getInstance().getConnection().createStatement().executeQuery("Select * from users");
            while (resultSet.next()){
                boolean add = logins.add(new Login(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return logins;
    }



    @Override
    public Boolean addUser(Login login) {
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=DBconnection.getInstance().getConnection().prepareStatement("Insert into users values(?,?,?,?)");
            preparedStatement.setString(1, login.getId());
            preparedStatement.setString(2,login.getUsername());
            preparedStatement.setString(3,login.getEmail());
            preparedStatement.setString(4,login.getPassword());
            return  preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
