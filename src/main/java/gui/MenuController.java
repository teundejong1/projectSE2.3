package gui;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import player.PlayEnum;

public class MenuController {
    public Button localTttPvp;
    public Button localTttPve;
    public Button localOthelloPvp;
    public Button localOthelloPve;
    Controller root;

    public void setRoot (Controller controller){
        root = controller;
    }

    public void tttStart(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == localTttPve) {
            root.setTTT(PlayEnum.PVE);
        } else {
            root.setTTT(PlayEnum.PVP);
        }
    }

    public void startOthello(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == localOthelloPve) {
            root.setOthello(PlayEnum.PVE);
        } else {
            root.setOthello(PlayEnum.PVP);
        }
    }
}
