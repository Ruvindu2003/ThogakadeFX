package customer;

import Controller.DBconnection;
import Controller.ItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;
import java.util.List;



public class Customer_Controller implements Customer_Service {

    private static Customer_Controller instance;

    public static Customer_Controller getInstance(){
        if(instance==null){
            return instance =new Customer_Controller();
        }
        return instance;
    }

    Connection connection= DBconnection.getInstance().getConnection();


    @Override
    public List<Customer> getAll() {

        ObservableList<Customer>customerObservableList= FXCollections.observableArrayList();

        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select * from customer");
            while (resultSet.next()){
                customerObservableList.add(
                        new Customer(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4)

                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return customerObservableList;
    }

    @Override
    public Customer searchItem(String customer) {
        Customer searchItemobject =null;

        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM customer WHERE id=?");
            pst.setString(1,customer);

            ResultSet res=pst.executeQuery();

            if (!res.next()) {
                return null;
            }

            searchItemobject=new Customer(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getDouble(4)
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return searchItemobject;
    }

    @Override
    public boolean updateItem(Customer customer) {
        try {
            PreparedStatement pst=connection.prepareStatement("UPDATE Customer SET name = ?,address=?,salary=? WHERE id=?");

            pst.setString(1, customer.getName());
            pst.setString(2,customer.getAdress());
            pst.setString(3, String.valueOf(Double.parseDouble(String.valueOf(customer.getSalary()))));
            pst.setString(4,customer.getId());


            // Executing the update and checking if it succeeded
            return pst.executeUpdate()>0;
        } catch (SQLException e) {
            new  Alert(AlertType.CONFIRMATION,"Suchses not").show();
            throw new RuntimeException(e);

        }


    }

    @Override
    public boolean deleteItem(Customer id) {




            return false;
    }

    @Override
    public void Additems(Customer customer) {
        try {
            PreparedStatement pst= connection.prepareStatement("Insert into customer values (?,?,?,?)");

            pst.setString(1,customer.getId());
            pst.setString(2, customer.getName());
            pst.setString(3, customer.getAdress());
            pst.setDouble(4,Double.parseDouble(String.valueOf(customer.getSalary())));

            if(pst.executeUpdate()>0) new Alert(AlertType.CONFIRMATION, "Add Success;").show();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
