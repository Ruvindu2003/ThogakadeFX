package Login;

import customer.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modle.Login;

import java.io.IOException;
import java.sql.SQLException;

public class Loging_Form {

    @FXML
    private TextField Login_Pss_Word;

    @FXML
    private TextField Login_user_name;

    @FXML
    void btn_Login_Action(ActionEvent event) throws SQLException {

        Login customer = Loging_controller.getInstance().getCustomer(Login_user_name.getText(), Login_Pss_Word.getText());
        System.out.println(customer);
        if (customer==null){return;}
        Stage stage=new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBord.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    public void btn_rejister_Action(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Rejister_cus_/Rejister.fxml"))));
        stage.show();


    }
}
