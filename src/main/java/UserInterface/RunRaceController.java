package UserInterface;

import CustomClasses.RaceEvent;
import CustomClasses.TableViewElement;
import CustomClasses.TableViewFinishEl;
import CustomClasses.Team;
import MainPackage.Main;
import Utils.RaceResultParserUtilities;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Utils.FolderListenerUtilities;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RunRaceController implements Initializable {

    private Thread t;
    AtomicBoolean running = new AtomicBoolean(false);

    static boolean done = false;

    @FXML
    Button startBtn;

    @FXML
    Button finishBtn;

    @FXML
    TableColumn<TableViewElement,String> column1;

    @FXML
    TableColumn<TableViewElement,String> column2;

    @FXML
    TableView<TableViewElement> wholeView;

    private Label placeholderLabel = new Label("Click \"Start\" to start ");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        placeholderLabel.setStyle("-fx-text-fill: white;");
        wholeView.setPlaceholder(placeholderLabel);
        column1.setSortable(false);
        column2.setSortable(false);
        wholeView.setEditable(false);
        column1.setReorderable(false);
        column2.setReorderable(false);
        wholeView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        finishBtn.setDisable(true);
        column1.setCellValueFactory(new PropertyValueFactory<>("raceName"));
        column2.setCellValueFactory(new PropertyValueFactory<>("finish"));
        column1.prefWidthProperty().bind(wholeView.widthProperty().divide(2));
        column2.prefWidthProperty().bind(wholeView.widthProperty().divide(2));

    }
    @FXML
    private void clickStart () {
        Main.isInsideRace = true;

        Main.racePaths = new LinkedList<>();
        if(Main.currentTeamList != null){for(var x : Main.currentTeamList){System.out.println(x);}}
        Main.currentTeamList = new LinkedList<>();

        t = new Thread(){
            @Override
            public void run() {
                running.set(true);
                while(running.get()) {
                    String pathName = FolderListenerUtilities.newFileCreated(Main.listenerPath, running);

                    Platform.runLater(()->Main.racePaths.add(pathName));
                    if(pathName != null) {
                        Platform.runLater(() -> {
                            TableViewElement el = new TableViewElement(pathName.substring(pathName.lastIndexOf("\\") + 1));
                            el.getFinish().setOnAction(e -> {
                                wholeView.getItems().remove(el);
                                Main.racePaths.remove(pathName);
                            });
                            wholeView.getItems().add(el);
                        })
                    ;}

                }

            }
        };

        placeholderLabel.setText("Finished races will start appearing now");
        wholeView.setPlaceholder(placeholderLabel);
        startBtn.setDisable(true);
        finishBtn.setDisable(false);
        t.setDaemon(true);
        t.start();

    }


    @FXML
    private void clickFinish () throws InterruptedException, IOException {
        RacePopupController.showPopup();
        if (Main.isInsideRace) return;

        running.set(false);
        finishBtn.setDisable(true);
        startBtn.setDisable(false);

        wholeView.getItems().removeAll(wholeView.getItems());

        placeholderLabel.setText("Click \"Start\" to start ");
        wholeView.setPlaceholder(placeholderLabel);

        for (var racePath : Main.racePaths) {
            if(racePath == null){break;}
            List<String> stringList = Arrays.asList(racePath.split("//"));
            Collections.reverse(stringList);
            String newFileName = RaceResultParserUtilities.createFormattedFile(racePath,stringList.get(0).substring(stringList.get(0).lastIndexOf("\\")));
        }
        List<String> dataToBeWritten = new LinkedList<>();
        Collections.sort(Main.currentTeamList);
        Main.currentTeamList.remove(0);
        Team winnerTeam = Main.currentTeamList.get(0);
        for (var team : Main.currentTeamList){
            dataToBeWritten.add(team.toString().concat(" ").concat(String.format("%.2f",team.getTotalTime()-winnerTeam.getTotalTime()))
            .concat(" ").concat(String.valueOf(team.getTotalTime())));
        }
        System.out.println(dataToBeWritten);
        Path finalResults = Path.of(Main.finalResPath.toString().concat("\\FINAL.txt"));
        Files.write(finalResults,dataToBeWritten);


        Main.showEndRace();


    }

}
