package main.java;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class FxmlConnector {
    public ComboBox TTTbox;
    public ComboBox othellobox;
    public AnchorPane parent;
    public Button butt00;
    public Button butt01;
    public Button butt02;
    public Button butt10;
    public Button butt11;
    public Button butt12;
    public Button butt20;
    public Button butt21;
    public Button butt22;
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
        parent.getChildren().setAll();
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

    public void setGrid(ActionEvent event) {
        if(event.getSource() == butt00) {
            butt00.setText("test");
        } else if(event.getSource() == butt01) {

        } else if(event.getSource() == butt02) {

        } else if(event.getSource() == butt10) {

        } else if(event.getSource() == butt11) {

        } else if(event.getSource() == butt12) {

        } else if(event.getSource() == butt20) {

        } else if(event.getSource() == butt21) {

        } else if(event.getSource() == butt22) {

        }
    }
}