package repositiry.custom.impl;

import Orders.Order;
import repositiry.custom.OrderRepository;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    public static OrderRepositoryImpl instnse;

    public static OrderRepositoryImpl getInstance(){
        if (instnse==null){
            instnse=new OrderRepositoryImpl();
        }
        return  instnse;
    }
    @Override
    public boolean Add(Order entity) {

        return false;
    }

    @Override
    public Order search(String s) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(Order entity) {
        return false;
    }

    @Override
    public List<Order> getAll() {
        return List.of();
    }
}
