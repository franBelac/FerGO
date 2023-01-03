package com.fran;


import com.fran.CustomClasses.RaceEvent;
import com.fran.CustomClasses.Team;
import com.fran.InputLocal.InputPopupController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class Main extends Application {

    // javafx graficke varijable

public static Stage primaryStage;

private static BorderPane mainLayout;

// nasi podaci za obradu

public static RaceEvent currentEvent;

public static List<Team> currentTeamList; //dzomke dodao!!

public static int currentTeam;

// putanje do spremljenih pathova

public static Path excelSavePath;

public static Path racSavePath;

public static Path listenerPath;

public static Path finalResPath;

private static AnchorPane racePane;


// controls if a popup window should be invoked
public static boolean isInputLocal = false;
public static Object lock = new Object();

public static LinkedList<String> racePaths;




    @Override
    public void start(Stage stage) throws Exception{
        String homeDir = System.getProperty("user.home");

        Path excel = Path.of(homeDir + "/fergoConfig/ExcelSavePath.txt");
        try {
            BufferedReader read1 = new BufferedReader(
                    new InputStreamReader(Files.newInputStream(excel)));
            String excelResult = read1.readLine();
            read1.close();
            if (!excelResult.isEmpty()) excelSavePath = Path.of(excelResult);
        } catch (Exception e) {

        }

        Path rac = Path.of(homeDir + "/fergoConfig/RacSavePath.txt");

        try {
            BufferedReader read2 = new BufferedReader(
                    new InputStreamReader(Files.newInputStream(rac)));
            String racSavePathResult = read2.readLine();
            read2.close();
            if (!racSavePathResult.isEmpty()) racSavePath = Path.of(racSavePathResult);
        } catch (Exception e) {}

        Path listener = Path.of(homeDir + "/fergoConfig/ListenerPath.txt");
        try {
            BufferedReader read3 = new BufferedReader(
                    new InputStreamReader(Files.newInputStream(listener)));
            String listenerPathResult = read3.readLine();
            read3.close();
            if(!listenerPathResult.isEmpty()) listenerPath = Path.of(listenerPathResult);
        } catch (Exception e) {}


        Path finalRes = Path.of(homeDir + "/fergoConfig/FinalResPath.txt");

        try {
            BufferedReader read4 = new BufferedReader(
                    new InputStreamReader(Files.newInputStream(finalRes)));
            String finalResPathResult = read4.readLine();
            read4.close();
            if(!finalResPathResult.isEmpty()) finalResPath = Path.of(finalResPathResult);
        } catch (Exception e) {}


        FXMLLoader runRaceloader = new FXMLLoader();
        runRaceloader.setLocation(getClass().getResource("/com/fran/runRace.fxml"));
        racePane = runRaceloader.load();



        primaryStage = stage;
        primaryStage.setTitle("FerGO");
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(600);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("com/fran/LogoNonTrans.png"));
        showMainView();
        showDefaultCenter();
    }


    private void showMainView () throws IOException {
        mainLayout = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(mainLayout);
        String CSS = this.getClass().getResource("/com/fran/styles.css").toExternalForm();
        scene.getStylesheets().add(CSS);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showDefaultCenter() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/com/fran/defaultCenter.fxml"));
        AnchorPane defCenter = loader.load();
        mainLayout.setCenter(defCenter);
    }

    public static void showArgsInput () throws IOException {
        if (isInputLocal) InputPopupController.showPopup();
        if (isInputLocal) return;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/com/fran/ArgsInput.fxml"));
        AnchorPane argsInp = loader.load();
        mainLayout.setCenter(argsInp);
}

    public static void showCreateChoice () throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/com/fran/CreateChoice.fxml"));
        AnchorPane createChoice = loader.load();
        mainLayout.setCenter(createChoice);
    }

    public static void showSettings () throws IOException {
        if(isInputLocal) InputPopupController.showPopup();
        if (isInputLocal) return;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/com/fran/settings.fxml"));
        AnchorPane settingsPane = loader.load();
        mainLayout.setCenter(settingsPane);
    }

    public static void showInputLocal () throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/com/fran/InputLocal.fxml"));
        AnchorPane inputPane = loader.load();
        mainLayout.setCenter(inputPane);
    }

    public static void showManualEnd () throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/com/fran/manualEnd.fxml"));
        AnchorPane manualEndPane = loader.load();
        mainLayout.setCenter(manualEndPane);
    }

    public static void showExcelInput () throws IOException {
        if(isInputLocal) InputPopupController.showPopup();
        if (isInputLocal) return;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/com/fran/ExcelPane.fxml"));
        AnchorPane excelPane = loader.load();
        mainLayout.setCenter(excelPane);
    }
    public static void showRunRace () throws IOException {
        if(isInputLocal) InputPopupController.showPopup();
        if (isInputLocal) return;


        mainLayout.setCenter(racePane);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
