package gui;

import games.GameEnum;
import games.Move;
import games.Othello;
import games.TicTacToe;
import games.board.Mark;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import networking.NetworkManager;
import player.PlayEnum;
import games.Game;
import networking.states.IllegalStateException;

import java.util.ArrayList;
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
    public static boolean ourTurn;

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
            Label label = new Label(speler);
            label.setOnMouseClicked(e ->{
                controller.challengeConfirmation(speler);
            });
            Platform.runLater(() -> {
                spelerLijst.getChildren().add(label);
            });
        }
    }

//    public static void setOurTurn(boolean bool) {
//        ourTurn = bool;
//    }

    public static void startOnlineMatch(GameEnum gameType) {
        System.out.println("starts online game");
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

        Platform.runLater(() ->{Stage loginScherm = new Stage();
            loginScherm.setScene(new Scene(group));
            loginScherm.setTitle("Fout");
            loginScherm.show();});

    }

    public static void tttRefresh(Game game) {
        ArrayList<Node> toAdd =  new ArrayList<>();

        for(Node node :elements.getChildren()) {
            if(node.getClass() == Tile.class) {
                Tile tile = (Tile)node;
                Mark mark = game.getBoard().getCell(tile.getYwaarde(), tile.getXwaarde());
                if (mark != tile.getMark()) {
                    if(!tile.isDisabled()) {
                        tile.setDisable(true);
                    }
                    if (mark == Mark.ONE) {
                        ImageView cross = new ImageView("/images/Cross.png");
                        cross.setFitWidth(View.TILE_SIZE);
                        cross.setFitHeight(View.TILE_SIZE);
                        cross.setX(tile.getXwaarde() * View.TILE_SIZE);
                        cross.setY(tile.getYwaarde() * View.TILE_SIZE);
                        toAdd.add(cross);
                        tile.setSpelStuk(cross);
                    } else if (mark == Mark.TWO) {
                        ImageView circle = new ImageView("/images/Circle.png");
                        circle.setFitWidth(View.TILE_SIZE);
                        circle.setFitHeight(View.TILE_SIZE);
                        circle.setX(tile.getXwaarde() * View.TILE_SIZE);
                        circle.setY(tile.getYwaarde() * View.TILE_SIZE);
                        toAdd.add(circle);
                        tile.setSpelStuk(circle);
                    }

                    tile.setMark(mark);
                }
            }
        }
        for (Node node:toAdd) {
            Platform.runLater(() -> {
                elements.getChildren().add(node);
            });
        }
        System.out.println(game.getBoard());
    }

    public static void othelloRefresh(Game game) {
        ArrayList<Node> toAdd =  new ArrayList<>();
        ArrayList<Node> toRemove =  new ArrayList<>();
        List<Move> moves = game.getPossibleMoves();
        System.out.println(moves);

        for(Node node : elements.getChildren()) {
            if(node.getClass() == Tile.class) {
                Tile tile = (Tile)node;
                for (Move move:moves) {
                    if(move.getY() == tile.getXwaarde() && move.getX() == tile.getYwaarde()) {
                        tile.highlight();
                        break;
                    } else {
                        tile.colorDefault();
                    }
                }
                Mark mark = game.getBoard().getCell(tile.getYwaarde(), tile.getXwaarde());
                if (mark != tile.getMark()) {
                    if(!tile.isDisabled()) {
                        tile.setDisable(true);
                    }
                    if(tile.getMark() == Mark.ONE || tile.getMark() == Mark.TWO) {
                        toRemove.add(tile.getSpelStuk());
                    }
                    Circle circle =  new Circle();
                    circle.setCenterX((tile.getXwaarde() + 0.5) * View.TILE_SIZE);
                    circle.setCenterY((tile.getYwaarde() + 0.5) * View.TILE_SIZE);
                    circle.setRadius(0.5*View.TILE_SIZE);
                    circle.setStrokeType(StrokeType.INSIDE);
                    circle.setStroke(Color.BLACK);
                    if (mark == Mark.ONE) {
                        circle.setFill(Color.WHITE);
                    } else if (mark == Mark.TWO) {
                        circle.setFill(Color.BLACK);
                    }
                    toAdd.add(circle);
                    tile.setSpelStuk(circle);

                    tile.setMark(mark);
                }
            }
        }
        for (Node node:toAdd) {
            Platform.runLater(() -> {
                elements.getChildren().add(node);
            });
        }
        for (Node node:toRemove) {
            Platform.runLater(() -> {
                elements.getChildren().remove(node);
            });
        }
        System.out.println(game.getBoard());
    }

    public static void challengeReceived(String uitdager, int challengeNummer) {
        NetworkManager networkManager = NetworkManager.getInstance("", 0);

        Group group = new Group();
        Label label = new Label("Je hebt een challenge van " + uitdager);
        group.getChildren().add(label);
        Button button =  new Button();
        button.setText("accepteer");
        button.setOnAction((challengeNumber) -> {
            try {
                networkManager.acceptChallenge(challengeNummer);
            } catch (IllegalStateException e) {
                illegalStateException();
            }
        });
        group.getChildren().add(button);

        Platform.runLater(() ->{Stage loginScherm = new Stage();
            loginScherm.setScene(new Scene(group));
            loginScherm.setTitle("Je hebt een challenge, kut");
            loginScherm.show();});
    }
}
