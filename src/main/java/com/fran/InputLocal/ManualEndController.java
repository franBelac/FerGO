package com.fran.InputLocal;

import com.fran.CustomClasses.RaceEvent;
import com.fran.Main;
import com.fran.Utils.FERGfileUtilities;
import com.fran.Utils.FileUtilities;
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
    Label warningEndLabel;

    @FXML
    public void clickGenerate () throws IOException {
        try {
            FileUtilities.generateRacFilesFromRaceEvent(Main.currentEvent, Main.racSavePath.toString());
            String s = FERGfileUtilities.createFERGfileFromRaceEvent(Main.currentEvent, "event1");

            //to be deleted
            RaceEvent helpingEvent = FERGfileUtilities.createRaceEventFromFergFile(s);
            System.out.println(helpingEvent.getName());

            confirmLabel.setVisible(true);
            generateBtn.setDisable(true);
        } catch (NullPointerException e) {
            warningEndLabel.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmLabel.setVisible(false);
        warningEndLabel.setVisible(false);
    }
}
