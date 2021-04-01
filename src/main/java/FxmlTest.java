import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlTest extends Application implements Runnable{

    FXMLLoader loader;
    int schermchecker = 0;
    Parent root;
    Controller controller;

    @Override
    public void start(Stage stage) throws Exception{
        //FXMLLoader instance

        switch (schermchecker) {
            case 1:
                loader = new FXMLLoader(getClass().getResource("/TTT1.fxml"));
                root = loader.load();
                controller =  loader.getController();
                break;
            default:
                loader = new FXMLLoader(getClass().getResource("/menu1.fxml"));
                root = loader.load();
                controller =  loader.getController();

                break;
        }



        stage.setTitle("Zarathustra");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void run() {
        launch();
    }

    public void showMenu() {
        schermchecker = 0;
        launch();
    }

    public void showTTT() {
        schermchecker = 1;
        launch();
    }
}
