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

public class InputGUITTT extends InputGUI {

    @Override
    public Move requestMove(Game game) {
        ArrayList<Node> toAdd =  new ArrayList<>();

        for(Node node :View.elements.getChildren()) {
            if(node.getClass() == Tile.class) {
                Tile tile = (Tile)node;
                Mark mark = game.getBoard().getCell(tile.getYwaarde(), tile.getXwaarde());
                if (mark != tile.getMark()) {
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
        System.out.println(game.getBoard());

        while (!View.moveSet) {
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