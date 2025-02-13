package repositiry.custom.impl;

import customer.Customer;
import repositiry.custom.CustomerRepository;

import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    public static  CustomerRepositoryImpl instance;

    public static  CustomerRepositoryImpl getInstance(){
        if (instance==null){
         return   instance=new CustomerRepositoryImpl();
        }
        return instance;
    }
    @Override
    public boolean Add(Customer entity) {

        return false;
    }

    @Override
    public Customer search(String s) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(Customer entity) {
        return false;
    }

    @Override
    public List<Customer> getAll() {
        return List.of();
    }
}
