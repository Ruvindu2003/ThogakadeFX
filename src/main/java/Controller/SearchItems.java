package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modle.Items;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchItems {
    ItemService itemController=new ItemController();

    @FXML
    private Label lbl_Description;

    @FXML
    private TextField lbl_code;

    @FXML
    private Label lbl_qtyhands;

    @FXML
    private Label lbl_unitPrice;

    @FXML
    void btn_searchCustomer_Action(ActionEvent event) throws SQLException {

        Items itemss=itemController.searchItem(lbl_code.getText());
        if(itemss!=null){

        }

            lbl_Description.setText(itemss.getDescription());
            lbl_unitPrice.setText(String.valueOf(itemss.getUnitprice()));
            lbl_qtyhands.setText(String.valueOf(itemss.getUnitprice()));



    }

}
