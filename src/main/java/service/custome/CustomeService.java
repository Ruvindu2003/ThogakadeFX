package service.custome;

import service.SuperService;
import customer.Customer;

import java.util.List;

public interface CustomeService extends SuperService {
    List<Customer> getAll();

    Customer searchItem(String customer);

    boolean updateItem(Customer customer);

    boolean deleteItem(Customer  id);

    Object Additems(Customer customer);
}



