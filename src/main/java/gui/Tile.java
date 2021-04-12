package gui;

import games.Game;
import games.Move;
import games.Othello;
import games.TicTacToe;
import games.board.Mark;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import player.inputBehaviour.InputGUI;


public class Tile extends Rectangle {

    private int xwaarde;
    private int ywaarde;
    private Mark mark;
    private Game game;
    private Node spelStuk;
    private Color defaultColor;
    private Color normalColor;
    private Color highlightColor;
    private Color darkenColor;


    public void colorDefault() {
        normalColor = defaultColor;
        setFill(defaultColor);
    }
    public void highlight() {
        normalColor = highlightColor;
        setFill(highlightColor);
    }

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
        defaultColor = Color.BEIGE;
        normalColor = defaultColor;
        darkenColor = Color.WHEAT;
        highlightColor = Color.WHITESMOKE;


        setWidth(View.TILE_SIZE);
        setHeight(View.TILE_SIZE);

        relocate(x * View.TILE_SIZE, y * View.TILE_SIZE);

        colorDefault();
        setStrokeType(StrokeType.INSIDE);
        setStroke(Color.BLACK);

        if(game.getClass() == TicTacToe.class) {
            setOnMouseEntered(e -> {
                setFill(darkenColor);
            });
            setOnMouseExited(e -> {
                setFill(normalColor);
            });
            setOnMouseReleased(e -> {
                //placeholder actie
                View.xwaarde = ywaarde;
                View.ywaarde = xwaarde;

                View.moveSet = true;
            });
        } else if(game.getClass() == Othello.class) {
            setOnMouseEntered(e -> {
                setFill(darkenColor);
            });
            setOnMouseExited(e -> {
                setFill(normalColor);
            });

            setOnMouseReleased(this::handle);
        }
    }

    private void handle(MouseEvent e) {
        Othello othello = (Othello) this.game;
        for (Move move : othello.getPossibleMoves()) {
            if (move.getY() == xwaarde && move.getX() == ywaarde) {
                View.xwaarde = ywaarde;
                View.ywaarde = xwaarde;

                View.moveSet = true;
            }
        }
    }
}
