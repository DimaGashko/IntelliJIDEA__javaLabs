package com.labs.lab5.ELib.windows;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerts {

    static private Alert alertInfo;
    static private Alert alertConfirm;
    static private Alert alertErr;

    public Alerts() {

    }


    public Optional<ButtonType> show(Alert alert, String header, String content) {
        alert.setHeaderText(header);
        alert.setContentText(content);

        return alert.showAndWait();
    }

    public Optional<ButtonType> show(Alert alert, String header) {
        return show(alert, header, "");
    }

    /**
     * @return Возвращает ответ пользователя в AlertConfirm
     */
    public boolean getAnswer(Optional<ButtonType> option) {
        // TODO: add isPresent check
        return (option.get() == ButtonType.OK);
    }

    private void initAlertInfo() {
        if (alertInfo != null) return;

        alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("ELib - your world of books");
    }

    private void initAlertConfirm() {
        if (alertConfirm != null) return;

        alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirm.setTitle("ELib - your world of books");
    }

    private void initAlertErr() {
        //if (alertErr != null) return;
        alertErr = new Alert(Alert.AlertType.ERROR);

        alertErr.setTitle("ELib - your world of books");
    }

    public Alert getAlertInfo() {
        initAlertInfo();
        return alertInfo;
    }

    public Alert getAlertConfirm() {
        initAlertConfirm();
        return alertConfirm;
    }

    public Alert getAlertErr() {
        initAlertErr();
        return alertErr;
    }
}
