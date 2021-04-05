package gui;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.List;

public class View {

    public final static int TTT_SIZE = 3;
    public final static int OTHELLO_SIZE = 8;
    public final static int TILE_SIZE = 100;
    public static boolean moveSet = false;
    public static int xwaarde;
    public static int ywaarde;

    //Dit is de static container HIER MOGEN ALLEEN TILES IN
    public static Group elements = new Group();

    public View() {

    }



    public static Parent setTTT()  {
        Pane root = new Pane();
        root.setPrefSize(TTT_SIZE * TILE_SIZE, TTT_SIZE * TILE_SIZE);

        /**
         * We moeten op één of andere manier bij de tiles komen
         * opties:
         * - Een static container maken en legen aan het begin van deze methode om hierna weer op te vullen met de elementen
         * - Een manier te vinden om de view te instantieren (op dit moment is alles in de View-klasse static, foei!)
         *
         * creating the board can also go in 1 method. Just give the size.
         */
        elements.getChildren().clear();
        root.getChildren().addAll(elements);
        for(int y = 0; y < TTT_SIZE; y++) {
            for (int x = 0; x < TTT_SIZE; x++) {
                Tile tile =  new Tile(x, y);
                // Tile maken
                elements.getChildren().add(tile);
            }
        }


        return root;
    }

    public static Parent setOthello()  {
        Pane root = new Pane();
        root.setPrefSize(OTHELLO_SIZE * OTHELLO_SIZE, OTHELLO_SIZE * OTHELLO_SIZE);
        elements.getChildren().clear();
        root.getChildren().addAll(elements);
        for(int y = 0; y < OTHELLO_SIZE; y++) {
            for (int x = 0; x < OTHELLO_SIZE; x++) {
                // Tile Maken
                Tile tile =  new Tile(x, y);
                elements.getChildren().add(tile);
            }
        }
        return root;
    }

//    public int getXwaarde() {
//        return xwaarde;
//    }
//
//    public int getYwaarde() {
//        return ywaarde;
//    }
//
//    public boolean isMoveSet() {
//        return moveSet;
//    }
//
//    public void setMoveSet(boolean moveSet) {
//        System.out.println(moveSet);
//        this.moveSet = moveSet;
//    }
//
//    public void setXwaarde(int xwaarde) {
//        System.out.println(xwaarde);
//        this.xwaarde = xwaarde;
//    }
//
//    public void setYwaarde(int ywaarde) {
//        this.ywaarde = ywaarde;
//    }
}
