package gui;

import javafx.scene.input.MouseEvent;

public class MenuController {
    Controller root;

    public void setRoot (Controller controller){
        root = controller;
    }

    public void tttStart(MouseEvent mouseEvent) {
        root.setTTT(mouseEvent);
    }

    public void startOthello(MouseEvent mouseEvent) {
        root.setOthello(mouseEvent);
    }
}
