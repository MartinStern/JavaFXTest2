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
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.beans.property.StringProperty;
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
    private TextField inputField2;
    @FXML
    private Label label2;
    @FXML
    private MenuItem exit;
    @FXML
    private ComboBox cboNames;
    @FXML
    private ChoiceBox choiceNames;

    
    private TestWithProperty testWithProperty = new TestWithProperty("bla");
    
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
        System.out.println("Menu '" + nemuId + "' clicked");
        label.setText("Menu '" + nemuId + "' clicked");
        switch (nemuId) {
            case "MenuClose":
                System.out.println("bye bye");
                JavaFXApplication2.closeProgram();
                break;
            default:
                System.out.println("unkknown menu clicked");
        }

// alternative Variante ...
//        if (exit == mi) {
//            System.out.println("yep is exit");
//            System.out.println("bye bye");
//            JavaFXApplication2.closeProgram();
//        }
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
        //label2.setText("Text in '" + tf.getId() + "' entered: " + tf.getText());  das machen wir mit binding!
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

        cboNames.getItems().addAll(
                "Item one",
                "Item two",
                "Item three",
                "Item four"
        );

        class Choice {

            int key;
            String name;

            Choice(int key, String name) {
                this.key = key;
                this.name = name;
            }

            @Override
            public String toString() {
                return name;
            }
        }
        
        ObservableList<Choice> ol = FXCollections.observableArrayList();
        ol.add(new Choice(1, "aaaaa"));
        ol.add(new Choice(2, "bb"));
        ol.add(new Choice(3, "-"));
        ol.add(new Choice(4, "zzzzz"));
        choiceNames.setItems(ol);

//        testWithProperty = new TestWithProperty("bla");
        
        
        label2.textProperty().bind(inputField.textProperty());
        inputField.textProperty().bindBidirectional(inputField2.textProperty());
        testWithProperty.testFieldProperty().bindBidirectional(inputField.textProperty());
        testWithProperty.setTestField("i woas ned");
        
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
            }
        });

//        inputField.textProperty().addListener((observable, oldValue, newValue) -> {
//                System.out.println("old value: " + oldValue + ", new value: " + newValue);
//            
//        });

        inputField.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                System.out.println("invalidated?");
            }
        });

    }

}
