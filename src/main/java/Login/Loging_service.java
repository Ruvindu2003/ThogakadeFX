package Login;

import customer.Customer;
import modle.Login;

import java.sql.SQLException;
import java.util.List;

public interface Loging_service {

    Login getCustomer(String user_name,String password) throws SQLException;

}
