package player.inputBehaviour;

import games.Game;
import games.Move;
import games.board.Board;
import gui.View;

public class InputGUI implements Input {

    @Override
    public Move requestMove(Game game) {
        // TODO GUI
        System.out.println(game.getBoard());
        while(!View.moveSet) { }
        Move move = new Move(View.xwaarde, View.ywaarde);
        return move;
    }
}
