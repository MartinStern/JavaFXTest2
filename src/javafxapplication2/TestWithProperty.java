/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Martin
 */
public class TestWithProperty {
    
    
    private final StringProperty testField;

    public TestWithProperty(String testField) {
        this.testField = new SimpleStringProperty(testField);
    }
    
    public StringProperty testFieldProperty(){
        return testField;
    }

    public String getTestField() {
        return testField.get();
    }

    public void setTestField(String testField) {
        this.testField.set(testField);
    }
    
}
