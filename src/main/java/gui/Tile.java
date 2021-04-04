package gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Tile extends Rectangle {

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
        setWidth(View.TILE_SIZE);
        setHeight(View.TILE_SIZE);

        relocate(x * View.TILE_SIZE, y * View.TILE_SIZE);

        setFill(Color.BEIGE);

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
            Ellipse ellipse =  new Ellipse();
            ellipse.setRadiusX(30);
            ellipse.setRadiusY(30);
            ellipse.setCenterX((x+0.5)*View.TILE_SIZE);
            ellipse.setCenterY((y+0.5)*View.TILE_SIZE);
            View.elements.getChildren().add(ellipse);
            //dit is niet placeholder, dit onderdeel staat er zodat de interactie maar 1 keer mogelijk is
            setDisable(true);
        });

    }
}
