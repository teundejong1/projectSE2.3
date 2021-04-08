package gui;

import games.Othello;
import games.TicTacToe;
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



    public static Parent setTTT(TicTacToe ticTacToe)  {
        Pane root = new Pane();
        root.setPrefSize(TTT_SIZE * TILE_SIZE, TTT_SIZE * TILE_SIZE);
        elements.getChildren().clear();
        root.getChildren().addAll(elements);
        for(int y = 0; y < TTT_SIZE; y++) {
            for (int x = 0; x < TTT_SIZE; x++) {
                Tile tile =  new Tile(x, y, ticTacToe);
                // Tile maken
                elements.getChildren().add(tile);
            }
        }


        return root;
    }

    public static Parent setOthello(Othello othello)  {
        Pane root = new Pane();
        root.setPrefSize(OTHELLO_SIZE * OTHELLO_SIZE, OTHELLO_SIZE * OTHELLO_SIZE);
        elements.getChildren().clear();
        root.getChildren().addAll(elements);
        for(int y = 0; y < OTHELLO_SIZE; y++) {
            for (int x = 0; x < OTHELLO_SIZE; x++) {
                // Tile Maken
                Tile tile =  new Tile(x, y, othello);
                elements.getChildren().add(tile);
            }
        }
        return root;
    }

}
