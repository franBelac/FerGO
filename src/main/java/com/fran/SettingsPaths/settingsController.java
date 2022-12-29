package com.fran.SettingsPaths;


import com.fran.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class settingsController implements Initializable {



    @FXML
    private TextField excelPathField;

    @FXML
    private TextField racPathField;

    @FXML
    private TextField listenerPathField;

    @FXML
    private TextField resultsPathField;

    private final String homeDir = System.getProperty("user.home");

    @FXML
    private void clickSetExcel () {
        DirectoryChooser chooser = new DirectoryChooser();
        File selectedDirectory = chooser.showDialog(Main.primaryStage);
        excelPathField.setText(selectedDirectory.getAbsolutePath());
    }

    @FXML
    private void clickSetRac () {
        DirectoryChooser chooser = new DirectoryChooser();
        File selectedDirectory = chooser.showDialog(Main.primaryStage);
        racPathField.setText(selectedDirectory.getAbsolutePath());
    }

    @FXML
    private void clickListenerPath () {
        DirectoryChooser chooser = new DirectoryChooser();
        File selectedDirectory = chooser.showDialog(Main.primaryStage);
        listenerPathField.setText(selectedDirectory.getAbsolutePath());
    }

    @FXML
    private void clickResultPath () {
        DirectoryChooser chooser = new DirectoryChooser();
        File selectedDirectory = chooser.showDialog(Main.primaryStage);
        resultsPathField.setText(selectedDirectory.getAbsolutePath());
    }

    @FXML
    private void clickApply () throws IOException, URISyntaxException {
        File directoryPath = new File(homeDir + "/fergoConfig");
        if (!directoryPath.exists()) {
            directoryPath.mkdir();
        }
        File excelFile = new File(homeDir + "/fergoConfig/ExcelSavePath.txt");
        File racFile = new File(homeDir + "/fergoConfig/RacSavePath.txt");
        File listenerFile = new File(homeDir + "/fergoConfig/ListenerPath.txt");
        File finalResFile = new File(homeDir + "/fergoConfig/FinalResPath.txt");
        if(!excelPathField.getText().trim().isEmpty()) {
            FileWriter writer = new FileWriter(excelFile);
            writer.write(excelPathField.getText().trim());
            writer.flush();
            writer.close();
            Main.excelSavePath = Path.of(excelPathField.getText());
        } else {

            FileWriter writer = new FileWriter(excelFile);
            writer.write("");
            writer.flush();
            writer.close();
            Main.excelSavePath = null;
        }

        if (!racPathField.getText().trim().isEmpty()) {
            FileWriter writer = new FileWriter(racFile);
            writer.write(racPathField.getText().trim());
            writer.flush();
            writer.close();
            Main.racSavePath = Path.of(racPathField.getText());
        } else {
            FileWriter writer = new FileWriter(racFile);
            writer.write("");
            writer.flush();
            writer.close();
            Main.racSavePath = null;
        }



        if(!listenerPathField.getText().trim().isEmpty()) {
            FileWriter writer = new FileWriter(listenerFile);
            writer.write(listenerPathField.getText().trim());
            writer.flush();
            writer.close();
            Main.listenerPath = Path.of(listenerPathField.getText());
        } else {
            FileWriter writer = new FileWriter(listenerFile);
            writer.write("");
            writer.flush();
            writer.close();
            Main.listenerPath = null;
        }

        if(!resultsPathField.getText().trim().isEmpty()) {
            FileWriter writer = new FileWriter(finalResFile);
            writer.write(resultsPathField.getText().trim());
            writer.flush();
            writer.close();
            Main.finalResPath = Path.of(resultsPathField.getText());
        } else {
            FileWriter writer = new FileWriter(finalResFile);
            writer.write("");
            writer.flush();
            writer.close();
            Main.finalResPath = null;
        }
    }

    @FXML
    public void showCurrentSettings () {
        if(Main.excelSavePath != null) excelPathField.setText(Main.excelSavePath.toString());
        if(Main.racSavePath != null) racPathField.setText(Main.racSavePath.toString());
        if (Main.listenerPath != null) listenerPathField.setText(Main.listenerPath.toString());
        if(Main.finalResPath != null) resultsPathField.setText(Main.finalResPath.toString());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCurrentSettings();
    }
}
