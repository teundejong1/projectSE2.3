package main.java;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class FxmlConnector {
    public ComboBox TTTbox;
    public ComboBox othellobox;
    public AnchorPane parent;
    @FXML
    private Label label;
    private final ObservableList<String> opties = FXCollections.observableArrayList("speler vs speler");

    public void setItems() {
        TTTbox.getItems().add(opties.get(0));
    }


    public void opgeefActie(ActionEvent actionEvent) {
        System.out.println("...noob");
    }


    public void openMenu(ActionEvent actionEvent) {
        parent.getChildren().setAll(FXMLLoader.load("vista2.fxml"));
    }

    public void playTicTacToe(ActionEvent actionEvent) {
        //TODO
    }

    public void TTTInfo(ActionEvent actionEvent) {
        //TODO
    }

    public void OthelloInfo(ActionEvent actionEvent) {
        //TODO
    }
}