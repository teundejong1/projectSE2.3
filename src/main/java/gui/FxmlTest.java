package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import gui.Controller;

public class FxmlTest extends Application implements Runnable{

    FXMLLoader loader;
    int schermchecker = 0;
    Parent root;
    Controller controller;
    Parent menu;
    MenuController menuController;


    public FxmlTest() {
    }


    @Override
    public void start(Stage stage) throws Exception{
        // basis scherm
        loader = new FXMLLoader(getClass().getResource("/fxml/oneWindow.fxml"));
        root = loader.load();
        controller =  loader.getController();

        controller.setMenu();

        stage.setTitle("Buttons");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void run() {
        launch();
    }
}
