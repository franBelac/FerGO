package UserInterface;

import CustomClasses.TableViewElement;
import CustomClasses.TableViewFinishEl;
import CustomClasses.Team;
import MainPackage.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class EndRaceController implements Initializable {

    @FXML
    TableView<TableViewFinishEl> endView;

    @FXML
    TableColumn<TableViewFinishEl, String> colTeamName;

    @FXML
    TableColumn<TableViewFinishEl, String> colTime;

    @FXML
    TableColumn<TableViewFinishEl, String> colAverage;

    @FXML
    TableColumn<TableViewFinishEl, String> colGap;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colTeamName.setCellValueFactory(new PropertyValueFactory<>("Team"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
        colAverage.setCellValueFactory(new PropertyValueFactory<>("Average"));
        colGap.setCellValueFactory(new PropertyValueFactory<>("Gap"));

        colTeamName.prefWidthProperty().bind(endView.widthProperty().divide(4));
        colTime.prefWidthProperty().bind(endView.widthProperty().divide(4));
        colAverage.prefWidthProperty().bind(endView.widthProperty().divide(4));
        colGap.prefWidthProperty().bind(endView.widthProperty().divide(4));

        colTeamName.setSortable(false);
        colTime.setSortable(false);
        colAverage.setSortable(false);
        colGap.setSortable(false);

        colTeamName.setEditable(false);
        colTime.setEditable(false);
        colAverage.setEditable(false);
        colGap.setEditable(false);

        colTeamName.setReorderable(false);
        colTime.setReorderable(false);
        colAverage.setReorderable(false);
        colGap.setReorderable(false);

        endView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //while(!RunRaceController.done){}

        Team winnerTeam = Main.currentTeamList.get(0);
        for (var team : Main.currentTeamList){
            TableViewFinishEl el = new TableViewFinishEl(
                    team.getName(),
                    team.displayTime(team.getTotalTime()),
                    team.displayTime(team.getTotalTime()/team.getRowers().size()),
                    String.format("%.2f",team.getTotalTime()-winnerTeam.getTotalTime())
            );
            endView.getItems().add(el);
        }
    }
}
