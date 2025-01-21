package Dash;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBord_Form {


    public AnchorPane Acncor_pain_home;

    public void Customer_Action(ActionEvent actionEvent) throws IOException {
//        Stage stage =new Stage();
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View2/Customer.fxml"))));
//            stage.show();

        URL url= this.getClass().getResource("/View2/Customer.fxml");
        assert  url !=null;

        Parent  load=FXMLLoader.load(url);
        this.Acncor_pain_home.getChildren().clear();
        this.Acncor_pain_home.getChildren().add(load);

    }

    public void Items_Action(ActionEvent actionEvent) throws IOException {
       // Stage stage=new Stage();
       // stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Items.fxml"))));
       // stage.show();

        URL url=this.getClass().getResource("/View/Items.fxml");
        assert  url !=null;
        Parent load =FXMLLoader.load(url);
        this.Acncor_pain_home.getChildren().clear();
        this.Acncor_pain_home.getChildren().add(load);

    }

    public void order_Action_btn(ActionEvent actionEvent) throws IOException {

        System.out.println("ADoo");

        URL url =this.getClass().getResource("/Order_View/Order.fxml");
        assert  url !=null;
        Parent load=FXMLLoader.load(url);
        this.Acncor_pain_home.getChildren().clear();
        this.Acncor_pain_home.getChildren().add(load);
    }
}
