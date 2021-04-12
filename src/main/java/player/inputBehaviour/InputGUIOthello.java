package player.inputBehaviour;

import games.Game;
import games.GameStatus;
import games.Move;
import games.Othello;
import games.board.Mark;
import gui.Tile;
import gui.View;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;
import java.util.List;

public class InputGUIOthello extends InputGUI {

    @Override
    public Move requestMove(Game game) {
        ArrayList<Node> toAdd =  new ArrayList<>();
        ArrayList<Node> toRemove =  new ArrayList<>();
        List<Move> moves = game.getPossibleMoves();

        //Dit is om te skippen
//        if(game.getPossibleMoves().isEmpty() && game.getStatus() == GameStatus.PLAYING) {
//            View.skipButton.setOnMouseClicked(e -> {
//                Othello othello = (Othello) game;
//                othello.changeTurn();
//            });
//            View.skipButton.setVisible(true);
//        }

        for(Node node :View.elements.getChildren()) {
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
                View.elements.getChildren().add(node);
            });
        }
        for (Node node:toRemove) {
            Platform.runLater(() -> {
                View.elements.getChildren().remove(node);
            });
        }
        System.out.println(game.getBoard());

        while (!View.moveSet && game.isRunning()) {
            try {
                Thread.sleep(1);
            } catch(InterruptedException exception) {

            }
        }
        Move move = new Move(View.xwaarde, View.ywaarde);
        View.moveSet = false;

        return move;
    }
}