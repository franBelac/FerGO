package UserInterface;

import CustomClasses.TableViewElement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class EndRaceController implements Initializable {

    @FXML
    TableView<String> endView;

    @FXML
    TableColumn<String, String> colTeamName;

    @FXML
    TableColumn<String, String> colTime;

    @FXML
    TableColumn<String, String> colAverage;

    @FXML
    TableColumn<String, String> colGap;





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




    }
}
