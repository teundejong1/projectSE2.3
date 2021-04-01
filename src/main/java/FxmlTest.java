import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlTest extends Application implements Runnable{



    @Override
    public void start(Stage stage) throws Exception{
        //FXMLLoader instance
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TTT1.fxml"));
        Parent root = loader.load();
        Controller view =  loader.getController();
        //controller.setItems();

        stage.setTitle("Zarathustra");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void run() {
        launch();
    }
}
