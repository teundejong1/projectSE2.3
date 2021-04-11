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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import networking.NetworkManager;
import player.PlayEnum;
import player.Player;
import player.PlayerType;

import java.io.IOException;
import java.nio.charset.Charset;


public class Controller {
    NetworkManager networkManager;

    @FXML
    public AnchorPane gameAnchor;
    public Button forfeit;
    public BorderPane mainWindow;


    FXMLLoader menuLoader;
    Parent menu;
    MenuController menuController;

    public void setNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    public void setMenu() throws IOException {
        forfeit.setVisible(false);
        menuLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        menu = menuLoader.load();
        menuController = menuLoader.getController();
        menuController.setRoot(this);

        gameAnchor.getChildren().clear();
        gameAnchor.getChildren().add(menu);
    }

    public void opgeefActie(ActionEvent actionEvent) {
        System.out.println("...noob");
    }

    public void TTTInfo(ActionEvent actionEvent) {
        //TODO
    }

    public void OthelloInfo(ActionEvent actionEvent) {
        //TODO
    }

    public void howToTTT(ActionEvent actionEvent) {
    }

    public void howToOThello(ActionEvent actionEvent) {
    }

    public void setOthello(PlayEnum playType) {
        forfeit.setVisible(true);
        gameAnchor.getChildren().clear();
        Othello othello = new Othello(PlayerType.ONE, playType);
        gameAnchor.getChildren().add(View.setOthello(othello));
        Thread testgame = new Thread(othello);
        testgame.start();
    }

    public void setTTT(PlayEnum playType) {
        forfeit.setVisible(true);
        gameAnchor.getChildren().clear();
        TicTacToe ticTacToe =  new TicTacToe(PlayerType.ONE, playType);
        gameAnchor.getChildren().add(View.setTTT(ticTacToe));
        Thread testgame =  new Thread(ticTacToe);
        testgame.start();
    }

    public void forfeit(ActionEvent actionEvent) throws IOException {
        /*
        TODO
         thread stoppen
         rekening houden met mogelijke andere spelers, verbinding verbreken
         mischien een popup maken die om bevestiging vraagt?
         */
        setMenu();
    }

    public void initLobby() throws IOException {
        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/fxml/Lobby.fxml"));
        Parent lobby = fxmlLoader.load();

        LobbyController lobbyController = fxmlLoader.getController();

        mainWindow.setLeft(lobby);
    }

    public void lobbyRefresh() {

    }
}