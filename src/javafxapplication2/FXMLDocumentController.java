/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Martin
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField inputField;
    @FXML
    private Label label2;
    @FXML
    MenuItem exit;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        System.out.println("Input: " + inputField.getText());
        label.setText("Hello World! Hello World! Hello World!");
    }

    @FXML
    private void handleMenuAction(ActionEvent event) {
        System.out.println("event: " + event.getSource().toString());
        if (!(event.getSource() instanceof MenuItem)) {
            System.err.println("Error: Wrong Event in handleMenuAction");
            return;
        }
        MenuItem mi = (MenuItem) event.getSource();
        String nemuId = mi.getId();
        if (exit == mi) {
            System.out.println("yep is exit");
            System.out.println("bye bye");
            Platform.exit();
        }

        System.out.println("Menu '" + nemuId + "' clicked");
        label.setText("Menu '" + nemuId + "' clicked");
        switch (nemuId) {
            case "MenuClose":
                System.out.println("bye bye");
                Platform.exit();
                break;
            default:
                System.out.println("unkknown menu clicked");
        }
    }

    @FXML
    private void handleKeyAction(KeyEvent event) {
        System.out.println("event (handleKeyPressAction): " + event.getSource().toString());
        if (!(event.getSource() instanceof TextField)) {
            System.err.println("Error: Wrong Event in handleKeyPressAction");
            return;
        }
        TextField tf = (TextField) event.getSource();
        System.out.println("Text in '" + tf.getId() + "' entered: " + tf.getText());
        label.setText("Text in '" + tf.getId() + "' entered: " + tf.getText());
        label2.setText("Text in '" + tf.getId() + "' entered: " + tf.getText());
    }

    @FXML
    private void handleInputChangedAction(Event event) {
        System.out.println("event (handleInputChangedAction): " + event.getSource().toString());
//        if (!(event.getSource() instanceof MenuItem)){
//            System.err.println("Error: Wrong Event in handleMenuAction");
//            return;
//        }
//        MenuItem mi = (MenuItem)event.getSource();
//        System.out.println("Menu '" + mi.getId() + "' clicked");
//        label.setText("Menu '" + mi.getId() + "' clicked");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Achtung: bei der Auswahl eines Menus wird der Listener nicht angesprochen! Fokus bleibt im Eingabefeld!
        inputField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (!newValue) {
                    System.out.println("Focusing out from textfield");
                }
            }
        });

        inputField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                System.out.println("old value: " + oldValue + ", new value: " + newValue);
                if (newValue == null) {
                    return;
                }

            }
        });

    }

}
