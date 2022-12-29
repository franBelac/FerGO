package com.fran.InputLocal;

import com.fran.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class InputPopupController {

private static Stage window;

    public static void showPopup() throws IOException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("FerGO");
        window.setResizable(false);
        window.getIcons().add(new Image("com/fran/LogoNonTrans.png"));
        window.setMinWidth(500);
        window.setMinHeight(300);

        FXMLLoader loader = new FXMLLoader();
        AnchorPane popupLayout = new AnchorPane();
        loader.setLocation(Main.class.getResource("../inputPopup.fxml"));
        popupLayout = loader.load();
        Scene scene = new Scene(popupLayout);
        window.setScene(scene);
        window.showAndWait();

    }

    public void clickOK () {
        Main.isInputLocal = false;
        window.close();
    }

    public void clickCancel () {
        window.close();
    }
}
