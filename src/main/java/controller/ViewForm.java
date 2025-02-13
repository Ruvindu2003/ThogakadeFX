package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modle.Items;

import java.util.ArrayList;
import java.util.List;

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
