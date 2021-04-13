package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import networking.NetworkManager;
import networking.commands.LoginCommand;
import networking.connection.ConnectionFailedException;

import java.io.IOException;

public class LoginController {

    public TextField naamVeld;
    public TextField ipVeld;
    public TextField poortVeld;
    public Button loginKnop;
    private MenuController menuController;

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void login() throws IOException {
        try {
            if (naamVeld.getCharacters().length() > 0 && ipVeld.getCharacters().length() > 0 && poortVeld.getCharacters().length() > 0) {
                int portnumber = Integer.parseInt(poortVeld.getCharacters().toString());
                NetworkManager manager = NetworkManager.getInstance(ipVeld.getCharacters().toString(), portnumber);
                String spelerNaam = naamVeld.getCharacters().toString();
                manager.login(spelerNaam);
                menuController.setNetworkManager(manager);
                Controller controller = menuController.getRoot();

                controller.initLobby();
                controller.setNetworkManager(manager);
                controller.setSpelerNaam(spelerNaam);

                Stage stage = (Stage) loginKnop.getScene().getWindow();
                stage.close();
            }
        } catch(ConnectionFailedException e) {
            Group group = new Group();
            Label label = new Label("Er is iets fout gegaan bij het inloggen, \ncontroleer je gegevens en probeer het nog eens.");
            group.getChildren().add(label);

            Stage loginScherm = new Stage();
            loginScherm.setScene(new Scene(group));
            loginScherm.setTitle("Login-fout");
            loginScherm.show();
        } catch(NumberFormatException e) {
            Group group = new Group();
            Label label = new Label("Er zijn verkeerde karakters ingevoerd, \ncontroleer je gegevens en probeer het nog eens.");
            group.getChildren().add(label);

            Stage loginScherm = new Stage();
            loginScherm.setScene(new Scene(group));
            loginScherm.setTitle("Login-fout");
            loginScherm.show();
        }
        catch(Exception e) {
            Group group = new Group();
            Label label = new Label("Er is iet misgegaan. \n Check je gegevens en probeer het opnieuw.");
            group.getChildren().add(label);

            Stage loginScherm = new Stage();
            loginScherm.setScene(new Scene(group));
            loginScherm.setTitle("Login-fout");
            loginScherm.show();

        }
    }
}
