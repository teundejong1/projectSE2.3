package main.java;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class Controller {
    @FXML
    private Label label;
    private ComboBox<String> tictactoebox;
    private ComboBox<String> othellobox;
    private final ObservableList<String> opties = FXCollections.observableArrayList("speler vs speler");

    public void setItems() {
        tictactoebox = new ComboBox<String>(opties);
    }


    public void opgeefActie(ActionEvent actionEvent) {
        System.out.println("...noob");
    }


    public void playTicTacToe(ActionEvent actionEvent) {

    }
}