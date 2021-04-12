package gui;

import games.Game;
import games.GameStatus;
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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import networking.NetworkManager;
import networking.commands.GetPlayerlistCommand;
import networking.states.IllegalStateException;
import player.PlayEnum;
import player.Player;
import player.PlayerType;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Controller {
    NetworkManager networkManager;
    Game game;
    Thread gameThread;

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
        game = othello;
        gameAnchor.getChildren().add(View.setOthello(othello));
        gameThread = new Thread(othello);
        gameThread.start();
    }

    public void setTTT(PlayEnum playType) {
        forfeit.setVisible(true);
        gameAnchor.getChildren().clear();
        TicTacToe ticTacToe =  new TicTacToe(PlayerType.ONE, playType);
        game = ticTacToe;
        gameAnchor.getChildren().add(View.setTTT(ticTacToe));
        gameThread =  new Thread(ticTacToe);
        gameThread.start();
    }

    public void forfeit(ActionEvent actionEvent) throws IOException {
        /*
        TODO
         rekening houden met mogelijke andere spelers, verbinding verbreken
         mischien een popup maken die om bevestiging vraagt?
         */
        game.setRunning(false);
        gameThread.interrupt();
        setMenu();
    }

    public void initLoggedInStatus() throws IOException{
        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/fxml/Inlogstatus.fxml"));
        Parent status = fxmlLoader.load();

        //TODO Networkmanager moet de challenges kunnen updaten, hiervoor moet de networkmanager bij de inlogstatuscontroller kunnen komen
        InlogstatusController inlogstatusController = fxmlLoader.getController();
        inlogstatusController.setController(this);

        mainWindow.setRight(status);
    }

    public void initLobby() throws IOException {
        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/fxml/Lobby.fxml"));
        Parent lobby = fxmlLoader.load();

        LobbyController lobbyController = fxmlLoader.getController();
        lobbyController.setController(this);

        mainWindow.setLeft(lobby);

        VBox vbox = (VBox) mainWindow.getLeft();
        VBox spelerLijst = new VBox();
        vbox.getChildren().add(spelerLijst);
    }

    public void lobbyRefresh() {
        try {
            networkManager.getPlayerList();
        } catch (IllegalStateException e) {
            Group group = new Group();
            Label label = new Label("Dit is nu niet toegestaan.");
            group.getChildren().add(label);

            Stage loginScherm = new Stage();
            loginScherm.setScene(new Scene(group));
            loginScherm.setTitle("Fout");
            loginScherm.show();
        }
        //TODO hier moet de playerlist nog gekregen worden
        List<String> players =  new ArrayList();
        players.add("bob");
        players.add("greta");
        players.add("henk");
        players.add("ellie");

        VBox lobby = (VBox) mainWindow.getLeft();

        VBox spelerLijst;

        for(Node node: lobby.getChildren()) {
            if (node.getClass() == VBox.class) {
                spelerLijst = (VBox) node;
                spelerLijst.getChildren().clear();
                for (String speler:players) {
                    spelerLijst.getChildren().add(new Label(speler));
                }
            }
        }
    }
}