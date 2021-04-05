package player.inputBehaviour;

import games.Game;
import games.Move;
import games.board.Board;
import games.board.Mark;
import gui.View;

public class InputGUI implements Input {
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public Move requestMove(Game game) {
        System.out.println(game.getBoard());


        while (!view.isMoveSet()) {}
        System.out.println(view.getXwaarde());
        System.out.println(view.getYwaarde());


        Move move = new Move(view.getXwaarde(), view.getYwaarde());
        //System.out.println(View.xwaarde);
//        System.out.println(View.xwaarde);
//        System.out.println(View.ywaarde);
        view.setMoveSet(false);
        return move;
    }
}
