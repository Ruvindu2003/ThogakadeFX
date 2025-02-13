package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import modle.Items;

import java.sql.*;

public class ItemController implements ItemService {
  private static ItemController itemController;

    public static ItemController getInstance(){
        if(itemController==null){
            return itemController =new ItemController();
        }
        return itemController;
    }


    Connection connection=DBconnection.getInstance().getConnection();
    @Override
    public ObservableList<Items> getAll() {
        ObservableList<Items> items = FXCollections.observableArrayList();
        //List<Items> items=new ArrayList<>();

        try {
            Connection  connection= DBconnection.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select * from item");
            while (resultSet.next()){
                items.add(
                        new Items(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getDouble(3),
                                resultSet.getInt(4)
                        )

                );


            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    @Override
    public Items searchItem(String item) {
        Items searchItemObject=null;
        try {


            PreparedStatement pst = connection.prepareStatement("SELECT * FROM item WHERE code=?");
            pst.setString(1, item);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                searchItemObject =  new Items(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchItemObject;

    }
    @Override
    public boolean updateItem(Items items) {

        try (Connection connection = DBconnection.getInstance().getConnection()) {
            String SQL = "UPDATE item SET description = ?, unitPrice = ?, qtyOnHand = ? WHERE code = ?";
            try (PreparedStatement stm = connection.prepareStatement(SQL)) {
                // Setting the parameters for the prepared statement
                stm.setString(1, items.getDescription());
                stm.setDouble(2, Double.parseDouble(String.valueOf(items.getUnitprice())));
                stm.setInt(3, Integer.parseInt(String.valueOf(items.getQtyonHand())));
                stm.setString(4, items.getCode());

                // Executing the update and checking if it succeeded
                return stm.executeUpdate()>0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while updating the item: " + e.getMessage()).showAndWait();


        }

        return false;
    }
    @Override
    public boolean deleteItem(String code) {
        return false;
    }
    @Override
    public void Additems(Items items) {
        

        try {
            PreparedStatement pst = connection.prepareStatement("Insert into item values (?,?,?,?) ");
            pst.setString(1,items.getCode());
            pst.setString(2, items.getDescription());
            pst.setDouble(3, Double.parseDouble(String.valueOf(items.getUnitprice())));
            pst.setInt(4, Integer.parseInt(String.valueOf(items.getQtyonHand())));
            if (pst.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.INFORMATION, "Item Add", new ButtonType[0])).show();
            }

        } catch (SQLException var4) {
            SQLException e = var4;
            throw new RuntimeException(e);
        }


    }
}
