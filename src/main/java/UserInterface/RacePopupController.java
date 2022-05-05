package UserInterface;

import MainPackage.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RacePopupController {

    private static Stage window;

    public static void showPopup() throws IOException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("FerGO");
        window.setResizable(false);
        window.getIcons().add(new Image("LogoNonTrans.png"));
        window.setMinWidth(500);
        window.setMinHeight(300);

        FXMLLoader loader = new FXMLLoader();
        AnchorPane popupLayout = new AnchorPane();
        loader.setLocation(Main.class.getResource("../InputRace.fxml"));
        popupLayout = loader.load();
        Scene scene = new Scene(popupLayout);
        window.setScene(scene);
        window.showAndWait();

    }

    public void clickOK () throws IOException {
        Main.isInsideRace = false;
        window.close();
    }

    public void clickCancel () {
        window.close();
    }

}
