import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label lblOutput;

    @FXML
    private Button eventHandler;

    @FXML
    private Button RecordProduction;

    @FXML
    public void printConsole(ActionEvent event) {
        System.out.println();

    }

}