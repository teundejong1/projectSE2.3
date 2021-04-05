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
    View view;

    public FxmlTest() {
        view =  new View();
    }

    //Dit is hier zodat we de view vanuit de rest van het project kunnen benaderen
    public View getView() {
        return view;
    }

    @Override
    public void start(Stage stage) throws Exception{
        //FXMLLoader instance

        loader = new FXMLLoader(getClass().getResource("/fxml/oneWindow.fxml"));
        root = loader.load();
        controller =  loader.getController();
        controller.setView(view);

        stage.setTitle("Buttons");
        stage.setScene(new Scene(root));
        stage.show();

//        switch (schermchecker) {
//            case 1:
//                loader = new FXMLLoader(getClass().getResource("/fxml/TTT1.fxml"));
//                root = loader.load();
//                controller =  loader.getController();
//                break;
//            default:
//                loader = new FXMLLoader(getClass().getResource("/fxml/menu1.fxml"));
//                root = loader.load();
//                controller =  loader.getController();
//
//                break;
//        }
//
//
//
//        stage.setTitle("Zarathustra");
//        stage.setScene(new Scene(root));
//        stage.show();
    }

    @Override
    public void run() {
        launch();
    }

    public void showMenu() {
        schermchecker = 0;
        launch();
    }

    public void showTTT() {
        schermchecker = 1;
        launch();
    }
}
