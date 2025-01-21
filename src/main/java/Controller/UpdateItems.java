package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modle.Items;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateItems {

    ItemService itemController=new ItemController();
    @FXML
    private TextField lbl_Descripton;

    @FXML
    private TextField lbl_code;

    @FXML
    private TextField lbl_qtyonhands;

    @FXML
    private TextField lbl_uniteprice;

    @FXML
    void btn_update_items(ActionEvent event) throws RuntimeException {
        boolean b = itemController.updateItem(new Items(
                lbl_code.getText(),
                lbl_Descripton.getText(),
                Double.parseDouble(lbl_uniteprice.getText()),
                Integer.parseInt(lbl_qtyonhands.getText())


        ));


    }

        public void btn_Search(ActionEvent actionEvent) throws SQLException {
        Connection connection= DBconnection.getInstance().getConnection();

        PreparedStatement pst =connection.prepareStatement("SELECT * FROM item WHERE code = ?");
        pst.setString(1,lbl_code.getText().trim());

        ResultSet resultSet=pst.executeQuery();

        if (resultSet.next()){
            lbl_Descripton.setText(resultSet.getString("description").trim());
            lbl_uniteprice.setText(String.valueOf(resultSet.getDouble("unitPrice")));
            lbl_qtyonhands.setText(String.valueOf(resultSet.getInt("qtyOnHand")));

        }else {
            new Alert(Alert.AlertType.INFORMATION,"Not Success full").show();
        }

        resultSet.close();
        pst.close();


    }


}

