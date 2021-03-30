package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.naming.ldap.Control;
import java.io.IOException;

public class FxmlTest extends Application {



    @Override
    public void start(Stage stage) throws Exception{
        //FXMLLoader instance
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu1.fxml"));
//        System.out.println(getClass().getResource("TTT1.fxml"));
//        System.out.println(getClass().getResource("TTT1.fxml").getClass());
        Parent root = (Parent)loader.load();
        Controller controller =  loader.getController();
        controller.setLabelText("Injected Text");

        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }
}
