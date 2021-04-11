package gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import networking.NetworkManager;
import networking.commands.LoginCommand;
import networking.connection.ConnectionFailedException;

public class LoginController {

    public TextField naamVeld;
    public TextField ipVeld;
    public TextField poortVeld;
    public Button loginKnop;
    private MenuController menuController;

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

public void login() throws ConnectionFailedException {
        if(naamVeld.getCharacters().length()>0 && ipVeld.getCharacters().length()>0 && poortVeld.getCharacters().length()>0) {
            int portnumber = Integer.parseInt(poortVeld.getCharacters().toString());
            NetworkManager manager = new NetworkManager(ipVeld.getCharacters().toString(), portnumber);
            manager.sendCommand(new LoginCommand(naamVeld.getCharacters().toString()));
            menuController.rootSetNetworkManager(manager);

        }
    }
}
