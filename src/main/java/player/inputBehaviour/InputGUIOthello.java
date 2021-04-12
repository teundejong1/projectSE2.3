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

import java.util.ArrayList;
import java.util.List;

public class InputGUIOthello extends InputGUI {

    @Override
    public Move requestMove(Game game) {
        ArrayList<Node> toAdd =  new ArrayList<>();
        ArrayList<Node> toRemove =  new ArrayList<>();
        List<Move> moves = game.getPossibleMoves();

        if(game.getPossibleMoves().isEmpty() && game.getStatus() == GameStatus.PLAYING) {
            View.skipButton.setOnMouseClicked(e -> {
                Othello othello = (Othello) game;
                othello.changeTurn();
            });
            View.skipButton.setVisible(true);
        }

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