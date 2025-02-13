package repositiry.custom;

import Orders.Order;
import repositiry.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, String> {
}
