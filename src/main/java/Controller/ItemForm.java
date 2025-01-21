package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modle.Items;

import java.io.IOException;
import java.sql.Connection;

public class ItemForm {
    ItemController itemController=new ItemController();

    @FXML
    private TextField txt_description;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_qtyonhand;

    @FXML
    private TextField txt_unitprice;

    @FXML
    void btn_Add_items_Action(ActionEvent event) {
        Connection connection= DBconnection.getInstance().getConnection();
        itemController.Additems(new Items(
                txt_id.getText(),
                txt_description.getText(),
                (Double.parseDouble(txt_unitprice.getText())),
                (Integer.parseInt(txt_qtyonhand.getText()))


        ));


    }

    @FXML
    void btn_Delete_items_Action(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/Delete.fxml"))));
        stage.show();

    }

    @FXML
    void btn_serach_items_Action(ActionEvent event) throws IOException {

        Stage stage =new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/Search_Items.fxml"))));
        stage.show();
    }

    @FXML
    void btn_view_items_Action(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/View_item.fxml"))));
        stage.show();


    }

    @FXML
    void update_Items_Action(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/update.fxml"))));
        stage.show();

    }

}
