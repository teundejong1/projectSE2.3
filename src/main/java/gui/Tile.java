package gui;

import games.Game;
import games.Move;
import games.Othello;
import games.TicTacToe;
import games.board.Mark;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import player.inputBehaviour.InputGUI;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Tile extends Rectangle {

    private int xwaarde;
    private int ywaarde;
    private Mark mark;
    private Game game;
    private Node spelStuk;


    public Node getSpelStuk() {
        return spelStuk;
    }

    public void setSpelStuk(Node spelStuk) {
        this.spelStuk = spelStuk;
    }

    public int getXwaarde() {
        return xwaarde;
    }

    public int getYwaarde() {
        return ywaarde;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Tile(int x, int y, Game game) {
        xwaarde = x;
        ywaarde = y;
        mark = Mark.EMPTY;
        this.game = game;

        setWidth(View.TILE_SIZE);
        setHeight(View.TILE_SIZE);

        relocate(x * View.TILE_SIZE, y * View.TILE_SIZE);

        setFill(Color.BEIGE);
        setStrokeType(StrokeType.INSIDE);
        setStroke(Color.BLACK);

        if(game.getClass() == TicTacToe.class) {
            setOnMouseReleased(e -> {
                //placeholder actie
                View.xwaarde = ywaarde;
                View.ywaarde = xwaarde;

                View.moveSet = true;


                //dit onderdeel staat er zodat de interactie maar 1 keer mogelijk is
                setDisable(true);
            });
        } else if(game.getClass() == Othello.class) {
            setOnMouseEntered(e -> {
                setFill(Color.WHEAT);
            });
            setOnMouseExited(e -> {
                setFill(Color.BEIGE);
            });

            setOnMouseReleased(e -> {
                Othello othello = (Othello) this.game;
                boolean zetMogelijk = false;
                for (Move move : othello.getPossibleMoves()) {
                    if (move.getY() == xwaarde && move.getX() == ywaarde) {
                        View.xwaarde = ywaarde;
                        View.ywaarde = xwaarde;

                        View.moveSet = true;
                        zetMogelijk = true;


                        //dit onderdeel staat er zodat de interactie maar 1 keer mogelijk is
                        setDisable(true);
                    }
                }
                if(!zetMogelijk) {
                    /*TODO
                    hier moet duidelijk gemaakt worden dat de zet niet mogelijk is
                     */
                }
            });
        }
    }
}
