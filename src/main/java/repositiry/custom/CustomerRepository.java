package repositiry.custom;

import customer.Customer;
import repositiry.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {
}
