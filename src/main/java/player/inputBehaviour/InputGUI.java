package player.inputBehaviour;

import games.Game;
import games.Move;
import games.board.Board;
import games.board.Mark;
import gui.Tile;
import gui.View;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class InputGUI implements Input {
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public Move requestMove(Game game) {
        // TODO GUI
        System.out.println(game.getBoard());

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
                        Platform.runLater(() -> {
                            View.elements.getChildren().add(cross);
                        });

                    }
                    if (mark == Mark.TWO) {
                        ImageView circle = new ImageView("/images/Circle.png");
                        circle.setFitWidth(View.TILE_SIZE);
                        circle.setFitHeight(View.TILE_SIZE);
                        circle.setX(tile.getXwaarde() * View.TILE_SIZE);
                        circle.setY(tile.getYwaarde() * View.TILE_SIZE);
                        Platform.runLater(() -> {
                            View.elements.getChildren().add(circle);
                        });
                    }
                    tile.setMark(mark);
                }
            }
        }

        while (!View.moveSet) {
            try {
                Thread.sleep(1);
            } catch(InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        Move move = new Move(View.xwaarde, View.ywaarde);
        View.moveSet = false;

        return move;
    }
}
