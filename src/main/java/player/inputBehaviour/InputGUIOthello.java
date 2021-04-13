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