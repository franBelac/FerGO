package com.fran.UserInterface;


import com.fran.Main;
import com.fran.Utils.FileUtilities;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateChoiceController implements Initializable {

    @FXML
    Button excelBtn;

    @FXML
    ImageView iconExcel;

    @FXML
    Label excelSuccess;

    @FXML
    Label excelWarningLabel;


    @FXML
    private void generateLocally () throws IOException {
        Main.isInputLocal = true;
        Main.currentTeam = 1;
        Main.showInputLocal();
    }

    @FXML
    private void generateSheet () throws IOException {
        //tu pozovi metodu za odradit exelicu, posalji path i parametre
        // zalijepi label koji potvrduje da je fajl generiran (ili ako je greska?)
        //onemoguci gumb za generirat jos
        try {
            FileUtilities.createExcelFileForRaceEvent(Main.currentEvent.getNumberOfTeams(), Main.currentEvent.getNumberOfRowers(),Main.currentEvent.getNumberOfErgs(), Main.currentEvent.getName(), Main.currentEvent.getLength(), Main.currentEvent.getSplits(),
                    Main.excelSavePath.toString());
        } catch (NullPointerException e) {
            excelWarningLabel.setVisible(true);
            return;
        }
        FadeTransition transition = new FadeTransition(Duration.millis(1200),iconExcel);
        FadeTransition transition2 = new FadeTransition(Duration.millis(500),excelSuccess);
        transition2.setFromValue(0);
        transition2.setToValue(1.0);
        transition.setFromValue(0.0);
        transition.setToValue(1.0);
        iconExcel.setVisible(true);
        transition.play();
        excelBtn.setDisable(true);
        excelSuccess.setVisible(true);
        transition2.play();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iconExcel.setVisible(false);
        excelSuccess.setVisible(false);
        excelWarningLabel.setVisible(false);
    }
}
