package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modle.Items;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewForm {

    ItemService itemController = new ItemController();

    @FXML
    private TableColumn<?, ?> colum_code;

    @FXML
    private TableColumn<?, ?> colum_description;

    @FXML
    private TableColumn<?, ?> colum_qtyHands;

    @FXML
    private TableColumn<?, ?> colum_uniteprice;

    @FXML
    private TableView<Items> tbl_items;
    List<Items> items = new ArrayList<>();

    @FXML
    void btn_Relode_Action(ActionEvent event) {
        colum_code.setCellValueFactory(new PropertyValueFactory<>("code"));
        colum_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        colum_uniteprice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colum_qtyHands.setCellValueFactory(new PropertyValueFactory<>("qtyonHand"));


        loadtable();
    }


    public void loadtable() {

        //ObservableList<Items> itemsObservableList = FXCollections.observableArrayList();
        ObservableList<Items> itemsList = ItemController.getInstance().getAll();
        tbl_items.setItems(itemsList);

    }


}
