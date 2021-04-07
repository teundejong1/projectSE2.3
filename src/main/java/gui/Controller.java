package gui;

import games.Othello;
import games.TicTacToe;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import player.Player;
import player.PlayerType;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadPoolExecutor;


public class Controller {
    private View view;

    @FXML
    public Button TTTvsspeler, TTTvsAI, Othellovsspeler, OthellovsAI;
    public AnchorPane parent;
    public Button butt00, butt01, butt02, butt10, butt11, butt12, butt20, butt21, butt22 ;
    private final ObservableList<String> opties = FXCollections.observableArrayList("speler vs speler", "speler vs AI");
    public AnchorPane gameAnchor;


    FXMLLoader loader;
    Parent root;
    Controller controller;



    public void opgeefActie(ActionEvent actionEvent) {
        System.out.println("...noob");
    }


//    public void TTT(ActionEvent actionEvent) {
//
//        try {
//            // close menu
//            Stage menu = (Stage) TTTvsAI.getScene().getWindow();
//            // do what you have to do
//            menu.close();
//
//            loader = new FXMLLoader(getClass().getResource("/fxml/menu1.fxml"));
//            root = loader.load();
//            controller =  loader.getController();
//
//            // open TTT
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/TTT1.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.initStyle(StageStyle.UNDECORATED);
//            stage.setTitle("Tic Tac Toe");
//            stage.setScene(new Scene(root1));
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Let's Play!
//        if(actionEvent.getSource() == TTTvsspeler) {
//
//        } else if(actionEvent.getSource() == TTTvsAI) {
//            // maak speler
//            Player player = new Player('x', "[PH] - FRANKENSTEIN");
//            AI ai = new AI('o', "[PH] - AI");
//            // start a game
//            Maintest.ticTacToe = new Maintest(player, ai);
//            System.out.println(Maintest.ticTacToe.ticTacToeManager.getCurrentPlayer().getPlayerName());
//            try {
//                Maintest.ticTacToe.ticTacToeManager.start(player, ai);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void playTicTacToe(ActionEvent actionEvent) {
        //TODO

    }

    public void TTTInfo(ActionEvent actionEvent) {
        //TODO
    }

    public void OthelloInfo(ActionEvent actionEvent) {
        //TODO
    }

//    /*
//    * Do moves --> actie om een zet uit te voeren
//    */
//    public void setGrid(ActionEvent event) {
//
//        if(event.getSource() == butt00) {
//
//            butt00.setText( "" + Maintest.ticTacToe.ticTacToeManager.getCurrentPlayerMark()); //hackerman
//            Maintest.ticTacToe.ticTacToeManager.checkMove(0, 0);
//            Maintest.ticTacToe.ticTacToeManager.placeMove(0, 0);
//            Maintest.ticTacToe.ticTacToeManager.printBoard(Maintest.ticTacToe.ticTacToeManager.getBoard()); //print
//            Maintest.ticTacToe.ticTacToeManager.changePlayer();
////            System.out.println("check");
//
//            /*if(GameManager.checkMove(0, 1)){
//                 TicTacToeManager.makeMove(0,1);
//                  butt00.setText(currentPlayer.getPlayerMark());
//             }*/
//        } else if(event.getSource() == butt01) {
//
//        } else if(event.getSource() == butt02) {
//
//        } else if(event.getSource() == butt10) {
//
//        } else if(event.getSource() == butt11) {
//
//        } else if(event.getSource() == butt12) {
//
//        } else if(event.getSource() == butt20) {
//
//        } else if(event.getSource() == butt21) {
//
//        } else if(event.getSource() == butt22) {
//
//        }
//    }


    public void howToTTT(ActionEvent actionEvent) {
    }

    public void howToOThello(ActionEvent actionEvent) {
    }

    public void conceid(MouseEvent mouseEvent) {
        gameAnchor.getChildren().clear();
        Othello othello = new Othello(PlayerType.ONE);
        gameAnchor.getChildren().add(View.setOthello(othello));
        Thread testgame = new Thread(othello);
        testgame.start();
    }

    public void getHint(MouseEvent mouseEvent) {
        gameAnchor.getChildren().clear();
        TicTacToe ticTacToe =  new TicTacToe(PlayerType.ONE);
        gameAnchor.getChildren().add(View.setTTT(ticTacToe));
        Thread testgame =  new Thread(ticTacToe);
        testgame.start();
    }

}