package InputLocal;

import MainPackage.Main;
import Utils.FileUtilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManualEndController implements Initializable {

    @FXML
    Button generateBtn;

    @FXML
    Label confirmLabel;

    @FXML
    public void clickGenerate () throws IOException {

        FileUtilities.generateRacFilesFromRaceEvent(Main.currentEvent,Main.racSavePath.toString());
        confirmLabel.setVisible(true);
        generateBtn.setDisable(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmLabel.setVisible(false);
    }
}
