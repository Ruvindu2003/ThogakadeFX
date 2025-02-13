package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeletItem {

    @FXML
    private TextField lbl_Descripton;

    @FXML
    private TextField lbl_code;

    @FXML
    private TextField lbl_qtyonhands;

    @FXML
    private TextField lbl_uniteprice;

    @FXML
    void btn_Delete_Items(ActionEvent event)  {



        Connection connection=DBconnection.getInstance().getConnection();
        String SQL="Delete from item  where code =?";
        try {
            PreparedStatement pst=connection.prepareStatement(SQL);
            pst.setString(1,lbl_code.getText());

            if(pst.executeUpdate()>0) {
                new Alert(Alert.AlertType.INFORMATION, "Suse's fully Delete ").show();

            }else {
                new  Alert(Alert.AlertType.INFORMATION,"Not sucses fully").show();;
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btn_Search(ActionEvent event) throws SQLException {
        Connection connection= DBconnection.getInstance().getConnection();

        PreparedStatement pst;
        pst = connection.prepareStatement("SELECT * FROM item WHERE code = ?");
        pst.setString(1,lbl_code.getText());

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


