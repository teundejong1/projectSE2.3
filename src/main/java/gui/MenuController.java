package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import networking.NetworkManager;
import player.PlayEnum;

import java.io.IOException;

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

    public void login(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginScherm.fxml"));
        Parent root = loader.load();
        LoginController loginController =  loader.getController();
        loginController.setMenuController(this);

        Stage loginScherm = new Stage();
        loginScherm.setScene(new Scene(root));
        loginScherm.setTitle("Login");
        loginScherm.show();
    }

    public void rootSetNetworkManager(NetworkManager networkManager) {
        root.setNetworkManager(networkManager);
    }
}
