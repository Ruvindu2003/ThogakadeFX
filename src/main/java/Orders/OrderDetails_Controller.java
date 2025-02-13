package Orders;

import controller.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetails_Controller {
    public boolean addOrderDetails (List<OrderDetails> orderDetails){

            for (OrderDetails orderDetail : orderDetails) {
                boolean isOrderDetailAdd = addOrderDetails(orderDetail);
                if (!isOrderDetailAdd){
                    return false;
                }
            }
            return true;

    }

    public  boolean addOrderDetails(OrderDetails orderDetails) {
        String sql = "Insert INTO  orderdetail VALUES (?,?,?,?)";
        Connection connection = DBconnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderDetails.getId());
            preparedStatement.setString(2, orderDetails.getItemcode());
            preparedStatement.setString(3, String.valueOf(orderDetails.getQty()));
            preparedStatement.setString(4, String.valueOf(orderDetails.getUniteprice()));

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    }


