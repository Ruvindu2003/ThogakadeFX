package Orders;

import Controller.DBconnection;
import Controller.ItemController;
import customer.Customer;
import customer.Customer_Controller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import modle.Items;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Order_Form implements Initializable {

    public Label lbl_id;
    public Label lbl_time;
    public ComboBox combo_order_id;
    public TextField txt_Adrees;
    public TextField txt_Salary;
    public ComboBox combo_item_id;
    public TextField txt_description;
    public TextField txt_unite_price;
    public TextField txt_qty;
    public TableView tbl_card;
    public Label lbl_total;
    public TableColumn col_itemcode;
    public TableColumn col_description;
    public TableColumn col_qty;
    public TableColumn colum_unitprice;
    public TableColumn colum_total;
    public TextField txt_tqqq;
    public TextField txt_orid;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_itemcode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        col_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colum_unitprice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colum_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadDateAndTime();


        loadDateAndTime();
        setCustomerid();
        setItemid();
        combo_order_id.getSelectionModel().selectedItemProperty().addListener((observableValue, ol, t1) -> {
            if (t1 != null) {
                setCustomerData((String) t1);
                System.out.println(t1);
            }
        });
        combo_item_id.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            if (t1 != null) {
                setitemsData((String) t1);
                System.out.println(t1);
            }
        });

    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        BreakIterator lblDate;
        lbl_id.setText(f.format(date));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime now = LocalTime.now();
                    lbl_time.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void setCustomerid() {
        ObservableList<String> customerobserverlist = FXCollections.observableArrayList();

        for (Customer customer : Customer_Controller.getInstance().getAll()) {
            customerobserverlist.add(customer.getId());
        }

        combo_order_id.setItems(customerobserverlist);


    }

    ObservableList<CarTm> carTmObservableList = FXCollections.observableArrayList();

    public void Add_Task_on_Action(ActionEvent actionEvent) {
        Double total = Double.parseDouble(txt_unite_price.getText()) * Integer.parseInt(txt_qty.getText());
        carTmObservableList.add(
                new CarTm(
                        combo_item_id.getValue().toString(),
                        txt_description.getText(),
                        Integer.parseInt(txt_tqqq.getText()),
                        Double.parseDouble(txt_unite_price.getText()),
                        total


                )
        );
        tbl_card.setItems(carTmObservableList);
        calacNetTotal();
        combo_order_id.setDisable(true);
    }

    private void calacNetTotal() {
        Double netTotal = 0.0;
        for (CarTm cartTM : carTmObservableList) {
            netTotal += cartTM.getTotal();
        }
        lbl_total.setText(netTotal.toString());
    }


    public void setItemid() {
        ObservableList<String> Iitemobserverlist = FXCollections.observableArrayList();
        for (Items items : ItemController.getInstance().getAll()) {
            Iitemobserverlist.add(items.getCode());
        }
        combo_item_id.setItems(Iitemobserverlist);
    }

    private void setCustomerData(String id) {
        Customer customer = Customer_Controller.getInstance().searchItem(id);
        txt_Adrees.setText(customer.getAdress());
        txt_Salary.setText(customer.getSalary().toString());


    }

    private void setitemsData(String code) {
        Items items = ItemController.getInstance().searchItem(code);
        txt_description.setText(items.getDescription());
        txt_unite_price.setText(String.valueOf(items.getUnitprice()));
        txt_qty.setText(String.valueOf(items.getQtyonHand()));


    }


    public void Add_Task_Action(ActionEvent actionEvent) {

    }

    public void btn_Place_order_Action(ActionEvent actionEvent) throws SQLException {

            Connection connection = DBconnection.getInstance().getConnection();


            String orderQuery = "INSERT INTO orders (id, date, customerId) VALUES (?, ?, ?)";


        try{
            connection.setAutoCommit(false);
            try {
                PreparedStatement orderStatement = connection.prepareStatement(orderQuery);
                orderStatement.setString(1, txt_orid.getText());
                orderStatement.setString(2, lbl_id.getText());
                orderStatement.setString(3, combo_order_id.getValue().toString());
                orderStatement.executeUpdate();

                ArrayList<OrderDetails> orderDetailList = new ArrayList<>();

                tbl_card.getItems().forEach(o -> {
                    CarTm item = (CarTm) o;

                    OrderDetails orderDetails = new OrderDetails(
                            txt_orid.getText(),
                            item.getItemcode(),
                            item.getQty(),
                            item.getUnitprice()


                    );

                    orderDetailList.add(orderDetails);
                });



                new OrderDetails_Controller().addOrderDetails(orderDetailList);

                new Alert(Alert.AlertType.INFORMATION,"Successes Fully").show();


            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

}



