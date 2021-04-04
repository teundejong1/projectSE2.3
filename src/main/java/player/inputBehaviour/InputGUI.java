package player.inputBehaviour;

import games.Game;
import games.Move;
import games.board.Board;
import gui.View;

public class InputGUI implements Input {
    private View view;

    @Override
    public Move requestMove(Game game) {
        // TODO GUI
        System.out.println(game.getBoard());

        while(!View.moveSet) {

        }
        Move move = new Move(view.getXwaarde(), view.getYwaarde());
        System.out.println(view.getXwaarde());
        System.out.println(view.getYwaarde());
        View.moveSet = false;
        return move;
    }
}
