package lib.Validation;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Validation {

    public static void initValidation(JFXTextField input) {
        input.focusedProperty().addListener((o,oldVal,newVal) -> {
            if(!newVal) input.validate();
        });

        input.textProperty().addListener((o,oldVal,newVal) -> input.validate());
    }

    public static void initValidation(JFXPasswordField input) {
        input.focusedProperty().addListener((o,oldVal,newVal) -> {
            if(!newVal) input.validate();
        });

        input.textProperty().addListener((o,oldVal,newVal) -> input.validate());
    }

    public static void initValidation(JFXComboBox input) {
        input.focusedProperty().addListener((o,oldVal,newVal) -> {
            if(!newVal) input.validate();
        });
    }

}
