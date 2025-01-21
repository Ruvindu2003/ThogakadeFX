package Rejeister;

import Controller.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modle.Login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Rejister_form implements Initializable {

    public TextField txt_id;
    public TextField txt_user_name;
    public TextField txt_Passsword;
    public TextField txt_Email;
    public TextField txt_conform_passsword;

    public void add_rejister_action(ActionEvent actionEvent) throws IOException {
        String key ="12345";

       Login basicTextEncryptor = new Login();
        basicTextEncryptor.setPassword(key);

        if (txt_Passsword.getText().equals(txt_Passsword.getText())){
            System.out.println(true);
            Connection connection = DBconnection.getInstance().getConnection();
            ResultSet resultSet = null;
            try {
                resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email=" + "'" + txt_Email.getText() + "'");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (!resultSet.next()){
                    System.out.println(false);

                    String SQL = "INSERT INTO users (username,email,password) VALUES (?,?,?)";
                    PreparedStatement psTm = connection.prepareStatement(SQL);
                    psTm.setString(1,txt_user_name.getText());
                    psTm.setString(2,txt_Email.getText());
                    psTm.setString(3,txt_Passsword.getText());
                    psTm.executeUpdate();

                }else{
                    System.out.println(true);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else {
            System.out.println(false);
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
