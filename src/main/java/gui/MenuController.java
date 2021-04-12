package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import networking.NetworkManager;
import networking.states.IllegalStateException;
import player.PlayEnum;

import java.io.IOException;

public class MenuController {
    public Button localTttPvp;
    public Button localTttPve;
    public Button localOthelloPvp;
    public Button localOthelloPve;
    public Button onlineTttManual;
    public Button onlineTttAi;
    public Button onlineOthelloManual;
    public Button onlineOthelloAi;
    Controller root;
    NetworkManager networkManager;

    public void setRoot (Controller controller){
        root = controller;
    }

    public void tttStart(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == localTttPve) {
            root.setTTT(PlayEnum.PVE);
        } else if(mouseEvent.getSource() == localTttPvp) {
            root.setTTT(PlayEnum.PVP);
        } else if(mouseEvent.getSource() == onlineTttManual) {
            try {
                networkManager.getPlayerList();
            } catch (IllegalStateException e) {
                Group group = new Group();
                Label label = new Label("Dit is nu niet toegestaan. Je kan alleen uitdagen als je ingelogd bent en geen spel speelt.");
                group.getChildren().add(label);

                Stage loginScherm = new Stage();
                loginScherm.setScene(new Scene(group));
                loginScherm.setTitle("Fout");
                loginScherm.show();
            } catch(NullPointerException ex) {
                Group group = new Group();
                Label label = new Label("Dit is nu niet toegestaan. Je bent nu niet ingelogd.");
                group.getChildren().add(label);

                Stage loginScherm = new Stage();
                loginScherm.setScene(new Scene(group));
                loginScherm.setTitle("Fout");
                loginScherm.show();
            }
        } else {

        }
    }

    public void startOthello(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == localOthelloPve) {
            root.setOthello(PlayEnum.PVE);
        } else if(mouseEvent.getSource() == localOthelloPvp){
            root.setOthello(PlayEnum.PVP);
        } else if(mouseEvent.getSource() == onlineOthelloManual) {
            try {
                networkManager.getPlayerList();
            } catch (IllegalStateException e) {
                Group group = new Group();
                Label label = new Label("Dit is nu niet toegestaan. Je kan alleen uitdagen als je ingelogd bent en geen spel speelt.");
                group.getChildren().add(label);

                Stage loginScherm = new Stage();
                loginScherm.setScene(new Scene(group));
                loginScherm.setTitle("Fout");
                loginScherm.show();
            } catch(NullPointerException ex) {
                Group group = new Group();
                Label label = new Label("Dit is nu niet toegestaan. Je bent nu niet ingelogd.");
                group.getChildren().add(label);

                Stage loginScherm = new Stage();
                loginScherm.setScene(new Scene(group));
                loginScherm.setTitle("Fout");
                loginScherm.show();
            }
        } else {

        }
    }

    public void login(ActionEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginScherm.fxml"));
        Parent root = loader.load();
        LoginController loginController =  loader.getController();
        loginController.setMenuController(this);


//        root.setText("Error");

        Stage loginScherm = new Stage();
        loginScherm.setScene(new Scene(root));
        loginScherm.setTitle("Login");
        loginScherm.show();
    }

    public void rootSetNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
        root.setNetworkManager(networkManager);
    }

    public void rootInitLobby() throws IOException {
        root.initLobby();
    }

//    public void newGame(MouseEvent mouseEvent){
//
//        if(mouseEvent.getSource() == localOthelloPve) root.setOthello(PlayEnum.PVE);
//        else if(mouseEvent.getSource() == localOthelloPvp) root.setOthello(PlayEnum.PVP);
//        else if(mouseEvent.getSource() == localTttPve) root.setTTT(PlayEnum.PVE);
//        else if(mouseEvent.getSource() == localTttPvp) root.setTTT(PlayEnum.PVP);
//        else if(mouseEvent.getSource() == onlineOthelloManual) {
//            try {
//                networkManager.getPlayerList();
//            } catch (IllegalStateException e) {
//                Group group = new Group();
//                Label label = new Label("Dit is nu niet toegestaan. Je kan alleen uitdagen als je ingelogd bent en geen spel speelt.");
//                group.getChildren().add(label);
//
//                Stage loginScherm = new Stage();
//                loginScherm.setScene(new Scene(group));
//                loginScherm.setTitle("Fout");
//                loginScherm.show();
//            } catch(NullPointerException ex) {
//                Group group = new Group();
//                Label label = new Label("Dit is nu niet toegestaan. Je bent nu niet ingelogd.");
//                group.getChildren().add(label);
//
//                Stage loginScherm = new Stage();
//                loginScherm.setScene(new Scene(group));
//                loginScherm.setTitle("Fout");
//                loginScherm.show();
//            }
//        } else {
//
//        }
//    }
//
//    public void tryOnline(MouseEvent mouseEvent, PlayEnum player){
//
//        try {
//            networkManager.getPlayerList();
//            // create a player for when a game starts, based on the player en the game
//        } catch (IllegalStateException e) {
//            Group group = new Group();
//            Label label = new Label("Dit is nu niet toegestaan. Je kan alleen uitdagen als je ingelogd bent en geen spel speelt.");
//            group.getChildren().add(label);
//
//            Stage loginScherm = new Stage();
//            loginScherm.setScene(new Scene(group));
//            loginScherm.setTitle("Fout");
//            loginScherm.show();
//
//        } catch(NullPointerException ex) {
//            Group group = new Group();
//            Label label = new Label("Dit is nu niet toegestaan. Je bent nu niet ingelogd.");
//            group.getChildren().add(label);
//
//            Stage loginScherm = new Stage();
//            loginScherm.setScene(new Scene(group));
//            loginScherm.setTitle("Fout");
//            loginScherm.show();
//        }
//
//    }


}
