package gui;

import games.board.Mark;
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

    public Tile(int x, int y) {
        xwaarde = x;
        ywaarde = y;
        mark = Mark.EMPTY;

        setWidth(View.TILE_SIZE);
        setHeight(View.TILE_SIZE);

        relocate(x * View.TILE_SIZE, y * View.TILE_SIZE);

        setFill(Color.BEIGE);
        setStrokeType(StrokeType.INSIDE);
        setStroke(Color.BLACK);

        /*
        De muisklik event
        op dit moment staat er een placeholderactie, maar het moet natuurlijk inspelen op de gamelogica
        er moet een methode zijn die we kunnen aanroepen die checkt wiens beurt het is en welk teken er aangemaakt moet worden
        BELANGRIJK (relatief): we moeten een logische manier bedenken om het teken te bepalen dat hier ingevuld moet worden
            we kunnen natuurlijk een case-switch maken voor elk mogelijk teken, dus othello en TTT (en in theoretische uitbreidingen nog meer)
            we kunnen ook meerdere Tile sub-klassen aanmaken
            andere opties?
         */
        setOnMouseReleased(e -> {
            //placeholder actie
            View.xwaarde = ywaarde;
            View.ywaarde = xwaarde;

            View.moveSet = true;


            //dit is niet placeholder, dit onderdeel staat er zodat de interactie maar 1 keer mogelijk is
            setDisable(true);
        });
    }
}
