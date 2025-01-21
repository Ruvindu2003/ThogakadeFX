package Rejeister;

import modle.Login;

import java.util.List;

public interface Rejister_service {
    List<Login> getAlluser();
    Boolean addUser(Login login);
}
