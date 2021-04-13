package gui;

import games.GameEnum;
import games.Move;
import games.Othello;
import games.TicTacToe;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import player.PlayEnum;

import java.util.List;

public class View {

    public final static int TTT_SIZE = 3;
    public final static int OTHELLO_SIZE = 8;
    public final static int TILE_SIZE = 100;
    public static boolean moveSet = false;
    public static int xwaarde;
    public static int ywaarde;

    //Dit is de static container HIER MOGEN ALLEEN TILES IN
    public static Group elements = new Group();
//    public static Button skipButton;

    public static VBox spelerLijst;
    public static Controller controller;
    public static String spelernaam;
    public static boolean remoteMoveSet;
    public static Move remoteMove;

    public View() {

    }

    public static Parent setTTT(TicTacToe ticTacToe)  {
        Pane root = new Pane();
        root.setPrefSize(TTT_SIZE * TILE_SIZE, TTT_SIZE * TILE_SIZE);
        elements.getChildren().clear();
        root.getChildren().addAll(elements);
        for(int y = 0; y < TTT_SIZE; y++) {
            for (int x = 0; x < TTT_SIZE; x++) {
                Tile tile =  new Tile(x, y, ticTacToe);
                // Tile maken
                elements.getChildren().add(tile);
            }
        }
//        skipButton = new Button();
//        root.getChildren().add(skipButton);
//        skipButton.setVisible(false);

        return root;
    }

    public static Parent setOthello(Othello othello)  {
        Pane root = new Pane();
        root.setPrefSize(OTHELLO_SIZE * OTHELLO_SIZE, OTHELLO_SIZE * OTHELLO_SIZE);
        elements.getChildren().clear();
        root.getChildren().addAll(elements);
        for(int y = 0; y < OTHELLO_SIZE; y++) {
            for (int x = 0; x < OTHELLO_SIZE; x++) {
                // Tile Maken
                Tile tile =  new Tile(x, y, othello);
                elements.getChildren().add(tile);
            }
        }
//        skipButton = new Button();
//        root.getChildren().add(skipButton);
//        skipButton.setVisible(false);
        return root;
    }

    public static void refreshLobby(List<String> spelers) {
        Platform.runLater(() -> {
            spelerLijst.getChildren().clear();
        });
        System.out.println("lijst gecleard");
        for (String speler:spelers) {
            System.out.println("ik probeer nu " + speler);
            Label label = new Label(speler);
            label.setOnMouseClicked(e -> {
                controller.challengeConfirm(speler);
            });
            Platform.runLater(() -> {
                spelerLijst.getChildren().add(new Label(speler));
            });
        }
    }

    public static void startOnlineMatch(GameEnum gameType) {
        if(gameType == GameEnum.OTHELLO) {
            controller.setOthello(PlayEnum.ONLINEAI);
        } else {
            controller.setTTT(PlayEnum.ONLINEAI);
        }
    }

    public static void illegalStateException() {
        Group group = new Group();
        Label label = new Label("Dit is nu niet toegestaan.");
        group.getChildren().add(label);

        Stage loginScherm = new Stage();
        loginScherm.setScene(new Scene(group));
        loginScherm.setTitle("Fout");
        loginScherm.show();
    }
}
