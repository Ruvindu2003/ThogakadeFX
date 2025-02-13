package customer;

import controller.DBconnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import service.ServiceFactory;
import service.custome.CustomeService;
import Util.Service_Type;
import javafx.collections.FXCollections;
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
    CustomeService customeService=ServiceFactory.getInstance().getServiceType(Service_Type.Customer);
    public void Add_Customer_Action(ActionEvent zactionEvent) {
        Connection connection= DBconnection.getInstance().getConnection();

        customeService.Additems(new Customer(
                lbl_id.getText(),
                lbl_name.getText(),
                lbl_customer_Adrees.getText(),
                Double.parseDouble(customer_salary1.getText())
        ));

    }

    public void update_customer_Action(ActionEvent actionEvent) {

        boolean b=customeService.updateItem(new Customer(
                lbl_id.getText(),
                lbl_name.getText(),
                lbl_customer_Adrees.getText(),
                Double.parseDouble(customer_salary1.getText())
        ));


    }

    public void search_Customer_Action(ActionEvent actionEvent) {


        Customer customer=customeService.searchItem(lbl_id.getText());
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
        List<Customer> customerObservableList= customeService.getAll();
        tbl_customer.setItems(FXCollections.observableArrayList(customerObservableList));
    }

    public void Report_Action(ActionEvent actionEvent) {
        try {
            JasperDesign design=JRXmlLoader.load("src/main/resources/Report/DailyReport.jrxml");
            JasperReport jasperReport =JasperCompileManager.compileReport(design);
            JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport,null,DBconnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(Jasperprint,"Customerpdf");
            JasperViewer.viewReport(Jasperprint,false);

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
