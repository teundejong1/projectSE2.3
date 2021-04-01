import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public ComboBox TTTbox;
    public ComboBox othellobox;
    public AnchorPane parent;
    public Button butt00, butt01, butt02, butt10, butt11, butt12, butt20, butt21, butt22;
    private final ObservableList<String> opties = FXCollections.observableArrayList("speler vs speler");

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        for(int i = 0; i < 9; i++) {
//            Button button =  new Button();
//            button.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    System.out.println("You clicked me!");
//                }
//            });
//        }
//    }


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

    /*
    * Do moves --> actie om een zet uit te voeren
    */
    public void setGrid(ActionEvent event) {
        if(event.getSource() == butt00) {
            butt00.setText("O");
            // if(GameManager.doMove(0, 1)){
            //      butt00.setText(currentPlayer.getPlayerMark())
            // }
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