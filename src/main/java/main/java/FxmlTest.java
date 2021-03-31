package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.naming.ldap.Control;
import java.io.IOException;

public class FxmlTest extends Application {

    FXMLLoader loader;

    @Override
    public void start(Stage stage) throws Exception{
        //FXMLLoader instance
        loader = new FXMLLoader(getClass().getResource("/Parent.fxml"));
//        System.out.println(getClass().getResource("TTT1.fxml"));
//        System.out.println(getClass().getResource("TTT1.fxml").getClass());
        Parent root = (Parent)loader.load();
        FxmlConnector controller =  loader.getController();
        //controller.setItems();

        stage.setTitle("Zarathustra");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public FXMLLoader getLoader() {
        return loader;
    }
}
