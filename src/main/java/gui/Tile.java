package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public Tile(int x, int y, View view) {
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
            view.setXwaarde(xwaarde);
            view.setYwaarde(ywaarde);

            // Create cross
            ImageView cross = new ImageView("/images/Cross.png");
            cross.setFitWidth(View.TILE_SIZE);
            cross.setFitHeight(View.TILE_SIZE);
            cross.setX(x*View.TILE_SIZE);
            cross.setY(y*View.TILE_SIZE);

            // Create Circle
            ImageView circle = new ImageView("/images/Circle.png");
            circle.setFitWidth(View.TILE_SIZE);
            circle.setFitHeight(View.TILE_SIZE);
            circle.setX(x*View.TILE_SIZE);
            circle.setY(y*View.TILE_SIZE);

            //TODO change that it depends on the marker
            View.elements.getChildren().add(circle);
        });

    }
}
