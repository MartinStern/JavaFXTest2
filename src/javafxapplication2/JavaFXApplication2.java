/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.util.Optional;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Martin
 */
public class JavaFXApplication2 extends Application {
    static Stage window;
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        System.out.println("ConditionalFeature.INPUT_METHOD is supported: " + Platform.isSupported(ConditionalFeature.INPUT_METHOD));

        Scene scene = new Scene(root);
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        }
        );
        window.setScene(scene);
        window.show();
    }

    public static void closeProgram() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Wollen Sie die Anwendung schliessen?");
        alert.setContentText("Ja -> OK oder Nein -> Cancel ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
    // ... user chose OK
            System.out.println("tschüss");
            window.close();
        } else {
    // ... user chose CANCEL or closed the dialog
            System.out.println("ich bleib noch");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
