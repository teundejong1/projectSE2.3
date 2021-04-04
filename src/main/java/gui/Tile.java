package gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Tile extends Rectangle {

    private int xwaarde;
    private int ywaarde;

//    private Piece piece;
//
//    public boolean hasPiece() {
//        return piece != null;
//    }
//
//    public Piece getPiece() {
//        return piece;
//    }
//
//    public void setPiece(Piece piece) {
//        this.piece = piece;
//    }

    public Tile(int x, int y) {
        xwaarde = x;
        ywaarde = y;

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
            View.moveSet = true;
            View.xwaarde = xwaarde;
            View.ywaarde = ywaarde;
            //dit is niet placeholder, dit onderdeel staat er zodat de interactie maar 1 keer mogelijk is
            setDisable(true);
        });

    }
}
