package player.inputBehaviour;

import games.Game;
import games.Move;
import games.board.Board;
import gui.View;

public class InputGUI implements Input {
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public Move requestMove(Game game) {
        // TODO GUI
        System.out.println(game.getBoard());


        while (!View.moveSet) {
            try {
                Thread.sleep(500);
            } catch(InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        Move move = new Move(View.xwaarde, View.ywaarde);
        System.out.println(View.xwaarde);
        System.out.println(View.ywaarde);
        View.moveSet = false;
        return move;
    }
}
