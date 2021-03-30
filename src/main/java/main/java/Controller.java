package main.java;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class Controller {
    @FXML
    private Label label;
    public void setLabelText(String text) {
        label.setText(text);
    }
}