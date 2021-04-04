package player.inputBehaviour;

import games.Game;
import games.Move;
import games.board.Board;
import games.board.Mark;
import gui.View;

public class InputGUI implements Input {

    @Override
    public Move requestMove(Game game) {
        System.out.println(game.getBoard());


        System.out.println(View.xwaarde);
        System.out.println(View.ywaarde);


        Move move = new Move(View.xwaarde, View.ywaarde);
        //System.out.println(View.xwaarde);
//        System.out.println(View.xwaarde);
//        System.out.println(View.ywaarde);
        View.moveSet = false;
        return move;
    }
}
