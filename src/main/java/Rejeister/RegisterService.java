package Rejeister;

import modle.Login;

import java.util.List;

public interface RegisterService {
    List<Login> getAlluser();
    Boolean addUser(Login login);
}
