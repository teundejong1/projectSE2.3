package gui;

import javafx.event.ActionEvent;

public class LobbyController {
    Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void refresh(ActionEvent actionEvent) {
        controller.lobbyRefresh();
    }
}
