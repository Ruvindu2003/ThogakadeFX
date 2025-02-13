package service.custome.impl;

import service.custome.CustomeService;
import Util.Crudutil;
import customer.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceimpl implements CustomeService {

    private  static  CustomerServiceimpl instance;

    public static   CustomerServiceimpl getInstance( ) {

        if (instance == null) {

            return instance = new CustomerServiceimpl();
        }
        return instance;
    }


    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();
        try {
            ResultSet resultSet = Crudutil.execute("SELECT * FROM customer");
            while (resultSet.next()) {
                customerList.add(new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;





    }

    @Override
    public Customer searchItem(String customer) {



        return null;
    }

    @Override
    public boolean updateItem(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteItem(Customer id) {
        return false;
    }

    @Override
    public Object Additems(Customer customer) {
        try {
            String SQL = "INSERT INTO customer VALUES(?,?,?,?)";
            return Crudutil.execute(SQL,
                    customer.getId(),
                    customer.getName(),
                    customer.getAdress(),
                    customer.getSalary()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
