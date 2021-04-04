package player.inputBehaviour;

import games.Move;
import games.board.Board;
import gui.View;

public class InputGUI implements Input {

    @Override
    public Move requestMove(Board board) {
        // TODO GUI
        System.out.println(board);
        while(!View.moveSet) { }
        Move move = new Move(View.xwaarde, View.ywaarde);
        return move;
    }
    
}
