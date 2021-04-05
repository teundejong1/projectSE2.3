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


        while (!view.isMoveSet()) {
            System.out.println(view.isMoveSet());
            try {
                Thread.sleep(2000);
            } catch(InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        Move move = new Move(view.getXwaarde(), view.getYwaarde());
        System.out.println(view.getXwaarde());
        System.out.println(view.getYwaarde());
        view.setMoveSet(false);
        return move;
    }
}
