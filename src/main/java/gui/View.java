package gui;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class View {

    public final static int TTT_SIZE = 3;
    public final static int OTHELLO_SIZE = 8;
    public final static int TILE_SIZE = 100;

    //Dit is de static container
    public static Group elements = new Group();

    public static Parent setTTT()  {
        Pane root = new Pane();
        root.setPrefSize(TTT_SIZE * TILE_SIZE, TTT_SIZE * TILE_SIZE);

        /*
         We moeten op één of andere manier bij de tiles komen
         opties:
         - Een static container maken en legen aan het begin van deze methode om hierna weer op te vullen met de elementen
         - Een manier te vinden om de view te instantieren (op dit moment is alles in de View-klasse static, foei!)
         */
        elements.getChildren().clear();
        root.getChildren().addAll(elements);
        for(int x = 0; x < TTT_SIZE; x++) {
            for (int y = 0; y < TTT_SIZE; y++) {
                Tile tile =  new Tile(x, y);
                elements.getChildren().add(tile);

                // de lijnen aanmaken
                Line line = new Line();
                line.setStartX(x*TILE_SIZE);
                line.setStartY(y*TILE_SIZE);
                line.setEndX(x*TILE_SIZE);
                line.setEndY((y+1)*TILE_SIZE);
                elements.getChildren().add(line);

                line = new Line();
                line.setStartX(x*TILE_SIZE);
                line.setStartY(y*TILE_SIZE);
                line.setEndX((x+1)*TILE_SIZE);
                line.setEndY(y*TILE_SIZE);
                elements.getChildren().add(line);

                if(x == TTT_SIZE - 1) {
                    line = new Line();
                    line.setStartX((x+1)*TILE_SIZE);
                    line.setStartY(y*TILE_SIZE);
                    line.setEndX((x+1)*TILE_SIZE);
                    line.setEndY((y+1)*TILE_SIZE);
                    elements.getChildren().add(line);
                }
                if(y == TTT_SIZE - 1) {
                    line = new Line();
                    line.setStartX(x*TILE_SIZE);
                    line.setStartY((y+1)*TILE_SIZE);
                    line.setEndX((x+1)*TILE_SIZE);
                    line.setEndY((y+1)*TILE_SIZE);
                    elements.getChildren().add(line);
                }
            }
        }
        return root;
    }

    public static Parent setOthello()  {
        Pane root = new Pane();
        root.setPrefSize(OTHELLO_SIZE * OTHELLO_SIZE, OTHELLO_SIZE * OTHELLO_SIZE);
        elements.getChildren().clear();
        root.getChildren().addAll(elements);
        for(int x = 0; x < OTHELLO_SIZE; x++) {
            for (int y = 0; y < OTHELLO_SIZE; y++) {
                Tile tile =  new Tile(x, y);
                elements.getChildren().add(tile);

                // de lijnen aanmaken
                Line line = new Line();
                line.setStartX(x*TILE_SIZE);
                line.setStartY(y*TILE_SIZE);
                line.setEndX(x*TILE_SIZE);
                line.setEndY((y+1)*TILE_SIZE);
                elements.getChildren().add(line);

                line = new Line();
                line.setStartX(x*TILE_SIZE);
                line.setStartY(y*TILE_SIZE);
                line.setEndX((x+1)*TILE_SIZE);
                line.setEndY(y*TILE_SIZE);
                elements.getChildren().add(line);

                if(x == OTHELLO_SIZE - 1) {
                    line = new Line();
                    line.setStartX((x+1)*TILE_SIZE);
                    line.setStartY(y*TILE_SIZE);
                    line.setEndX((x+1)*TILE_SIZE);
                    line.setEndY((y+1)*TILE_SIZE);
                    elements.getChildren().add(line);
                }
                if(y == OTHELLO_SIZE - 1) {
                    line = new Line();
                    line.setStartX(x*TILE_SIZE);
                    line.setStartY((y+1)*TILE_SIZE);
                    line.setEndX((x+1)*TILE_SIZE);
                    line.setEndY((y+1)*TILE_SIZE);
                    elements.getChildren().add(line);
                }
            }
        }
        return root;
    }

}
