package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.awt.event.MouseEvent;
import java.util.HashMap;

public class InlogstatusController {
    Controller controller;
    @FXML
    VBox uitdagingen;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void updateChallengers(HashMap<String, String> challenger) {
        //{CHALLENGER: "Sjors", GAMETYPE: "Guess Game", CHALLENGENUMBER: "1"}
        String name = challenger.get("CHALLENGER");
        String game= challenger.get("GAMETYPE");
        //Label label = new Label()
    }

    public void removeChallenge(HashMap<String, String> details){
        // CANCELLED {CHALLENGENUMBER: "<uitdaging nummer>"}

    }

    public void clearChallenges(){
        uitdagingen.getChildren().clear();
    }

    public void acceptChallenge(MouseEvent mouseEvent){
        // challenge accept <uitdaging nummer>

    }
}
