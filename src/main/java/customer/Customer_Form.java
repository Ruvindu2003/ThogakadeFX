package customer;

import Controller.DBconnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Customer_Form {
    public TextField lbl_id;
    public TextField lbl_name;
    public TextField lbl_customer_Adrees;
    public TextField customer_salary1;
    public TableView tbl_customer;
    public TableColumn colum_id;
    public TableColumn colum_name;
    public TableColumn colum_adrees;
    public TableColumn colum_salary;
    Customer_Controller customerController=new Customer_Controller();

    public void Add_Customer_Action(ActionEvent actionEvent) {
        Connection connection= DBconnection.getInstance().getConnection();
        customerController.Additems(new Customer(
                lbl_id.getText(),
                lbl_name.getText(),
                lbl_customer_Adrees.getText(),
                Double.parseDouble(customer_salary1.getText())
        ));

    }

    public void update_customer_Action(ActionEvent actionEvent) {

        boolean b=customerController.updateItem(new Customer(
                lbl_id.getText(),
                lbl_name.getText(),
                lbl_customer_Adrees.getText(),
                Double.parseDouble(customer_salary1.getText())
        ));


    }

    public void search_Customer_Action(ActionEvent actionEvent) {


        Customer customer=customerController.searchItem(lbl_id.getText());
        if (customer!=null){

        }
        lbl_name.setText(String.valueOf(customer.getName()));
        lbl_customer_Adrees.setText(String.valueOf(customer.getAdress()));
        customer_salary1.setText(String.valueOf(customer.getSalary()));

    }

    public void View_Customer_Action(ActionEvent actionEvent) {
        colum_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        colum_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        colum_adrees.setCellValueFactory(new PropertyValueFactory<>("adress"));
        colum_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));


        loadtable();


    }

    public void Delete_customer_Action(ActionEvent actionEvent) {
        Connection connection = DBconnection.getInstance().getConnection();
        String SQL = "Delete from Customer  where id =?";
        try {
            PreparedStatement pst = connection.prepareStatement(SQL);
            pst.setString(1, lbl_id.getText());

            if (pst.executeUpdate() > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Suse's fully Delete ").show();

            } else {
                new Alert(Alert.AlertType.INFORMATION, "Not sucses fully").show();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public  void  loadtable(){
        List<Customer> customerObservableList=Customer_Controller.getInstance().getAll();
        tbl_customer.setItems((ObservableList) customerObservableList);
    }
}
