package customer;

import java.util.List;

public interface Customer_Service {

    List<Customer> getAll();

    Customer searchItem(String customer);

    boolean updateItem(Customer customer);

    boolean deleteItem(Customer  id);

    void  Additems(Customer customer);
}
